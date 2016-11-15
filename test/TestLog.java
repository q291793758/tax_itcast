import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class TestLog {
    Log log = LogFactory.getLog(getClass());

    @Test
    public void testLog() {

        log.debug("debugInfo");
        log.info("Info");
        log.warn("warnInfo");
        log.error("errorInfo");
        log.fatal("fatalInfo");
    }

    @Test
    public void test2() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());

        }
    }

    @Test
    public void testStringUtils() {

        String str = null;
        str = " ";
        boolean notBlank = StringUtils.isNotBlank(str);
        System.out.println(notBlank);
    }

    @Test
    public void test4() {
        String[] date1 = {"yyyy-MM-dd HH-mm"};
        String str1 = "2016-11-12 23-18";
        try {
            Date date = DateUtils.parseDate(str1, date1);
            System.out.println("date = " + date.toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5() {
        try {
            Date start;
            Date end;
            Date temp;
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            for (int i = 0; i < 12; i++) {
                start=calendar.getTime();;
                calendar.add(Calendar.MONTH,1);
                end=calendar.getTime();
                printDate(start,end);
            }
            calendar.add(Calendar.SECOND,-1);
            Date time = calendar.getTime();
            System.out.println(time.toLocaleString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void printDate(Date start, Date end) {
        System.out.println("start = " + start.toLocaleString());
        System.out.println("end = " + end.toLocaleString());
    }
}
