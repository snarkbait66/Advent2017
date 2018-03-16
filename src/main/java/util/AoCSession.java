package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AoCSession {

    /**
     * In order to use the FileIO.getAOCInputForDay, you will need to log in to your
     * http://adventofcode.com account and get the session cookie information
     * by using the developer tools in your web browser.
     * The three codes you will need to find are listed under '_ga", '_gid' and 'session'
     *
     * @param ga _ga
     * @param gid _gid
     * @param session session
     */
    public static void setCredentials(String ga, String gid, String session) {
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

    public static String getCredentials() {
        Properties props = new Properties();
        String credentials = "";
        try (FileInputStream propFile = new FileInputStream("session_config.properties")) {
            props.load(propFile);
            credentials = "_ga=" + props.getProperty("_ga") + "; ";
            credentials += "_gid=" + props.getProperty("_gid") + "; ";
            credentials += "session=" + props.getProperty("session");
        }
        catch (FileNotFoundException e) {
            System.err.println("Properties file cannot be found.\n" +
            "Please run AoCSession.setCredentials()");
            throw new RuntimeException("No properties file.");
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return credentials;
    }

}
