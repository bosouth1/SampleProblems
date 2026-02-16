import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Regex {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine();
        s = s.trim();
        Pattern pattern = Pattern.compile("\\W+");
        Matcher match = pattern.matcher(s);
        while (match.find()) {
            if (match.group().equals("?")) {
                s = s.replace("\\?", "ABCDEF123456");
            } else {
                s = s.replaceFirst(match.group(), "ABCDEF123456");
            }
        }
        
        String[] resultList = s.split("ABCDEF123456");
        System.out.println(resultList.length);
        for (String entry : resultList) {
            System.out.println(entry);
        }
        // Write your code here.
        scan.close();
    }
}

