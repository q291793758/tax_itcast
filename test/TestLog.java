import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import java.text.ParseException;
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
            int i=1/0;
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());

        }
    }

    @Test
    public void testStringUtils() {

        String str=null;
        str = " ";
        boolean notBlank = StringUtils.isNotBlank(str);
        System.out.println(notBlank);
    }

    @Test
    public void test4() {
        String[] date1 = {"yyyy-MM-dd HH-mm"};
        String str1="2016-11-12 23-18";
        try {
            Date date = DateUtils.parseDate(str1, date1);
            System.out.println("date = " + date.toString());


        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
