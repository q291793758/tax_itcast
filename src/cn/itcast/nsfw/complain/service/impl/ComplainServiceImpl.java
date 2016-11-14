package cn.itcast.nsfw.complain.service.impl;

import cn.itcast.core.service.impl.BaseServiceImpl;
import cn.itcast.core.util.QueryHelper;
import cn.itcast.nsfw.complain.dao.ComplainDao;
import cn.itcast.nsfw.complain.entity.Complain;
import cn.itcast.nsfw.complain.service.ComplainService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ComplainServiceImpl extends BaseServiceImpl<Complain> implements ComplainService {

    private ComplainDao complainDao;

    public void setComplainDao(ComplainDao complainDao) {
        super.setBaseDao(complainDao);
        this.complainDao = complainDao;
    }

    //月末处理本月以前的投诉
    @Override
    public void autoDeal() {
        System.out.println("开始清理本月以前的投诉信息,未受理的标记为失效"+new Date().toString());
        //1,查询本月之前的待受理的投诉列表
        Calendar calendar = Calendar.getInstance();
//        calendar.add(Calendar.MONTH,-1);  //直接设置日期到上个月
        calendar.set(Calendar.SECOND, 0);  //秒
        calendar.set(Calendar.MINUTE, 0);  //分
        calendar.set(Calendar.HOUR_OF_DAY, 0);     //小时
        calendar.set(Calendar.DAY_OF_MONTH, 1); //每月1号
        calendar.toString();
        QueryHelper queryHelper = new QueryHelper(Complain.class, "c");
        //投诉状态为未处理
        queryHelper.addQueryCondition("c.state = ?", Complain.COMPLAIN_STATE_UNDONE);
        //投诉的时间在本月1日之前
        queryHelper.addQueryCondition("c.compTime < ?", calendar.getTime());

        List<Complain> compList = complainDao.findObjects(queryHelper);
        if (compList != null && compList.size() > 0) {
            for (Complain complain : compList) {
                //2,将投诉设置状态为失效("2")
                complain.setState(Complain.COMPLAIN_STATE_INVALID);
                complainDao.update(complain);
            }
        }
        System.out.println("处理完毕!"+new Date().toString());
    }
}
