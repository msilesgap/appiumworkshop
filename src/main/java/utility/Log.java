package utility;

import listeners.CustomizedReports;
import org.apache.log4j.Logger;
import org.testng.Reporter;

public class Log {
    // Initialize Log4j logs
    //private static Logger logger = Logger.getLogger(Log.class.getName());
    private static final Logger logger = Logger.getLogger(CustomizedReports.class);

    // This is to print log for the beginning of the test case, as we usually run so many test cases as a test suite
    public static void startTestCase(String sTestCaseName) {
        logger.info("****************************************************************************************");
        logger.info("****************************************************************************************");
        logger.info("$$$$$$$$$$$$$$$$$$$$$                 " + sTestCaseName + "       $$$$$$$$$$$$$$$$$$$$$$$$$");
        logger.info("****************************************************************************************");
        logger.info("****************************************************************************************");
        Reporter.log("Executing Test Case: " + sTestCaseName);
    }


    // Need to create these methods, so that they can be called
    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Boolean addToReporter) {
        logger.info(message);
        Reporter.log(message);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Boolean addToReporter) {
        logger.error(message);
        Reporter.log(message);
    }

    public static void fatal(String message) {
        logger.fatal(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }
}