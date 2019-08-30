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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private Map<String, String> map = new HashMap<String, String>();
	private List<MyParam> paramList;

    public Table(String tableName, String tableRemark, List<MyParam> paramList) {
        super();
        this.tableName = tableName.toUpperCase();
        this.tableRemart = tableRemark == null || "null".equals(tableRemark) ? "" : tableRemark;
        this.paramList = paramList;
        
    }
    private void initMap() {
        if(hasDateType()) {
            map.put("import", "import java.util.Date;");
        }else {
            map.put("import", "");
        }
        map.put("entityName", getEntityName());
        map.put("entityNameLow", getEntityName().toLowerCase());
        map.put("remark", this.tableRemart); 
        map.put("now", getNow());
        map.put("tableName", getTableName());
        map.put("pkName",paramList.get(0).getParamName());
        map.put("pkColumnName",paramList.get(0).getColumnName());
        map.put("seqName", getSeqName());
        map.put("pkNameUp",CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, paramList.get(0).getParamName()));
        map.put("tableNo", tableNo());
        
    }
    
    //获得缩写
    public String tableNo() {
        String tableName = getTableName();
        String reg = "[^\\_]+";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(tableName);
        StringBuffer buffer = new StringBuffer();
        while(matcher.find()) {
            String str = matcher.group();
            buffer.append(str.charAt(0));
        }
        return buffer.toString();
    }
    
    
	/**
	 * 是否有时间类型
	 * @Description:
	 * @return boolean
	 * @author luhao
	 * @since：2019年1月30日 下午4:44:48
	 */
	private boolean hasDateType() {
	    if(paramList == null || paramList.isEmpty()) {
	        throw new RuntimeException("无列表信息！");
	    }
       for (MyParam myParam : paramList) {
            if(myParam == null) {
                throw new RuntimeException("Param中无信息！");
            }
            if(myParam.getType().getValue() == Types.DATE || myParam.getType().getValue() == Types.TIMESTAMP) {
                return true;
            }
        }
	    return false;
	}
	/**
	 * 获得插入语句的字段，例如to_date('','')
	 * @param obj
	 * @param line
	 * @return String
	 * @author luhao
	 * @since：2018年12月26日 下午5:17:29
	 */
	private String getInsertValue(Object obj,MyParam param) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    int columnType = param.getType().getValue();
	    String result = null;
	    if(obj == null) {
	        result = "null";
	    }else if (Types.DECIMAL == columnType) {
	        result = String.valueOf(obj);
        } else if (Types.VARCHAR == columnType || Types.CHAR == columnType) {
            String str = String.valueOf(obj);
            str = str.replaceAll("'", "''");
            str = str.replace("&", "' || '&' || '");
            result = "'" + str + "'";
        } else if (Types.TIMESTAMP == columnType || Types.DATE == columnType) {
            if(obj.getClass() == oracle.sql.TIMESTAMP.class){
                TIMESTAMP time = (TIMESTAMP) obj;
                Date dateTime = null;
                try {
                    dateTime = time.dateValue();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
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
     * @param out
     * @param dataList
     * @return void
     * @author luhao
     * @since：2018年12月28日 下午6:23:39
     */
    public void writeDate(PrintWriter out,List<List<Object>> dataList,String[] primaryColUpKeys) {
        String[] primaryKeyValues = new String[primaryColUpKeys.length];
        for (List<Object> list : dataList) {
            out.print("INSERT INTO " + tableName + "(");
            for (ListIterator<MyParam> iterator = paramList.listIterator(); iterator.hasNext();) {
                MyParam param = iterator.next();
                String columnName = param.getColumnName();
                if (iterator.hasNext()) {
                    out.print(columnName + ",");
                } else {
                    out.println(columnName + ") ");
                }
            }
            out.print("SELECT ");
            int index = 0;
            for (ListIterator<Object> iterator = list.listIterator(); iterator.hasNext();) {
                boolean last = false;
                Object object = iterator.next();
                MyParam param = paramList.get(index++);
                String columnName = param.getColumnName();
                String value = getInsertValue(object, param);
                for (int i = 0; i < primaryKeyValues.length; i++) {
                    if(columnName.equals(primaryColUpKeys[i])) {
                        primaryKeyValues[i] = value;
                    }
                }
                last = !iterator.hasNext();
                if(last) {
                    out.print(value);
                }else {
                    out.print(value + ",");
                }
            }
            StringBuffer whereStr = new StringBuffer(128);
            for (int i = 0; i < primaryColUpKeys.length; i++) {
                String primaryColUpKey = primaryColUpKeys[i];
                String primaryKeyValue = primaryKeyValues[i];
                if(i > 0) {
                    whereStr.append(" AND ");
                }
                whereStr.append(primaryColUpKey + " = " + primaryKeyValue);
            }
            out.println(" FROM DUAL ");
            out.println("WHERE NOT EXISTS (SELECT 1 FROM " + tableName + " WHERE " + whereStr + ")");
            out.println("/");
        }
       
    }
	
	/**
	 * 根据集合集合内容生成数据库插入语句
	 * @param out
	 * @param dataList
	 * @return void
	 * @author luhao
	 * @since：2018年12月28日 下午6:23:39
	 */
	public void writeDate(PrintWriter out,List<List<Object>> dataList) {
	    String seqNext = getSeqName() + ".NEXTVAL";
	    //String primaryKeyName = paramList.get(0).getParamName();
	    String primaryKeyColumnName = paramList.get(0).getColumnName();
	    for (List<Object> list : dataList) {
	        String primaryKeyValue = String.valueOf(list.get(0));
	        out.print("INSERT INTO " + tableName + "(");
	        for (ListIterator<MyParam> iterator = paramList.listIterator(); iterator.hasNext();) {
	            MyParam param = iterator.next();
	            if (iterator.hasNext()) {
	                out.print(param.getColumnName() + ",");
	            } else {
	                out.println(param.getColumnName() + ") ");
	            }
	        }
	        out.print("SELECT ");
	        int index = 0;
	        for (ListIterator<Object> iterator = list.listIterator(); iterator.hasNext();) {
	            boolean first = false;
	            boolean last = false;
	            first = !iterator.hasPrevious();
                Object object = iterator.next();
                String value = getInsertValue(object, paramList.get(index++));
                last = !iterator.hasNext();
                boolean use = false;//此处不应有序列
                if(CommonSettings.dealSEQ && first && use) {
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
	        out.println("WHERE NOT EXISTS (SELECT 1 FROM " + tableName + " WHERE " + primaryKeyColumnName + " = '" + primaryKeyValue + "')");
	        out.println("/");
        }
       
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
     * 获得实体类的名称
     * @Description:
     * @return
     * @return String
     * @author luhao
     * @since：2018年12月28日 下午6:32:04
     */
    public String getEntityName() {
        String entityName = null;
        if(tableName.toUpperCase().startsWith("UM_")) {
            entityName = tableName.substring(tableName.indexOf(TableContans.UNDER_LINE) + 1);
            entityName = "UM_" + entityName.substring(entityName.indexOf(TableContans.UNDER_LINE) + 1);
        }else if (tableName.contains(TableContans.UNDER_LINE)) {
            entityName = tableName.substring(tableName.indexOf(TableContans.UNDER_LINE));
        } else {
            entityName = tableName;
        }
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, entityName);
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
    
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTableRemart() {
		return tableRemart;
	}
	public void setTableRemart(String tableRemart) {
		this.tableRemart = tableRemart;
	}
	
    @Override
	public Table clone() {
	    return new Table(tableName, tableRemart,paramList);
	}
	@Override
    public String toString() {
        return "Table [tableName=" + tableName + ", tableRemart=" + tableRemart + "]";
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
