package cn.itcast.nsfw.complain.action;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.entity.ComplainReply;
import cn.itcast.nsfw.complain.service.ComplainService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;

/**
 * The type Complain action.
 */
public class ComplainAction extends BaseAction {
    private ComplainService complainService;
    private Complain complain;
    private ComplainReply reply;
    //回显查询条件
    private String strState; //回显状态
    private String startTime;  //回显开始时间
    private String endTime;  //回显结束时间

    private String[] dateformat = new String[]{"yyyy-MM-dd HH:mm"};

    /**
     * 列出投诉信息列表
     *
     * @return the string
     */
//列表
    public String listUI() {
        try {
            //加载状态集合
            ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
            QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
            //like %?%耗性能,放后边,其他能过滤更多条件的放前边
            if (StringUtils.isNotBlank(startTime)) {  //查询开始时间之后的数据
                startTime = URLDecoder.decode(startTime, "UTF-8");
                queryHelper.addQueryCondition("c.compTime >= ?", DateUtils.parseDate(startTime, dateformat));
            }
            if (StringUtils.isNotBlank(endTime)) {   //查询结束时间之前的数据
                endTime = URLDecoder.decode(endTime, "UTF-8");
                queryHelper.addQueryCondition("c.compTime <= ?", DateUtils.parseDate(endTime, dateformat));
            }
            if (searchString != null) {
                searchString = URLDecoder.decode(searchString, "UTF-8");
                queryHelper.addQueryCondition("c.compTitle like ?", "%" + searchString + "%");
            }
            if (StringUtils.isNotBlank(strState)){
                queryHelper.addQueryCondition("c.state=?", strState);
            }
            //按照状态升序排序  0--未处理的在前边 依次 1 2
            queryHelper.addOrderByCondition("c.state", QueryHelper.ORDER_BY_ASC);
            //按照时间升序排序  时间越久的排前边,方便处理
            queryHelper.addOrderByCondition("c.compTime", QueryHelper.ORDER_BY_ASC);
            pageResult = complainService.getPageResult(queryHelper, getPageNo(), getPageSize());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "listUI";
    }

    //回复投诉信息
    public String dealUI() {
        //加载状态集合
        ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
        //根据传过来的id查找投诉信息
        if (StringUtils.isNotBlank(complain.getCompId())) {
            complain = complainService.findObjectById(complain.getCompId());
        }
        return "dealUI";
    }

    /**
     * 处理投诉信息
     *
     * @return the string
     */
    public String deal() {
        if (complain != null && StringUtils.isNotBlank(reply.getReplyContent())) {
            //1,更新投诉状态 为已受理
            Complain tem = complainService.findObjectById(complain.getCompId());
            if (!Complain.COMPLAIN_STATE_DONE.equals(tem.getState())) {
                tem.setState(Complain.COMPLAIN_STATE_DONE);
            }
            //2,保存投诉回复信息
            if (reply != null) {
                reply.setReplyTime(new Timestamp(new Date().getTime()));
                reply.setComplain(tem);
            }
            tem.getComplainReplies().add(reply);

            //3,保存投诉信息
            complainService.update(tem);
        }

        return "list";
    }


    /**
     * Gets complain.
     *
     * @return the complain
     */
//    =====================get set方法=============================
    public Complain getComplain() {
        return complain;
    }


    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    public ComplainService getComplainService() {
        return complainService;
    }

    public void setComplainService(ComplainService complainService) {
        this.complainService = complainService;
    }

    public String getStartTime() {
        return startTime;
    }


    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }


    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ComplainReply getReply() {
        return reply;
    }

    public void setReply(ComplainReply reply) {
        this.reply = reply;
    }

    public String getStrState() {
        return strState;
    }

    public void setStrState(String strState) {
        this.strState = strState;
    }
}
