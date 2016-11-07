package cn.itcast.nsfw.info.action;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.utils.QueryHelper;
import cn.itcast.nsfw.info.entity.Info;
import cn.itcast.nsfw.info.service.InfoService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class InfoAction extends BaseAction {

    private InfoService infoService;
    private List<Info> infoList;
    private Info info;

    //列表页面
    public String listUI() throws Exception{
        try {
            //加载分类集合
            ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
            QueryHelper qh = new QueryHelper(Info.class, "i");
            if (info != null) {
                if (StringUtils.isNotBlank(info.getTitle())) {
                    qh.addQueryCondition("i.title like ?","%"+info.getTitle()+"%");
                }
                qh.addQueryCondition("i.state = ?","1");
            }
            qh.addOrderByCondition("i.createTime",qh.ORDER_BY_DESC);
            infoList=infoService.findObjects(qh);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return "listUI";
    }

    //跳转到新增页面
    public String addUI(){
        //加载分类集合
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        //在进入编辑页面的时候传入当前创建时间
        info=new Info();
        info.setCreateTime(new Timestamp(new Date().getTime()));
        return "addUI";
    }
    //保存新增
    public String add(){
        if(info!=null ){
            infoService.save(info);
        }
        return "list";
    }
    //跳转到编辑界面
    public String editUI(){
        //加载分类集合
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        if(info!=null && info.getInfoId()!=null){
            info=infoService.findObjectById(info.getInfoId());
        }
        return "editUI";
    }
    //保存编辑
    public String edit(){
        infoService.update(info);
        return "list";
    }
    //删除
    public String delete(){
        if(info!=null && info.getInfoId()!=null){
            infoService.delete(info.getInfoId());
        }
        return "list";
    }
    //批量删除
    public String deleteSelected(){
        if(selectedRow!=null){
            for(String id:selectedRow){
                infoService.delete(id);
            }
        }
        return "list";
    }
    //信息发布
    public void publicInfo(){
        try {
            if (info != null) {
                //1.更新信息状态
                Info tem = infoService.findObjectById(info.getInfoId());
                tem.setState(info.getState());
                infoService.update(tem);


                //2.输出更新结果
                HttpServletResponse response = ServletActionContext
                        .getResponse();
                response.setContentType("text/html");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write("更新状态成功".getBytes("utf-8"));
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public InfoService getInfoService() {
        return infoService;
    }

    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }

    public List<Info> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<Info> infoList) {
        this.infoList = infoList;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
