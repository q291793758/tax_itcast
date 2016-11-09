package cn.itcast.core.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 查询工具类
 */
public class QueryHelper {

    /**
     *  String hql="FROM Info i WHERE i.title=? AND i.state=? ORDER BY i.createTime ?,i.name ?"
     *  查询需要 查询语句hql,查询值列表parameters
     *  一个查询语句包含如下
     *  1,FROM语句, 必须有,且只出现一次
     *  2,WHERE语句,可以有,但关键字WHERE只能出现一次,可添加多个查询条件用关键字AND 连接
     *  3,ORDER BY语句,可以有,但关键字ORDER BY只能出现一次,后面的条件用关键字 , 连接
     */

    /**
     * The Parameters.
     */
    private String fromClause ="";
    private String whereClause ="";
    private String orderByClause ="";
    List<Object> parameters;    //参数值集合
    public static String ORDER_BY_DESC="desc"; //降序
    public static String ORDER_BY_ASC="asc";   //升序

    /**
     * Instantiates a new Query helper.
     * 由于FROM必须有且只能出现一次,可以用构造函数接收from 数据库对应的实体类
     *
     * @param clazz the 需要查询的对应数据库表的实体类,
     * @param alias the 别名
     */
    public QueryHelper(Class clazz,String alias) {

        this.fromClause = "FROM "+clazz.getSimpleName()+" "+alias;
    }

    /**
     * Add query condition.
     *
     * @param condition 查询条件语句:例如i.title like ?
     * @param params    查询条件语句中?对应的查询条件值，例如:%标题%
     */
    public void addQueryCondition(String condition, Object... params) {
        //已经添加过where语句,后面就连接and语句
        if (whereClause.length() > 1) {
            whereClause+=" AND "+condition;
        } else {
            //添加where语句
            whereClause+=" WHERE "+condition;
        }
        //将条件条件值保存到条件值集合中;
        if (this.parameters == null) {
            parameters=new ArrayList<Object>();
        }
        Collections.addAll(parameters, params);

    }

    public void addOrderByCondition(String conditon,String descORasc) {
        if (orderByClause.length() > 1) {//非第一个排序属性
            orderByClause+=" , "+conditon+" "+descORasc;
        } else {
            orderByClause+=" ORDER BY "+conditon+" "+descORasc;
        }
    }

    //查询hql语句
    public String getQueryListHql(){
        return fromClause+whereClause+orderByClause;
    }
    //查询hql语句中?对应的查询条件集合
    public List<Object> getParameters(){
        return parameters;
    }

}
