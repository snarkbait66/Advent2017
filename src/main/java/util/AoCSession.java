package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AoCSession {

    static void setCredentials(String ga, String gid, String session) {
        Properties props = new Properties();
        try (FileOutputStream fos = new FileOutputStream("session_config.properties")) {
            props.setProperty("_ga", ga);
            props.setProperty("_gid", gid);
            props.setProperty("session", session);

            props.store(fos, null);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    static String getCredentials() {
        Properties props = new Properties();
        String credentials = "";
        try (FileInputStream propFile = new FileInputStream("session_config.properties")) {
            props.load(propFile);
            credentials = "_ga=" + props.getProperty("_ga") + "; ";
            credentials += "_gid=" + props.getProperty("_gid") + "; ";
            credentials += "session=" + props.getProperty("session");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return credentials;
    }

}
