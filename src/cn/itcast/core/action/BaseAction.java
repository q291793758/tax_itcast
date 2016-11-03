package cn.itcast.core.action;

import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

public class BaseAction extends ActionSupport {

    protected List<String> selectedRow;   //操作多个id [id1,id2]




    public List<String> getSelectedRow() {
        return selectedRow;
    }

    public void setSelectedRow(List<String> selectedRow) {
        this.selectedRow = selectedRow;
    }

}
