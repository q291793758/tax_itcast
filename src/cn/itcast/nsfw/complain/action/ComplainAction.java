package cn.itcast.nsfw.complain.action;

import cn.itcast.core.action.BaseAction;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.service.ComplainService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;

/**
 * The type Complain action.
 */
public class ComplainAction extends BaseAction {
    private ComplainService complainService;
    private Complain complain;
    private String startTime;
    private String endTime;
    private String[] dateformat= new String[]{"yyyy-MM-dd HH:mm"};

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
                queryHelper.addQueryCondition("c.compTime >= ?", DateUtils.parseDate(startTime,dateformat));
            }
            if (StringUtils.isNotBlank(endTime)) {   //查询结束时间之前的数据
                endTime = URLDecoder.decode(endTime, "UTF-8");
                queryHelper.addQueryCondition("c.compTime <= ?", DateUtils.parseDate(endTime,dateformat));
            }
            if (searchString != null) {
                searchString = URLDecoder.decode(searchString, "UTF-8");
                queryHelper.addQueryCondition("c.compTitle like ?", "%" + searchString + "%");
            }
            if (complain != null && StringUtils.isNotBlank(complain.getState())) {
                queryHelper.addQueryCondition("c.state=?", complain.getState());
            }
            //按照状态升序排序  0--未处理的在前边 依次 1 2
            queryHelper.addOrderByCondition("c.state",QueryHelper.ORDER_BY_ASC);
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


    /**
     * 跳转回复投诉页面
     *
     * @return the string
     */
    public String dealUI() {
        //加载状态集合
        ActionContext.getContext().getContextMap().put("complainStateMap", Complain.COMPLAIN_STATE_MAP);
        //根据传过来的id查找投诉信息
        if (StringUtils.isNotBlank(complain.getCompId())) {
            complain=complainService.findObjectById(complain.getCompId());
        }
        return "dealUI";
    }

    /**
     * 处理投诉信息
     *
     * @return the string
     */
    public String deal() {



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

    /**
     * Sets complain.
     *
     * @param complain the complain
     */
    public void setComplain(Complain complain) {
        this.complain = complain;
    }

    /**
     * Gets complain service.
     *
     * @return the complain service
     */
    public ComplainService getComplainService() {
        return complainService;
    }

    /**
     * Sets complain service.
     *
     * @param complainService the complain service
     */
    public void setComplainService(ComplainService complainService) {
        this.complainService = complainService;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
