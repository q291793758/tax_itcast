SELECT imonth,count(comp_id)
FROM tmonth LEFT JOIN complain
ON   imonth=month(comp_time) AND year(comp_time)=2016
GROUP BY imonth
ORDER BY imonth asc
;

-- 推荐使用下面的语句,先查询出需要的数据再左外连接,效率更高
SELECT t.imonth,c.c2
FROM tmonth AS t LEFT JOIN (SELECT
                              month(comp_time) c1,
                              count(comp_id)   c2
                            FROM complain WHERE year(comp_time)= 2016
                            GROUP BY c1) AS c
    ON t.imonth = c.c1
ORDER BY t.imonth ASC ;