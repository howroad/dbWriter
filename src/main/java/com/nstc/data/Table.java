package com.nstc.data;
import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.CaseFormat;

import oracle.sql.TIMESTAMP;
/**
 * 
 * <p>Title: Table.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2018年12月26日 下午4:56:49
 *
 */
public class Table {
    
	private String tableName;
	private String tableRemart;
	List<Line> lineList;
	public final static String TAB = "    ";
	private final static String TABTAB = "        ";
	//private final static String TABTABTAB = "            ";
	private final static int SAVE = 0;
	private final static int DELETE = 1;
	private final static int UPDATE = 2;
	private final static int GET = 3;
	private final static String[] COM_NAME = new String[] {"新增","删除","修改","查询"};
	private final static String[] COM_PARAM = new String[] {"model","id","model","scope"};
	public final static String PO = "";
	private final static String[] COM_PARAM_TYPE = new String[] { "实体","主键","实体", "查询条件"};
	private final static int DAO = 4;
	private final static int SERVICE = 5;
	private final static String UNDER_LINE = "_";
	private String appNo = null;
	
	
    public void writeCommonFile() {
        String xmlPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\dao\\TEMP_Common.xml";
        String daoInterfacePath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\dao\\ICommonDao.java";
        String daoImplPath = System.getProperty("user.dir") + "\\src\\main\\java\\com\\nstc\\temp\\dao\\CommonDaoImpl.java";
        File daoInterface = new File(daoInterfacePath);
        File daoImpl = new File(daoImplPath);
        File xml = new File(xmlPath);
        WriteUtil.writeFileInsertKey(xml, this, WriteUtil.XML);
        WriteUtil.writeFileInsertKey(daoInterface, this, WriteUtil.DAO_INTERFACE);
        WriteUtil.writeFileInsertKey(daoImpl, this, WriteUtil.DAO_IMPL);

    }
	/**
	 * 是否有时间类型
	 * @Description:
	 * @return boolean
	 * @author luhao
	 * @since：2019年1月30日 下午4:44:48
	 */
	private boolean hasDateType() {
	    if(lineList == null || lineList.isEmpty()) {
	        throw new RuntimeException("无列表信息！");
	    }
	    for (Line line : lineList) {
            if(line == null) {
                throw new RuntimeException("Line中无信息！");
            }
            if("Date".equals(line.getParamType()) || Types.DATE == line.getColumnType() || Types.TIMESTAMP == line.getColumnType()) {
                return true;
            }
        }
	    return false;
	}
	/**
	 * 获得插入语句的字段，例如to_date('','')
	 * @Description:
	 * @param obj
	 * @param line
	 * @return String
	 * @author luhao
	 * @since：2018年12月26日 下午5:17:29
	 */
	private String getInsertValue(Object obj,Line line) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    int columnType = line.getColumnType();
	    String result = null;
	    if(obj == null) {
	        result = "null";
	    }else if (Types.DECIMAL == columnType) {
	        result = String.valueOf(obj);
        } else if (Types.VARCHAR == columnType) {
            result = "'" + String.valueOf(obj) + "'";
        } else if (Types.TIMESTAMP == columnType || Types.DATE == columnType) {
            if(obj.getClass() == oracle.sql.TIMESTAMP.class){
                TIMESTAMP time = (TIMESTAMP) obj;
                Date dateTime = null;
                try {
                    dateTime = time.dateValue();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                String value = sdf.format(dateTime);
                result = "TO_DATE('" + value + "', 'YYYY-MM-DD HH24:MI:SS')";
            }else {
                String value = sdf.format(obj);
                result = "TO_DATE('" + value + "', 'YYYY-MM-DD HH24:MI:SS')";
            }
        } else {
            throw new RuntimeException("未知类型！");
        }
	    return result;
	}
	/**
	 * 根据集合集合内容生成数据库插入语句
	 * @Description:
	 * @param out
	 * @param dataList
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:23:39
	 */
	public void writeDate(PrintWriter out,List<List<Object>> dataList) {
	    String seqNext = getSeqName() + ".NEXTVAL";
	    String primaryKeyName = lineList.get(0).getColumnName();
	    for (List<Object> list : dataList) {
	        String primaryKeyValue = String.valueOf(list.get(0));
	        out.print("INSERT INTO " + tableName + "(");
	        for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
	            Line line = iterator.next();
	            if (iterator.hasNext()) {
	                out.print(line.getColumnName() + ",");
	            } else {
	                out.println(line.getColumnName() + ") ");
	            }
	        }
	        out.print("SELECT ");
	        int index = 0;
	        for (ListIterator<Object> iterator = list.listIterator(); iterator.hasNext();) {
	            boolean first = false;
	            boolean last = false;
	            first = !iterator.hasPrevious();
                Object object = iterator.next();
                String value = getInsertValue(object, lineList.get(index++));
                last = !iterator.hasNext();
                if(DbSettings.dealSEQ && first) {
                    if(last) {
                        out.print(seqNext);
                    }else {
                        out.print(seqNext + ",");
                    }
                }else {
                    if(last) {
                        out.print(value);
                    }else {
                        out.print(value + ",");
                    }
                }
            }
	        out.println(" FROM DUAL ");
	        out.println("WHERE NOT EXISTS (SELECT 1 FROM " + tableName + " WHERE " + primaryKeyName + " = '" + primaryKeyValue + "')");
	        out.println("/");
        }
       
	}
	/**
	 * 生成序列的创建语句
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:25:19
	 */
	public void writeSEQ(PrintWriter out) {
	    String seqName = getSeqName();
        out.println("DECLARE");
        out.println(TAB + "CNT INTEGER;");
        out.println("BEGIN");
        out.println(TAB + "SELECT COUNT(0) INTO CNT FROM USER_SEQUENCES");
        out.println(TAB + "WHERE SEQUENCE_NAME = UPPER('" + seqName + "');");
        out.println(TAB + "IF CNT = 0 THEN");
        out.println(TAB + "EXECUTE IMMEDIATE 'CREATE SEQUENCE " + seqName);
        out.println(TABTAB + "MINVALUE 1");
        out.println(TABTAB + "MAXVALUE 9999999999999999999999999");
        out.println(TABTAB + "START WITH 1");
        out.println(TABTAB + "INCREMENT BY 1");
        out.println(TABTAB + "CACHE 20';");
        out.println(TAB + "END IF;");
        out.println("END;");
        out.println("/");	    
	}
	/**
	 * 生成主键索引的创建语句
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:25:30
	 */
	public void writePrimaryKey(PrintWriter out) {
	    String columnName = lineList.get(0).getColumnName();
	    String primaryKeyName = tableName + "_PK";
	    out.println("DECLARE");
        out.println(TAB + "CNT INTEGER;");
        out.println("BEGIN");
        out.println(TAB + "SELECT COUNT(0) INTO CNT FROM USER_CONSTRAINTS");
        out.println(TAB + "WHERE CONSTRAINT_NAME = UPPER('" + primaryKeyName + "');");
        out.println(TAB + "IF CNT = 0 THEN");
        out.println(TAB + "EXECUTE IMMEDIATE 'ALTER TABLE " + tableName + " ADD CONSTRAINT " + primaryKeyName + " PRIMARY KEY(" + columnName + ")';");
        out.println(TAB + "END IF;");
        out.println("END;");
        out.println("/");
	}
	/**
	 * 生成表的创建语句
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:25:45
	 */
	public void writeCreateTable(PrintWriter out) {
	    out.println("DECLARE");
	    out.println(TAB + "CNT INTEGER;");
	    out.println("BEGIN");
	    out.println(TAB + "SELECT COUNT(0) INTO CNT FROM USER_ALL_TABLES");
	    out.println(TAB + "WHERE TABLE_NAME = UPPER('" + tableName + "');");
	    out.println(TAB + "IF CNT = 0 THEN");
	    out.println(TAB + "EXECUTE IMMEDIATE 'CREATE TABLE " + tableName + "(");
	    for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            Line line = iterator.next();
            if(iterator.hasNext()) {
                out.println(TABTAB + line.getColumnName() + " " + line.getColumnTypeName() + ",");
            }else {
                out.println(TABTAB + line.getColumnName() + " " + line.getColumnTypeName() + ")';");
            }
        }
	    out.println(TAB + "END IF;");
	    out.println("END;");
	    out.println("/");
	    out.println("COMMENT ON TABLE " + tableName + " IS '" + tableRemart + "';");
	    out.println("/");
        for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            Line line = iterator.next();
            out.println("COMMENT ON COLUMN " + tableName + "." + line.getColumnName() + " is '" + line.getRemark() + "';");
            out.println("/");
        }
        writePrimaryKey(out);
	}
	/**
	 * 生成JAVABEAN的成员变量等信息
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:25:55
	 */
    public void writeJaveBean(PrintWriter out) {
        out.println("package com.nstc." + getAppNo().toLowerCase() + ".model;");
        if(hasDateType()) {
            out.println("import java.util.Date;"); 
        }
        out.println("/** " + tableRemart + " */");
        out.println("public class " + getClassName() +" {");
        for(ListIterator<Line> iterator = lineList.listIterator();iterator.hasNext();) {
            Line line = iterator.next();
            out.println(TAB + line.commentLine());
            out.println(TAB + line.paramLine());
        }
        out.println();
        for(ListIterator<Line> iterator = lineList.listIterator();iterator.hasNext();) {
            Line line = iterator.next();
            String paramName = line.getParamName();
            String upName = paramName.substring(0,1).toUpperCase()+paramName.substring(1);
            out.println(TAB + "public void set" + upName + "(" + line.getParamType() + line.getParamName() + "){");
            out.println(TABTAB + "this." + line.getParamName() + " = " + line.getParamName() + ";");
            out.println(TAB + "}");
            out.println();
            out.println(TAB + "public " + line.getParamType() + "get" + upName + "(){");
            out.println(TABTAB + "return this." + line.getParamName() + ";");
            out.println(TAB + "}");
            out.println();
        }
        out.println("}");
    }
    /**
     * 输出DAO接口和实现类和service实现类的语句
     * @Description:
     * @param out
     * @return void
     * @author luhao
     * @since：2018年12月28日 下午6:26:22
     */
    public void writeDao(PrintWriter out) {
        writeDaoInterface(out);
        
        out.println();
        out.println();
        
        writeDaoImpl(out);
        
        out.println();
        out.println();
        
        writeServiceImpl(out);

        //writeTestUnit(out);
    }
    public void writeDaoInterface(PrintWriter out) {
        writeSaveDao(out);
        out.println();
        writeDeleteDao(out);
        out.println();
        writeUpdateDao(out);
        out.println();
        writeGetDao(out);
        out.println();
    }
    
    public void writeDaoImpl(PrintWriter out) {
        writeSaveDaoImpl(out,DAO);
        out.println();
        writeDeleteDaoImpl(out,DAO);
        out.println();
        writeUpdateDaoImpl(out,DAO);
        out.println();
        writeGetDaoImpl(out,DAO);
        out.println();
    }
    
    public void writeServiceImpl(PrintWriter out) {
        writeSaveDaoImpl(out,SERVICE);
        out.println();
        writeDeleteDaoImpl(out,SERVICE);
        out.println();
        writeUpdateDaoImpl(out,SERVICE);
        out.println();
        writeGetDaoImpl(out,SERVICE);
        out.println();
    }
    
    /**
     * 输出xml中curd的语句
     * @Description:
     * @param out
     * @return void
     * @author luhao
     * @since：2018年12月28日 下午6:27:00
     */
    public void writeXml(PrintWriter out) {
        writeSaveXml(out);
        out.println();
        writeDeleteXml(out);
        out.println();
        writeUpdateXml(out);
        out.println();
        writeGetXml(out);
    }
    /**
     * 输出保存的xml语句
     * @Description:
     * @param out
     * @return void
     * @author luhao
     * @since：2018年12月28日 下午6:27:13
     */
	private void writeSaveXml(PrintWriter out) {
	    
		out.println(TAB + "<!-- 新增" + tableRemart + " -->");
		out.println(TAB + "<insert id=\"save" + getEntityName() + "\" parameterClass=\""+ getEntityClassPath() + "\">");
		if(DbSettings.dealSEQ) {
		    out.println(TAB + TAB + "<selectKey resultClass=\"java.lang.Integer\" keyProperty=\"" + lineList.get(0).getParamName() + "\">");
		    out.println(TAB + TABTAB + "SELECT " + getSeqName() + ".NEXTVAL AS " + lineList.get(0).getParamName() + " FROM DUAL");
		    out.println(TAB + TAB + "</selectKey>");
		}
		out.println(TAB + TAB + "INSERT INTO " + tableName + " (");
		for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
			Line line = (Line) iterator.next();
            if(!iterator.hasNext()) {
            	out.println(TAB + TABTAB + line.getColumnName());
            }else {
            	out.println(TAB + TABTAB + line.getColumnName() + ",");
            }
		}
		out.println(TAB + TAB + ") VALUES(");
		for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            boolean first = false;
            first = !iterator.hasPrevious();
			Line line = (Line) iterator.next();
            if(!iterator.hasNext()) {
            	out.println(TAB + TABTAB + line.getSharpName());
            }else {
                if(first) {
                    //out.println(TABTAB + seqNext + ",");
                    out.println(TAB + TABTAB + line.getSharpName() + ",");
                }else {
                    out.println(TAB + TABTAB + line.getSharpName() + ",");
                }
            }
		}
		out.println(TAB + TAB + ")");
		out.println(TAB + "</insert>");
	}
	/**
	 * 输出按照Id删除的xml语句
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:27:36
	 */
	private void writeDeleteXml(PrintWriter out) {
		out.println(TAB + "<!-- 删除" + tableRemart + " -->");
		out.println(TAB + "<delete id=\"delete" + getEntityName() + "ById\" parameterClass=\"java.lang.Integer\">");
		out.println(TAB + TAB + "delete " + tableName +" WHERE " + lineList.get(0).getColumnName() + " = " + lineList.get(0).getSharpName());
		out.println(TAB + "</delete>");		
	}
	/**
	 * 输出按照字段修改的语句
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:27:47
	 */
	private void writeUpdateXml(PrintWriter out) {
		out.println(TAB + "<!-- 修改" + tableRemart + " -->");
		out.println(TAB + "<update id=\"update" + getEntityName() + "\" parameterClass=\"" + getEntityClassPath() + "\">");
		out.println(TAB + TAB + "UPDATE " + tableName + " SET");
		for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
		    boolean first = false;
		    first = !iterator.hasPrevious();
			Line line = (Line) iterator.next();
			if(first) {
			    out.println(TAB + TAB + line.getColumnName() + " = " + line.getSharpName());
			}else {
			    out.println(TAB + TAB + "<isNotEmpty prepend=\",\" property=\"" + line.getParamName() + "\">");
			    out.println(TAB + TABTAB + line.getColumnName() + " = " + line.getSharpName());
			    out.println(TAB + TAB + "</isNotEmpty>");
			}
		}
		out.println(TAB + TAB + "WHERE " + lineList.get(0).getColumnName() + " = " + lineList.get(0).getSharpName());
		out.println(TAB + "</update>");
	}
	/**
	 * 输出保存的xml语句
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:28:05
	 */
	private void writeGetXml(PrintWriter out) {
		out.println(TAB + "<!-- 查询" + tableRemart + " -->");
		out.println(TAB + "<select id=\""+"get"+getEntityName()+"List\" parameterClass=\"" + getEntityClassPath() + "\" resultClass=\"" + getEntityClassPath() + "\">");
		out.println(TAB + TAB + "SELECT");
		for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            Line line = iterator.next();
            String printLine = "T." + line.getColumnName() + " AS " + "\"" + line.getParamName() + "\",";
            if(!iterator.hasNext()) {
                printLine = printLine.replace(",", "");
            }
            out.println(TAB + TABTAB + printLine);
		}
		out.println(TAB + TAB + "FROM " + tableName + " T WHERE 1 = 1");
        for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            Line line = iterator.next();
            out.println(TAB + TAB + "<isNotEmpty property=\"" + line.getParamName() + "\" prepend=\"and\">");
            String printLine = "T." + line.getColumnName() + " = " + line.getSharpName();
            out.println(TAB + TABTAB + printLine);
            out.println(TAB + TAB + "</isNotEmpty>");
        }
        out.println(TAB + "</select>");
	}
	/**
	 * 输出DAO接口的注释信息
	 * @Description:
	 * @param out
	 * @param type
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:28:17
	 */
	private void commont(PrintWriter out,int type) {
        String comName = COM_NAME[type];
        String paramName = COM_PARAM[type];
        String paramType = COM_PARAM_TYPE[type];
        out.println(TAB + "/**");
        out.println(TAB + "* " + comName + tableRemart);
        out.println(TAB + "* @param " + paramName + " " + tableRemart + paramType);
        out.println(TAB + "* @author luhao");
        if(GET == type) {
            out.println(TAB + "* @return " + tableRemart + "集合");
        }else if(SAVE == type){
            out.println(TAB + "* @return " + tableRemart + "主键");
        }
        out.println(TAB + "* @since " + getNow());
        out.println(TAB + "*/");
	}
	/**
	 * 输出dao保存接口
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:28:36
	 */
	private void writeSaveDao(PrintWriter out) {
	    commont(out, SAVE);
		out.println(TAB + "public Integer save" + getEntityName() + "(" + getClassName() +" model);");
	}
	/**
	 * 输出dao保存实现类
	 * @Description:
	 * @param out
	 * @param type
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:28:52
	 */
	private void writeSaveDaoImpl(PrintWriter out,int type) {
	    if(DbSettings.implCommont) {
	        commont(out, SAVE);
	    }
		out.println(TAB + "public Integer save" + getEntityName() + "(" + getClassName() + " model) {");
		if(DAO == type) {
		    out.println(TAB + TAB + "return (Integer)getSqlMapClientTemplate().insert(getStatement(\"save" + getEntityName() +"\"),model);");
		}else {
		    out.println(TAB + TAB + "return getDaoFacade().getCommonDao().save" + getEntityName() + "(model);");
		}
		out.println(TAB + "}");
	}
	/**
	 * 输出dao删除接口
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:29:17
	 */
	private void writeDeleteDao(PrintWriter out) {
	    commont(out, DELETE);
		out.println(TAB + "public void delete" + getEntityName() + "ById (Integer id);");
	}
	/**
	 * 生成dao删除的实现类
	 * @Description:
	 * @param out
	 * @param type
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:29:31
	 */
	private void writeDeleteDaoImpl(PrintWriter out,int type) {
       if(DbSettings.implCommont) {
            commont(out, DELETE);
        }
		out.println(TAB + "public void delete" + getEntityName() + "ById (Integer id) {");
		if(DAO == type) {
		    out.println(TAB + TAB + "getSqlMapClientTemplate().delete(getStatement(\"delete" + getEntityName() + "ById\"),id);");
		}else {
		    out.println(TAB + TAB + "getDaoFacade().getCommonDao().delete" + getEntityName() + "ById(id);");
		}
		out.println(TAB + "}");
	}
	/**
	 * 生成dao修改的接口
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:29:42
	 */
	private void writeUpdateDao(PrintWriter out) {
	    commont(out, UPDATE);
		out.println(TAB + "public void update" + getEntityName() + "(" + getClassName() + " model);");
	}
	/**
	 * 生成DAO修改的实现类
	 * @Description:
	 * @param out
	 * @param type
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:29:51
	 */
	private void writeUpdateDaoImpl(PrintWriter out,int type) {
       if(DbSettings.implCommont) {
            commont(out, UPDATE);
        }	    
		out.println(TAB + "public void update" + getEntityName() + "(" + getClassName() + " model) {");
		if(DAO == type) {
		    out.println(TAB + TAB + "getSqlMapClientTemplate().update(getStatement(\"update" + getEntityName() + "\"),model);");
		}else {
		    out.println(TAB + TAB + "getDaoFacade().getCommonDao().update" + getEntityName() + "(model);");
		}
		out.println(TAB + "}");
	}
	/**
	 * 输出保存的接口
	 * @Description:
	 * @param out
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:30:15
	 */
	private void writeGetDao(PrintWriter out) {
	    commont(out, GET);
		out.println(TAB + "public List<" + getClassName() +"> get" + getEntityName() + "List(" + getClassName() + " scope);");
	}
	/**
	 * 输出保存的实现类
	 * @Description:
	 * @param out
	 * @param type
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:30:29
	 */
	private void writeGetDaoImpl(PrintWriter out,int type) {
       if(DbSettings.implCommont) {
            commont(out, GET);
        }   	    
		out.println(TAB + "public List<" + getClassName() + "> get" + getEntityName() + "List(" + getClassName() + " scope) {");
		if(DAO == type) {
		    //out.println(TAB + "return getSqlMapClientTemplate().queryForList(getStatement(\"get" + getEntityName() + "List\"),scope);");
		    out.println(TAB + TAB + "List<?> list = getSqlMapClientTemplate().queryForList(getStatement(\"get" + getEntityName() + "List\"),scope);");
		    out.println(TAB + TAB + "List<" + getClassName() + "> resultList = Arrays.asList(list.toArray(new " + getClassName() + "[0]));");
		    out.println(TAB + TAB + "return resultList;");
		}else {
		    out.println(TAB + TAB + "return getDaoFacade().getCommonDao().get" + getEntityName() + "List(scope);");
		}
		out.println(TAB + "}");
	}
	/*
	private void writeTestUnit(PrintWriter out) {
	    String EntityName = getEntityName();
	    out.println("@Test");
	    out.println("public void test" + EntityName + "(){");
	    out.println(TAB + "PrintStream out = System.out;");
        out.println(TAB + String.format("%sModel saveModel = DataUtil.getObj(%sModel.class);", EntityName, EntityName));
        out.println(TAB + String.format("Integer id = dao.save%s(saveModel);", EntityName));
        out.println(TAB + String.format("%sModel idScope = DataUtil.getIdScope(%sModel.class,id);", EntityName, EntityName));
        out.println(TAB + String.format("List<%sModel> list = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "if(!list.isEmpty()) {out.println(\"插入成功！\" + list.get(0));}else {out.println(\"插入或查询失败！\");return;}");
        out.println();
        out.println(TAB + String.format("%sModel scope = DataUtil.getScope(%sModel.class);", EntityName, EntityName));
        out.println(TAB + String.format("List<%sModel> queryList = dao.get%sList(scope);", EntityName, EntityName));
        out.println(TAB + "if(!queryList.isEmpty() && queryList.size() == 1) {out.println(\"查询成功...\" + queryList);}else {out.println(\"查询失败！\");return;}");
        out.println();
        out.println(TAB + String.format("%sModel updateModel = DataUtil.getUpdateModel(%sModel.class,id);", EntityName,EntityName));
        out.println(TAB + String.format("dao.update%s(updateModel);", EntityName));
        out.println(TAB + String.format("List<%sModel> updateList = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "out.println(\"修改成功...: \" + updateList);");
        out.println();
        out.println(TAB + String.format("dao.delete%sById(id);", EntityName));
        out.println(TAB + String.format("List<%sModel> listAfterDelete = dao.get%sList(idScope);", EntityName, EntityName));
        out.println(TAB + "if(listAfterDelete.isEmpty()) {System.out.println(\"删除成功！...\");}else {System.out.println(\"删除失败！\");}");
        out.println("}");
	}
	*/
	/**
	 * 获得字符串格式的当前的时间
	 * @Description:
	 * @return
	 * @return String
	 * @author luhao
	 * @since：2018年12月28日 下午6:30:40
	 */
	private String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	/**
	 * 获得项目名
	 * @Description:
	 * @return
	 * @return String
	 * @author luhao
	 * @since：2018年12月28日 下午6:30:50
	 */
    private String getAppNo() {
        String appNo = null;
        if(this.appNo.length() > 0) {
            return this.appNo;
        }
        if (tableName.contains(UNDER_LINE)) {
            appNo = tableName.split(UNDER_LINE)[0];
        } else {
            appNo = "APPNO";
        }
        return appNo.toUpperCase();
    }
    /**
     * 获得JavaBean包名
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:31:07
     */
    private String getEntityClassPath() {
        return "com.nstc." + getAppNo().toLowerCase() + ".model." + getEntityName() + PO;
    }
    /**
     * 获得JavaBean的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:31:36
     */
    public String getJaveBeanName() {
        return getEntityName() + PO + ".java";
    }
    /**
     * 获得DAO的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:31:48
     */
    public String getDaoName() {
        return getEntityName() + "Dao.java";
    }
    
    /**
     * 
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2019年1月10日 下午5:03:34
     */
    public String getSeqName() {
        String seqName = null;
        if(new Integer(0).equals(DbSettings.SEQ_DIR)) {
            seqName = "SEQ_" + getTableName();
        }else {
            seqName = getTableName() + "_SEQ";
        }
        return seqName;
    }
    
    /**
     * 获得XML的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:31:56
     */
    public String getXmlName() {
        String appNo = getAppNo() + UNDER_LINE;
        if(appNo.length() > 0) {
            return appNo + getEntityName() + ".xml";
        }
        return getEntityName() + "xml";
    }
    /**
     * 获得实体类的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:32:04
     */
    public String getEntityName() {
        String entityName = null;
        if (tableName.contains(UNDER_LINE)) {
            entityName = tableName.substring(tableName.indexOf(UNDER_LINE));
        } else {
            entityName = tableName;
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, entityName);
    }
    /**
     * 获得JavaBean不带.java的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:32:14
     */
    private String getClassName() {
        return getEntityName() + PO;
    }
	public Table(String tableName, String tableRemark, List<Line> lineList,String appNo) {
		super();
		if(lineList == null) {
		    throw new RuntimeException("无法创建Table对象，原因：无法获得属性信息。");
		}
		this.tableName = tableName.toUpperCase();
		this.tableRemart = tableRemark == null || "null".equals(tableRemark) ? "" : tableRemark; 
		this.lineList = lineList;
		this.appNo = appNo;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List<Line> getLineList() {
		return lineList;
	}
	public void setLineList(List<Line> lineList) {
		this.lineList = lineList;
	}
	public String getTableRemart() {
		return tableRemart;
	}
	public void setTableRemart(String tableRemart) {
		this.tableRemart = tableRemart;
	}
	@Override
	public Table clone() {
	    return new Table(tableName, tableRemart, lineList,appNo);
	}
	@Override
    public String toString() {
        return "Table [tableName=" + tableName + ", tableRemart=" + tableRemart + ", lineList=" + lineList + "]";
    }
	
}
class Line{
    private String columnName;
    private String paramName;
    private int columnType;
    private int decimalDigits;
    private String remark;
    private String columnTypeName;
    private final static String DATE = "DATE";
    private final static String TIMESTAMP = "TIMESTAMP";
    private final static String NUMBER = "NUMBER";
    private final static String VARCHAR2 = "VARCHAR2";
    private final static String PARENTHE= "(";
    
