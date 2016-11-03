import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

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
}
