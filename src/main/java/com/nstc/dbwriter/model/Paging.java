package com.nstc.dbwriter.model;
/**
 * 
 * <p>Title: Paging.java</p>
 *
 * <p>Description: </p>
 *
 * @author luhao
 * 
 * @since��2019��2��18�� ����4:31:20
 *
 */
public class Paging {

    public static final int PAGE_SIZE = 1000;
    /**
     * ÿҳ��¼��
     */
    private int pageSize = PAGE_SIZE;
    
    /**
     * �ܼ�¼��
     */
    private int totalRow;
    
    /**
     * ��ǰҳ
     */
    private int currentPage = 1;

    /**
     * @return ��ǰҳ�Ŀ�ʼ��¼
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
     * @return ��ǰҳ�Ľ�����¼
     */
    public int getEndRow(){
        int end = 0;
        end = getStartRow() + pageSize;
        return end;
    }
    /**
     * @return ��ҳ��
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
     *У����ǰҳ�����������ҳ���򷵻���ҳ��
     */
    public int checkCurrentPage(){
        int totalPage = getTotalPage();
        if(currentPage>totalPage) {
            currentPage = totalPage;
        }
        return currentPage;
    }
        
}
