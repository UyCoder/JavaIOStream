package dev.ahmed.exer;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * Exercise 3: Get the number of occurrences of characters on the text and write the data to the file

 * Ideas:
 * 1. Traverse each character of the text
 * 2. The number of occurrences of characters is stored in the Map

 * Map<Character,Integer> map = new HashMap<Character,Integer>();
 * map. put('a',18);
 * map.put('You',2);

 * 3. Write the data in the map to the file
 *
 * @author shkstart
 * @create 2019 at 3:47 pm
 */
public class WordCount {
    /*
    Note: If unit testing is used, the relative path of the file is the current module
          If you use main() to test, the relative path of the file is the current project
     */
    @Test
    public void testWordCount() {
        FileReader fr = null;
        BufferedWriter bw = null;
        try {
    //1. Create a Map collection
            Map<Character, Integer> map = new HashMap<Character, Integer>();

    //2. Traverse each character, and put the number of times each character appears in the map
            fr = new FileReader("dbcp.txt");
            int c = 0;
            while ((c = fr. read()) != -1) {
                //int restore char
                char ch = (char) c;
                // Determine whether char appears for the first time in the map
                if (map. get(ch) == null) {
                    map. put(ch, 1);
                } else {
                    map. put(ch, map. get(ch) + 1);
                }
            }

    //3. Store the data in the map in the file count.txt
            //3.1 Create Writer
            bw = new BufferedWriter(new FileWriter("wordcount.txt"));

            //3.2 traverse the map, and then write data
            Set<Map.Entry<Character, Integer>> entrySet = map.entrySet();
            for (Map.Entry<Character, Integer> entry : entrySet) {
                switch (entry. getKey()) {
                    case ' ':
                        bw.write("space=" + entry.getValue());
                        break;
                    case '\t'://\t represents the tab key character
                        bw.write("tab key=" + entry.getValue());
                        break;
                    case '\r'://
                        bw.write("Enter=" + entry.getValue());
                        break;
                    case '\n'://
                        bw.write("Newline=" + entry.getValue());
                        break;
                    default:
                        bw.write(entry.getKey() + "=" + entry.getValue());
                        break;
                }
                bw. newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
    //4. Close the flow
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
