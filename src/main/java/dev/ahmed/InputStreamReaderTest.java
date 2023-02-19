package dev.ahmed;

import org.junit.Test;

import java.io.*;

/**
 * Processing stream two: the use of conversion flow
     *1. Conversion stream: belongs to the character stream
         * InputStreamReader: Convert a byte input stream to a character input stream
         * OutputStreamWriter: Convert a character output stream to a byte output stream

     * 2. Function: Provide conversion between byte stream and character stream

     * 3. Decoding: byte, byte array ---> character array, string
        * Encoding: chararray, string ---> byte, bytearray

    * 4. Character set

     ASCII: American Standard Code for Information Interchange.
         It can be represented by 7 bits of a byte.
     ISO8859-1: Latin code table. Eurocode
         Represented by 8 bits of a byte.
     GB2312: Chinese code table for China. Up to two bytes encode all characters
     GBK: China's Chinese code table has been upgraded to incorporate more Chinese characters and symbols. Up to two byte encoding
     Unicode: International Standard Code, which combines all the characters currently used by humans. Assign a unique character code to each character. All text is represented by two bytes.
     UTF-8: A variable-length encoding method, which can represent a character with 1-4 bytes.

 * @author ahmed Bughra
 * @create 2023 02 19
 */
public class InputStreamReaderTest {
    /*
         If you handle exceptions at this time, you should still use try-catch-finally
         The use of InputStreamReader realizes the conversion of byte input stream to character input stream
          */
    @Test
    public void test1() throws IOException {

        FileInputStream fis = new FileInputStream("dbcp.txt");
        // InputStreamReader isr = new InputStreamReader(fis);//Use the system default character set
        // Parameter 2 specifies the character set, which character set to use depends on the character set used when the file dbcp.txt is saved
        InputStreamReader isr = new InputStreamReader(fis,"UTF-8");//Use the system default character set

        char[] cbuf = new char[20];
        int len;
        while((len = isr. read(cbuf)) != -1){
            String str = new String(cbuf,0,len);
            System.out.print(str);
        }
        isr. close();
    }

    /*
    If you handle exceptions at this time, you should still use try-catch-finally
    Combined use of InputStreamReader and OutputStreamWriter
     */
    @Test
    public void test2() throws Exception {
    //1. Create files and create streams
        File file1 = new File("dbcp.txt");
        File file2 = new File("dbcp_gbk.txt");

        FileInputStream fis = new FileInputStream(file1);
        FileOutputStream fos = new FileOutputStream(file2);

        InputStreamReader isr = new InputStreamReader(fis,"utf-8");
        OutputStreamWriter osw = new OutputStreamWriter(fos,"gbk");

    //2. Read and write process
        char[] cbuf = new char[20];
        int len;
        while((len = isr. read(cbuf)) != -1){
            osw.write(cbuf,0,len);
        }

    //3. Close the resource
        isr.close();
        osw.close();
    }
}
