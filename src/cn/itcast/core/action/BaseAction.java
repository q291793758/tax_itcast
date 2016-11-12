package cn.itcast.core.action;

import cn.itcast.core.util.PageResult;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class BaseAction extends ActionSupport {

    protected List<String> selectedRow;   //操作多个id [id1,id2]
    protected String searchString;         //搜索关键字

    //分页对象
    protected PageResult pageResult;
    //页号
    protected int pageNo;
    //页大小
    protected int pageSize;

    //默认每页页大小
    protected static int DEFAULT_PAGE_SIZE=4;


    public List<String> getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(List<String> selectedRow) {
        this.selectedRow = selectedRow;
    }
    public PageResult getPageResult() {
        return pageResult;
    }

    public void setPageResult(PageResult pageResult) {
        this.pageResult = pageResult;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        //给的默认的分页大小
        if(pageSize < 1) pageSize = DEFAULT_PAGE_SIZE;
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
