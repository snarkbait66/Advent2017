package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

/**
 * FileIO.java is a set of static text file reading/writing methods
 * for maximum simplicity. They are all for one-line use.
 * @author /u/Philboyd_Studge on 12/5/2015.
 */
public class FileIO {

    private FileIO() { }

    private static final Logger LOGGER = Logger.getLogger( FileIO.class.getName() );
    /**
     * Load file into one String - assumes no line feeds
     * i.e. 2015 Day 1, 2015 Day 3
     * @param filename file in current working directory or full pathname
     * @return String
     */
    public static String getFileAsString(final String filename) {
        String test = "";
        try  {
            test = new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException ioe) {
            LOGGER.severe(ioe.toString());
        }
        return test;

    }

    /**
     * Write byte array to given filename
     * @param input byte array
     * @param outFile valid filename
     */
    public static void writeBytesToFile(final byte[] input, String outFile) {
        Path path = Paths.get(outFile);
        try {
            Files.write(path, input);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Write List of strings to file, adding line separators after each line
     * except for the last.
     * @param input List of Strings
     * @param outfile valid filename/path
     */
    public static void writeListToFile(List<String> input, String outfile) {
        Path path = Paths.get(outfile);
        try (BufferedWriter bw = Files.newBufferedWriter(path)){
            for (int i = 0; i < input.size(); i++) {
                bw.write(input.get(i));
                if (i < input.size() - 1) {
                    bw.newLine();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    /**
     * Load list of strings from advent of code input for given day/year
     * writes text file to working directory named adventXXXX_dayXX.txt
     * You must supply session ID information by logging in to a valid
     * <a href>http://adventofcode.com</a> account and copying
     * the ENTIRE information string listed in the GET header under Cookie
     * Cannot download the information until midnight EST of that day
     *
     * if file has already been created, read list from that file instead.
     *
     * @param year integer year, 2015, 2016 or 2017
     * @param day 1 - 25
     * @param sessionID Session cookie information
     * @return
     */
    public static List<String> getAOCInputForDay(int year, int day, String sessionID) {
        if (year < 2015 || year > 2017) {
            throw new IllegalArgumentException("Year out of range.");
        }
        if (day < 1 || day > 25) {
            throw new IllegalArgumentException("Day out of range");
        }
        String url = "http://adventofcode.com/" + year +
                "/day/" + day + "/input";
        String filename = "puzzle_input\\advent" + year + "_day" + day + ".txt";
        Path path = Paths.get(filename);
        if (Files.exists(path)) {
            //LOGGER.info("File found: " + filename);
            return getFileAsList(filename);
        } else {
            List<String> input = getFromUrl(url, sessionID);
            writeListToFile(input, filename);
            LOGGER.info("File written as: " + filename);
            LOGGER.info("Number of lines: " + input.size());
            return input;
        }
    }

    /**
     * Performs given Function on file, one line at a time, and summing the results
     * @param filename file in current working directory or full pathname
     * @param func Function that takes a String as parameter and returns an int
     * @return int summed result
     */
    public static int performIntActionOnLine(final String filename, Function<String, Integer> func) {
        int result = 0;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String input;
            while ((input = br.readLine()) != null) {
                result += func.apply(input);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return result;

    }

    /**
     * Loads entire file, one line at a time, into List
     * @param filename file in current working directory or full pathname
     * @return ArrayList of strings
     */
    public static List<String> getFileAsList(final String filename) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String input;
            while ((input = br.readLine()) != null) {
                list.add(input);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    /**
     * Read data from a URL into a List of Strings
     * Note -> does not work with Advent of Code puzzle input
     * @param url
     * @return
     */
    public static List<String> getFromUrl(final String url) {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
            String input;
            while ((input = br.readLine()) != null) {
                list.add(input);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    /**
     * Load input data from URL using session cookie information.
     * url will look like <code>http://adventofcode.com/2015/day/18/input</code>
     * You must have copied the entire session cookie string (not just session ID
     * but the two other strings tht start with "_ga" and "_gid"
     * You will not be able to access days that have not been posted yet
     * @param url correct adventofcode.com url
     * @param sessionID session cookie info
     * @return List of strings from data file
     */
    private static List<String> getFromUrl(final String url, String sessionID) {
        List<String> list = new ArrayList<>();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", sessionID);
            connection.connect();
            String response = connection.getResponseMessage();
            if (!response.equals("OK")) {
                throw new InvalidParameterException("Unable to establish connection.");
            }
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()))) {
                String input;
                while (( input = br.readLine()) != null) {
                    list.add(input);
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;

    }

    /**
     * Return an ArrayList of String Arrays, split using the given delimiter
     * @param filename file in current working directory or full pathname
     * @param delimiter REGEX string delimiter. Catches PatternSyntaxException.
     * @return List of String Arrays
     */
    public static List<String[]> getFileLinesSplit(final String filename, String delimiter) {
        List<String[]> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String input;
            while ((input = br.readLine()) != null) {
                try {
                   // input = input.trim();
                    String[] s = input.split(delimiter);
                    list.add(s);
                } catch (PatternSyntaxException pse) {
                    System.out.println("Bad regex syntax. Delimiter \"" + delimiter + " \"");
                    return null;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;

    }

    public static List<int[]> getFileLinesSplitAsInt(final String filename, String delimiter) {
        List<int[]> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(filename))) {
            String input;
            while ((input = br.readLine()) != null) {
                try {
                    // input = input.trim();
                    String[] s = input.split(delimiter);
                    list.add(StringArrayToInt(s));
                } catch (PatternSyntaxException pse) {
                    System.out.println("Bad regex syntax. Delimiter \"" + delimiter + " \"");
                    return null;
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    /**
     * Parse a String array into an int array
     * if parsing error occurs, inserts a value of -1
     * into array at that index
     * @param input String array
     * @return array of primitive integers
     */
    public static int[] StringArrayToInt(final String[] input) {
        return StringArrayToInt(input, -1);
    }

    /**
     * Parse a String array into int array
     * Catches conversion errors and puts given defaultValue at that index
     * @param input String array
     * @param defaultValue value to use when error is caught
     * @return array of primitive integers
     */
    public static int[] StringArrayToInt(final String[] input, final int defaultValue) {
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            try {
                output[i] = Integer.parseInt(input[i]);
            } catch (NumberFormatException nfe) {
                System.err.println("Not a valid integer at index: " + i);
                System.err.println("Replacing with: " + defaultValue);
                output[i] = defaultValue;
            }
        }
        return output;
    }

    /**
     * Parse a String array into an Integer array
     * if parsing error occurs, inserts a value of -1
     * into array at that index
     * @param input String array
     * @return array of Integer objects
     */
    public static Integer[] StringArrayToInteger(final String[] input) {
        return StringArrayToInteger(input, -1);
    }

    /**
     * Parse a String array into Integer array
     * Catches conversion errors and puts given defaultValue at that index
     * @param input String array
     * @param defaultValue value to use when error is caught
     * @return array of Integer objects
     */
    public static Integer[] StringArrayToInteger(final String[] input, final int defaultValue) {
        Integer[] output = new Integer[input.length];
        for (int i = 0; i < input.length; i++) {
            try {
                output[i] = Integer.parseInt(input[i]);
            } catch (NumberFormatException nfe) {
                System.err.println("Not a valid integer at index: " + i);
                System.err.println("Replacing with: " + defaultValue);
                output[i] = defaultValue;
            }
        }
        return output;
    }

    public static List<String[]> getListOfStringArrays(List<String> input, String delimiter) {
        return input.stream()
                .map(x -> x.split(delimiter))
                .collect(Collectors.toList());
    }

    public static List<Integer[]> getListOfIntegerArrays(List<String> input, String delimiter) {
        return input.stream()
                .map(x -> x.split(delimiter))
                .map(FileIO::StringArrayToInteger)
                .collect(Collectors.toList());

    }

}
