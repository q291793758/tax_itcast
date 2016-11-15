package cn.itcast.nsfw.complain.dao.impl;

import cn.itcast.core.dao.impl.BaseDaoImpl;
import cn.itcast.nsfw.complain.dao.ComplainDao;
import cn.itcast.nsfw.complain.entity.Complain;
import org.hibernate.SQLQuery;

import java.util.List;

public class ComplainDaoImpl extends BaseDaoImpl<Complain> implements ComplainDao {

    //根据年份获取投诉统计数(按月分组)
    @Override
    public List getAnnualStatisticsByYear(int year) {
        String sql = "SELECT t.imonth,c.c2 FROM tmonth AS t LEFT JOIN (SELECT month(comp_time) c1, count(comp_id) c2 FROM complain WHERE year(comp_time) = ? GROUP BY c1) AS c ON t.imonth = c.c1 ORDER BY t.imonth ASC ;";
        SQLQuery sqlQuery = getSession().createSQLQuery(sql);
        sqlQuery.setParameter(0, year);

        return sqlQuery.list();
    }
}
