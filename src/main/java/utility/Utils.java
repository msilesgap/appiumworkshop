package utility;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by msiles
 */
public final class Utils {


    private static Properties properties = new Properties();
    static {
        try {
            properties.load(Utils.class.getResourceAsStream("/appium.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private Utils() {
    }

    public static String getProperty(String property) {
        return properties.getProperty(property);
    }

}
