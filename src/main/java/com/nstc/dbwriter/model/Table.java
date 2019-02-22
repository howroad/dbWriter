package com.nstc.dbwriter.model;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import com.google.common.base.CaseFormat;
import com.nstc.dbwriter.config.CommonSettings;
import com.nstc.dbwriter.config.TableContans;

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
public class Table implements MapContent{
    
	private String tableName;
	private String tableRemart;
	List<Line> lineList;
	private Map<String, String> map = new HashMap<String, String>();
	private List<MyParam> paramList;

    public Table(String tableName, String tableRemark, List<Line> lineList,List<MyParam> paramList) {
        super();
        if (lineList == null) {
            throw new RuntimeException("无法创建Table对象，原因：无法获得属性信息。");
        }
        this.tableName = tableName.toUpperCase();
        this.tableRemart = tableRemark == null || "null".equals(tableRemark) ? "" : tableRemark;
        this.lineList = lineList;
        this.paramList = paramList;
        
    }
    private void initMap() {
        if(hasDateType()) {
            map.put("import", "import java.util.Date;");
        }
        map.put("entityName", getEntityName());
        map.put("entityNameLow", getEntityName().toLowerCase());
        map.put("remark", this.tableRemart); 
        map.put("now", getNow());
        map.put("tableName", getTableName());
        map.put("pkName",paramList.get(0).getParamName());
        map.put("pkColumnName",paramList.get(0).getColumnName());
        map.put("seqName", getSeqName());
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
                if(CommonSettings.dealSEQ && first) {
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
        out.println(TableContans.TAB + "CNT INTEGER;");
        out.println("BEGIN");
        out.println(TableContans.TAB + "SELECT COUNT(0) INTO CNT FROM USER_SEQUENCES");
        out.println(TableContans.TAB + "WHERE SEQUENCE_NAME = UPPER('" + seqName + "');");
        out.println(TableContans.TAB + "IF CNT = 0 THEN");
        out.println(TableContans.TAB + "EXECUTE IMMEDIATE 'CREATE SEQUENCE " + seqName);
        out.println(TableContans.TABTAB + "MINVALUE 1");
        out.println(TableContans.TABTAB + "MAXVALUE 9999999999999999999999999");
        out.println(TableContans.TABTAB + "START WITH 1");
        out.println(TableContans.TABTAB + "INCREMENT BY 1");
        out.println(TableContans.TABTAB + "CACHE 20';");
        out.println(TableContans.TAB + "END IF;");
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
        out.println(TableContans.TAB + "CNT INTEGER;");
        out.println("BEGIN");
        out.println(TableContans.TAB + "SELECT COUNT(0) INTO CNT FROM USER_CONSTRAINTS");
        out.println(TableContans.TAB + "WHERE CONSTRAINT_NAME = UPPER('" + primaryKeyName + "');");
        out.println(TableContans.TAB + "IF CNT = 0 THEN");
        out.println(TableContans.TAB + "EXECUTE IMMEDIATE 'ALTER TABLE " + tableName + " ADD CONSTRAINT " + primaryKeyName + " PRIMARY KEY(" + columnName + ")';");
        out.println(TableContans.TAB + "END IF;");
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
	    out.println(TableContans.TAB + "CNT INTEGER;");
	    out.println("BEGIN");
	    out.println(TableContans.TAB + "SELECT COUNT(0) INTO CNT FROM USER_ALL_TABLES");
	    out.println(TableContans.TAB + "WHERE TABLE_NAME = UPPER('" + tableName + "');");
	    out.println(TableContans.TAB + "IF CNT = 0 THEN");
	    out.println(TableContans.TAB + "EXECUTE IMMEDIATE 'CREATE TABLE " + tableName + "(");
	    for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            Line line = iterator.next();
            if(iterator.hasNext()) {
                out.println(TableContans.TABTAB + line.getColumnName() + " " + line.getColumnTypeName() + ",");
            }else {
                out.println(TableContans.TABTAB + line.getColumnName() + " " + line.getColumnTypeName() + ")';");
            }
        }
	    out.println(TableContans.TAB + "END IF;");
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
        out.println("package com.nstc." + getAppNoLower() + ".model;");
        if(hasDateType()) {
            out.println("import java.util.Date;"); 
        }
        out.println("/** " + tableRemart + " */");
        out.println("public class " + getClassName() +" {");
        for(ListIterator<Line> iterator = lineList.listIterator();iterator.hasNext();) {
            Line line = iterator.next();
            out.println(TableContans.TAB + line.commentLine());
            out.println(TableContans.TAB + line.paramLine());
        }
        out.println();
        for(ListIterator<Line> iterator = lineList.listIterator();iterator.hasNext();) {
            Line line = iterator.next();
            String paramName = line.getParamName();
            String upName = paramName.substring(0,1).toUpperCase()+paramName.substring(1);
            out.println(TableContans.TAB + "public void set" + upName + "(" + line.getParamType() + line.getParamName() + "){");
            out.println(TableContans.TABTAB + "this." + line.getParamName() + " = " + line.getParamName() + ";");
            out.println(TableContans.TAB + "}");
            out.println();
            out.println(TableContans.TAB + "public " + line.getParamType() + "get" + upName + "(){");
            out.println(TableContans.TABTAB + "return this." + line.getParamName() + ";");
            out.println(TableContans.TAB + "}");
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
        writeSaveDaoImpl(out,TableContans.DAO);
        out.println();
        writeDeleteDaoImpl(out,TableContans.DAO);
        out.println();
        writeUpdateDaoImpl(out,TableContans.DAO);
        out.println();
        writeGetDaoImpl(out,TableContans.DAO);
        out.println();
    }
    
    public void writeServiceImpl(PrintWriter out) {
        writeSaveDaoImpl(out,TableContans.SERVICE);
        out.println();
        writeDeleteDaoImpl(out,TableContans.SERVICE);
        out.println();
        writeUpdateDaoImpl(out,TableContans.SERVICE);
        out.println();
        writeGetDaoImpl(out,TableContans.SERVICE);
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
	    
		out.println(TableContans.TAB + "<!-- 新增" + tableRemart + " -->");
		out.println(TableContans.TAB + "<insert id=\"save" + getEntityName() + "\" parameterClass=\""+ getEntityClassPath() + "\">");
		if(CommonSettings.dealSEQ) {
		    out.println(TableContans.TAB + TableContans.TAB + "<selectKey resultClass=\"java.lang.Integer\" keyProperty=\"" + lineList.get(0).getParamName() + "\">");
		    out.println(TableContans.TAB + TableContans.TABTAB + "SELECT " + getSeqName() + ".NEXTVAL AS " + lineList.get(0).getParamName() + " FROM DUAL");
		    out.println(TableContans.TAB + TableContans.TAB + "</selectKey>");
		}
		out.println(TableContans.TAB + TableContans.TAB + "INSERT INTO " + tableName + " (");
		for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
			Line line = (Line) iterator.next();
            if(!iterator.hasNext()) {
            	out.println(TableContans.TAB + TableContans.TABTAB + line.getColumnName());
            }else {
            	out.println(TableContans.TAB + TableContans.TABTAB + line.getColumnName() + ",");
            }
		}
		out.println(TableContans.TAB + TableContans.TAB + ") VALUES(");
		for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            boolean first = false;
            first = !iterator.hasPrevious();
			Line line = (Line) iterator.next();
            if(!iterator.hasNext()) {
            	out.println(TableContans.TAB + TableContans.TABTAB + line.getSharpName());
            }else {
                if(first) {
                    //out.println(TABTAB + seqNext + ",");
                    out.println(TableContans.TAB + TableContans.TABTAB + line.getSharpName() + ",");
                }else {
                    out.println(TableContans.TAB + TableContans.TABTAB + line.getSharpName() + ",");
                }
            }
		}
		out.println(TableContans.TAB + TableContans.TAB + ")");
		out.println(TableContans.TAB + "</insert>");
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
		out.println(TableContans.TAB + "<!-- 删除" + tableRemart + " -->");
		out.println(TableContans.TAB + "<delete id=\"delete" + getEntityName() + "ById\" parameterClass=\"java.lang.Integer\">");
		out.println(TableContans.TAB + TableContans.TAB + "delete " + tableName +" WHERE " + lineList.get(0).getColumnName() + " = " + lineList.get(0).getSharpName());
		out.println(TableContans.TAB + "</delete>");		
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
		out.println(TableContans.TAB + "<!-- 修改" + tableRemart + " -->");
		out.println(TableContans.TAB + "<update id=\"update" + getEntityName() + "\" parameterClass=\"" + getEntityClassPath() + "\">");
		out.println(TableContans.TAB + TableContans.TAB + "UPDATE " + tableName + " SET");
		for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
		    boolean first = false;
		    first = !iterator.hasPrevious();
			Line line = (Line) iterator.next();
			if(first) {
			    out.println(TableContans.TAB + TableContans.TAB + line.getColumnName() + " = " + line.getSharpName());
			}else {
			    out.println(TableContans.TAB + TableContans.TAB + "<isNotEmpty prepend=\",\" property=\"" + line.getParamName() + "\">");
			    out.println(TableContans.TAB + TableContans.TABTAB + line.getColumnName() + " = " + line.getSharpName());
			    out.println(TableContans.TAB + TableContans.TAB + "</isNotEmpty>");
			}
		}
		out.println(TableContans.TAB + TableContans.TAB + "WHERE " + lineList.get(0).getColumnName() + " = " + lineList.get(0).getSharpName());
		out.println(TableContans.TAB + "</update>");
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
		out.println(TableContans.TAB + "<!-- 查询" + tableRemart + " -->");
		out.println(TableContans.TAB + "<select id=\""+"get"+getEntityName()+"List\" parameterClass=\"" + getEntityClassPath() + "\" resultClass=\"" + getEntityClassPath() + "\">");
		out.println(TableContans.TAB + TableContans.TAB + "SELECT");
		for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            Line line = iterator.next();
            String printLine = "T." + line.getColumnName() + " AS " + "\"" + line.getParamName() + "\",";
            if(!iterator.hasNext()) {
                printLine = printLine.replace(",", "");
            }
            out.println(TableContans.TAB + TableContans.TABTAB + printLine);
		}
		out.println(TableContans.TAB + TableContans.TAB + "FROM " + tableName + " T WHERE 1 = 1");
        for (ListIterator<Line> iterator = lineList.listIterator(); iterator.hasNext();) {
            Line line = iterator.next();
            out.println(TableContans.TAB + TableContans.TAB + "<isNotEmpty property=\"" + line.getParamName() + "\" prepend=\"and\">");
            String printLine = "T." + line.getColumnName() + " = " + line.getSharpName();
            out.println(TableContans.TAB + TableContans.TABTAB + printLine);
            out.println(TableContans.TAB + TableContans.TAB + "</isNotEmpty>");
        }
        out.println(TableContans.TAB + "</select>");
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
        String comName = TableContans.COM_NAME[type];
        String paramName = TableContans.COM_PARAM[type];
        String paramType = TableContans.COM_PARAM_TYPE[type];
        out.println(TableContans.TAB + "/**");
        out.println(TableContans.TAB + "* " + comName + tableRemart);
        out.println(TableContans.TAB + "* @param " + paramName + " " + tableRemart + paramType);
        out.println(TableContans.TAB + "* @author luhao");
        if(TableContans.GET == type) {
            out.println(TableContans.TAB + "* @return " + tableRemart + "集合");
        }else if(TableContans.SAVE == type){
            out.println(TableContans.TAB + "* @return " + tableRemart + "主键");
        }
        out.println(TableContans.TAB + "* @since " + getNow());
        out.println(TableContans.TAB + "*/");
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
	    commont(out, TableContans.SAVE);
		out.println(TableContans.TAB + "public Integer save" + getEntityName() + "(" + getClassName() +" model);");
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
	    if(CommonSettings.implCommont) {
	        commont(out, TableContans.SAVE);
	    }
		out.println(TableContans.TAB + "public Integer save" + getEntityName() + "(" + getClassName() + " model) {");
		if(TableContans.DAO == type) {
		    out.println(TableContans.TAB + TableContans.TAB + "return (Integer)getSqlMapClientTemplate().insert(getStatement(\"save" + getEntityName() +"\"),model);");
		}else {
		    out.println(TableContans.TAB + TableContans.TAB + "return getDaoFacade().getCommonDao().save" + getEntityName() + "(model);");
		}
		out.println(TableContans.TAB + "}");
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
	    commont(out, TableContans.DELETE);
		out.println(TableContans.TAB + "public void delete" + getEntityName() + "ById (Integer id);");
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
       if(CommonSettings.implCommont) {
            commont(out, TableContans.DELETE);
        }
		out.println(TableContans.TAB + "public void delete" + getEntityName() + "ById (Integer id) {");
		if(TableContans.DAO == type) {
		    out.println(TableContans.TAB + TableContans.TAB + "getSqlMapClientTemplate().delete(getStatement(\"delete" + getEntityName() + "ById\"),id);");
		}else {
		    out.println(TableContans.TAB + TableContans.TAB + "getDaoFacade().getCommonDao().delete" + getEntityName() + "ById(id);");
		}
		out.println(TableContans.TAB + "}");
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
	    commont(out, TableContans.UPDATE);
		out.println(TableContans.TAB + "public void update" + getEntityName() + "(" + getClassName() + " model);");
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
       if(CommonSettings.implCommont) {
            commont(out, TableContans.UPDATE);
        }	    
		out.println(TableContans.TAB + "public void update" + getEntityName() + "(" + getClassName() + " model) {");
		if(TableContans.DAO == type) {
		    out.println(TableContans.TAB + TableContans.TAB + "getSqlMapClientTemplate().update(getStatement(\"update" + getEntityName() + "\"),model);");
		}else {
		    out.println(TableContans.TAB + TableContans.TAB + "getDaoFacade().getCommonDao().update" + getEntityName() + "(model);");
		}
		out.println(TableContans.TAB + "}");
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
	    commont(out, TableContans.GET);
		out.println(TableContans.TAB + "public List<" + getClassName() +"> get" + getEntityName() + "List(" + getClassName() + " scope);");
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
       if(CommonSettings.implCommont) {
            commont(out, TableContans.GET);
        }   	    
		out.println(TableContans.TAB + "public List<" + getClassName() + "> get" + getEntityName() + "List(" + getClassName() + " scope) {");
		if(TableContans.DAO == type) {
		    //out.println(TAB + "return getSqlMapClientTemplate().queryForList(getStatement(\"get" + getEntityName() + "List\"),scope);");
		    out.println(TableContans.TAB + TableContans.TAB + "List<?> list = getSqlMapClientTemplate().queryForList(getStatement(\"get" + getEntityName() + "List\"),scope);");
		    out.println(TableContans.TAB + TableContans.TAB + "List<" + getClassName() + "> resultList = Arrays.asList(list.toArray(new " + getClassName() + "[0]));");
		    out.println(TableContans.TAB + TableContans.TAB + "return resultList;");
		}else {
		    out.println(TableContans.TAB + TableContans.TAB + "return getDaoFacade().getCommonDao().get" + getEntityName() + "List(scope);");
		}
		out.println(TableContans.TAB + "}");
	}
	/**
	 * 获得字符串格式的当前的时间
	 * @Description:
	 * @return String
	 * @author luhao
	 * @since：2018年12月28日 下午6:30:40
	 */
	private String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	/**
	 * 获得大写项目名
	 * @author luhao
	 * @since：2018年12月28日 下午6:30:50
	 */
    private String getAppNoUpper() {
        String appNo = null;
        if(CommonSettings.appNo.length() > 0) {
            appNo =  CommonSettings.appNo;
        }else if (tableName.contains(TableContans.UNDER_LINE)) {
            appNo = tableName.split(TableContans.UNDER_LINE)[0];
        } else {
            appNo = "APPNO";
        }
        return appNo.toUpperCase();
    }
    /**
     * 获得小写项目名
     * @author luhao
     * @since：2019年2月1日 下午12:31:35
     */
    private String getAppNoLower() {
        String appNo = null;
        if(CommonSettings.appNo.length() > 0) {
            appNo =  CommonSettings.appNo;
        }else if (tableName.contains(TableContans.UNDER_LINE)) {
            appNo = tableName.split(TableContans.UNDER_LINE)[0];
        } else {
            appNo = "APPNO";
        }
        return appNo.toLowerCase();
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
        return "com.nstc." + getAppNoLower() + ".model." + getEntityName() + TableContans.PO;
    }
    /**
     * 获得JavaBean的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:31:36
     */
    public String getJaveBeanFileName() {
        return getEntityName() + TableContans.PO + ".java";
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
        if(new Integer(0).equals(CommonSettings.SEQ_DIR)) {
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
        String appNo = getAppNoUpper() + TableContans.UNDER_LINE;
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
        if (tableName.contains(TableContans.UNDER_LINE)) {
            entityName = tableName.substring(tableName.indexOf(TableContans.UNDER_LINE));
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
        return getEntityName() + TableContans.PO;
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
	    return new Table(tableName, tableRemart, lineList,paramList);
	}
	@Override
    public String toString() {
        return "Table [tableName=" + tableName + ", tableRemart=" + tableRemart + ", lineList=" + lineList + "]";
    }
	@Override
    public Map<String, String> getMap() {
	    initMap();
        return map;
    }

    public List<MyParam> getParamList() {
        return paramList;
    }

    public void setParamList(List<MyParam> paramList) {
        this.paramList = paramList;
    }
	
}
