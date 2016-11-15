package cn.itcast.nsfw.complain.dao;

import cn.itcast.core.dao.BaseDao;
import cn.itcast.nsfw.complain.entity.Complain;

import java.util.List;

public interface ComplainDao extends BaseDao<Complain> {

    //根据年份获取投诉按月统计
    List getAnnualStatisticsByYear(int year);
}
