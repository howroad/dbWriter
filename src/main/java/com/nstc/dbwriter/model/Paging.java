package com.nstc.dbwriter.model;
/**
 * 
 * <p>Title: Paging.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since：2019年2月18日 下午4:31:20
 *
 */
public class Paging {

    public static final int PAGE_SIZE = 1000;
    /**
     * 每页记录数
     */
    private int pageSize = PAGE_SIZE;
    
    /**
     * 总记录数
     */
    private int totalRow;
    
    /**
     * 当前页
     */
    private int currentPage = 1;

    /**
     * @return 当前页的开始记录
     */
    public int getStartRow(){
        int first = 0;
        if(currentPage<1) {
            return first;
        }
        first = (currentPage-1)*pageSize;
        return first;
    }
    /**
     * @return 当前页的结束记录
     */
    public int getEndRow(){
        int end = 0;
        end = getStartRow() + pageSize;
        return end;
    }
    /**
     * @return 总页数
     */
    public int getTotalPage(){
        if(totalRow<=0) {
            return 1;
        }
        return (totalRow-1)/pageSize+1;
    }
    
    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if(currentPage<1) {
            this.currentPage = 1;
        }
        this.currentPage = currentPage;
    }
    
    /**
     *校正当前页，如果大于总页数则返回总页数
     */
    public int checkCurrentPage(){
        int totalPage = getTotalPage();
        if(currentPage>totalPage) {
            currentPage = totalPage;
        }
        return currentPage;
    }
        
}
