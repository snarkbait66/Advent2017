package util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AoCTitle {

    public static void main(String[] args) {
        List<String> stuff = FileIO.getFromUrl("http://adventofcode.com/2017/day/22");
        Pattern p = Pattern.compile("--- (Day [\\d]+: [A-Za-z ]+) ---"); // [0-9]+: [A-Za-z ]+---");
        for (String s : stuff) {
            Matcher m = p.matcher(s);
            if (m.find()) {
                System.out.println(m.group(1));
            }
        }


    }
}