    public String paramLine() {
        StringBuffer sb = new StringBuffer("private ");
        paramName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        sb.append(getParamType()).append(paramName).append(";");
        return sb.toString();
    }
    public String commentLine() {
        StringBuffer sb = new StringBuffer("/** ");
        sb.append(remark).append(" */");
        return sb.toString();
    }

    public Line(String columnName, String paramName, int columnType, int decimalDigits, String remark) {
        super();
        this.columnName = columnName.toUpperCase();
        this.paramName = paramName;
        this.columnType = columnType;
        this.decimalDigits = decimalDigits;
        this.remark = remark == null || "null".equals(remark) ? "" : remark; 
    }
    
    public Line(String columnName, String type, String remark) {
        super();
        this.columnName = columnName.toUpperCase();
        this.remark = remark == null || "null".equals(remark) ? "" : remark; 
        this.columnTypeName = type.toUpperCase();
        this.paramName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, columnName);
        String columnType = type.trim().toUpperCase();
        if(DATE.equals(columnType)) {
            this.columnType = Types.DATE;
            this.decimalDigits = 0;
        }else if(TIMESTAMP.equals(columnType)) {
            this.columnType = Types.TIMESTAMP;
            this.decimalDigits = 0;
        }else if(columnType.startsWith(NUMBER)) {
            if(!columnType.contains(PARENTHE)) {
                this.columnType = Types.DECIMAL;
                this.decimalDigits = 0;
            }else {
                this.columnType = Types.DECIMAL;
                this.decimalDigits = 2;
            }
        }else if(columnType.startsWith(VARCHAR2)) {
            this.columnType = Types.VARCHAR;
            this.decimalDigits = 0;
        }else {
            throw new RuntimeException("未知类型！");
        }
    }
    public String getColumnName() {
        return columnName;
    }
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    public String getParamName() {
        return paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public int getColumnType() {
        return columnType;
    }

    public void setColumnType(int columnType) {
        this.columnType = columnType;
    }

    public int getDecimalDigits() {
        return decimalDigits;
    }

    public void setDecimalDigits(int decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public String getColumnTypeName() {
        return columnTypeName;
    }
    public void setColumnTypeName(String columnTypeName) {
        this.columnTypeName = columnTypeName;
    }
    public String getSharpName(){
        return "#" + this.paramName + "#";
    }
    public String getParamType() {
        String paramType = null;
        if (Types.DECIMAL == columnType) {
            if (decimalDigits == 0) {
                paramType = "Integer ";
            } else {
                paramType = "Double ";
            }
        } else if (Types.VARCHAR == columnType) {
            paramType = "String ";
        } else if (Types.TIMESTAMP == columnType || Types.DATE == columnType) {
            paramType = "Date ";
        } else {
            throw new RuntimeException("未知类型！");
        }
        return paramType;
    }
    @Override
    public String toString() {
        return "Line [columnName=" + columnName + ", paramName=" + paramName + ", columnType=" + columnType
                + ", decimalDigits=" + decimalDigits + ", remark=" + remark + "]";
    }
}