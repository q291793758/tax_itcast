package cn.itcast.test;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TestDateFormat {
    @Test
    public void test1() throws ParseException {
        String dateStr1 = "1985-02-33";
        String dateStr2 = "19855-991-6";
        Calendar instance = Calendar.getInstance(Locale.CHINESE);
        instance.set(2015,11,12,11,55,55);
//        instance.add(Calendar.MONTH,2);
        instance.roll(Calendar.MONTH,5);
        Date dateStr3 = instance.getTime();

        System.out.println(dateStr3);


    }

    @Test
    public void test2() {
        Date date1 = new Date();
        long l1=1000*60*60*24*3;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        Date date2 = new Date(date1.getTime() - l1);
//        System.exit(-1);
        Runtime.getRuntime().exit(0);
        System.out.println(date2.toLocaleString());


    }

    @Test
    public void test3() {
        List<String> list = new ArrayList();
        list.add("dd");
        list.add("cc");
        list.add("bb");
        list.add("aa");

        Collections.sort(list);

        //1，iterator
        System.out.println("============1iterator==============");
        Iterator iterator = list.iterator();
        List<String> synchronizedList = Collections.synchronizedList(list);
        while (iterator.hasNext()) {
            Map<String, Number> map = new HashMap<String, Number>();
            map.put("1",1f);

            System.out.println(iterator.next());
        }
        //2，foreach
        System.out.println("============2foreach==============");
        for (String str : list) {
            System.out.println(str);
        }
        //3,fori
        System.out.println("============3fori==============");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        //4,先将集合转换为数组，再利用数组的遍历输出；
        System.out.println("============4toArray==============");
        Object strs[] =  list.toArray();
        for (Object str : strs) {
            System.out.println(str);
        }
        //


    }
}
