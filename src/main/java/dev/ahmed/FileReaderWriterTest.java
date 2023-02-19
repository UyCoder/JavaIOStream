package dev.ahmed;


import org.junit.Test;

import java.io.*;

/**
 *
 * First, Stream classification:
     * 1. Operation data unit: byte stream, character stream
     * 2. Data flow: input stream, output stream
     * 3. The role of flow: node flow, processing flow

 * Second, the architecture of the flow
     * Abstract base class node stream (or file stream) buffer stream (a kind of processing stream)
     * InputStream     FileInputStream   (read(byte[] buffer))        BufferedInputStream (read(byte[] buffer))
     * OutputStream    FileOutputStream  (write(byte[] buffer,0,len)  BufferedOutputStream (write(byte[] buffer,0,len) / flush()
     * Reader          FileReader (read(char[] cbuf))                 BufferedReader (read(char[] cbuf) / readLine())
     * Writer          FileWriter (write(char[] cbuf,0,len)           BufferedWriter (write(char[] cbuf,0,len) / flush()


 *
 * @author ahmed Bughra
 * @create 2023 02 17
 */
public class FileReaderWriterTest {
    public static void main(String[] args) {
        File file = new File("hello.txt");//compared to the current project
        System.out.println(file.getAbsolutePath());

        File file1 = new File("day09\\hello.txt");
        System.out.println(file1.getAbsolutePath());
    }

    /*
    Read the content of the hello.txt file under day09 into the program and output it to the console

    Explanation points:
    1. The understanding of read(): returns a character read in. Returns -1 if end of file is reached
    2. Exception handling: In order to ensure that stream resources can be closed. Need to use try-catch-finally processing
    3. The file to be read must exist, otherwise a FileNotFoundException will be reported.

     */
    @Test
    public void testFileReader(){
        FileReader fr = null;
        try {
    // 1. Instantiate the object of the File class and specify the file to be operated
            File file = new File("hello.txt");//Compared to the current Module
    //2. Provide a specific stream
            fr = new FileReader(file);

    //3. Data reading
            //read(): Returns a character read in. Returns -1 if end of file is reached
            //method one:
            // int data = fr. read();
            // while(data != -1){
            // System.out.print((char)data);
            // data = fr. read();
            // }

            //Method two: syntax modified for Method 1
            int data;
            while((data = fr.read()) != -1){
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
    //4. The closing operation of the stream
            // try {
            // if(fr != null)
            // fr. close();
            // } catch (IOException e) {
            // e. printStackTrace();
            // }
            //or
            if(fr != null){
                try {
                    fr. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //Upgrade the read() operation: use the overloaded method of read
    @Test
    public void testFileReader1() {
        FileReader fr = null;
        try {
    //1.Instantiation of the File class
            File file = new File("hello.txt");

    //2. Instantiation of the FileReader stream
            fr = new FileReader(file);

    //3. Read operation
            //read(char[] cbuf): Returns the number of characters read into the cbuf array each time. Returns -1 if end of file is reached
            char[] cbuf = new char[5];
            int len;
            while((len = fr. read(cbuf)) != -1){
                //method one:
                //wrong wording
                // for(int i = 0;i < cbuf.length;i++){
                // System.out.print(cbuf[i]);
                // }
                                //Correct spelling
                // for(int i = 0;i < len;i++){
                // System.out.print(cbuf[i]);
                // }
                //Method 2:
                //Wrong way of writing, corresponding to the way of wrong way of writing
                // String str = new String(cbuf);
                // System.out.print(str);
                //Correct spelling
                String str = new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr != null){
    //4. Close the resource
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    /*
       Write data from memory to a file on disk.

       Description:
       1. For output operations, the corresponding File may not exist. will not report an exception
       2.
            If the file in the hard disk corresponding to File does not exist, this file will be created automatically during the output process.
            If the file in the hard disk corresponding to File exists:
                   If the constructor used by the stream is: FileWriter(file,false) / FileWriter(file): Overwrite the original file
                   If the constructor used by the stream is: FileWriter(file,true): the original file will not be overwritten, but the content will be appended to the original file
        */
    @Test
    public void testFileWriter() {
        FileWriter fw = null;
        try {
            //1. Provide an object of the File class, indicating the file to be written to
            File file = new File("hello1.txt");

            //2. Provide FileWriter object for data writing
            fw = new FileWriter(file,false);

            //3. Write operation
            fw.write("I have a dream!\n");
            fw.write("you need to have a dream!");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. Closing of stream resources
            if(fw != null){

                try {
                    fw. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Test
    public void testFileReaderFileWriter() {
        FileReader fr = null;
        FileWriter fw = null;
        try {
            //1. Create an object of the File class, indicating the file to read in and write out
            File srcFile = new File("hello.txt");
            File destFile = new File("hello2.txt");

            //You cannot use character streams to process byte data such as pictures
// File srcFile = new File("Love and friendship.jpg");
// File destFile = new File("Love and Friendship 1.jpg");


            //2. Create objects of input stream and output stream
            fr = new FileReader(srcFile);
            fw = new FileWriter(destFile);


            //3. Data read-in and write-out operations
            char[] cbuf = new char[5];
            int len;//Record the number of characters read into the cbuf array each time
            while((len = fr. read(cbuf)) != -1){
                //Write out len characters each time
                fw.write(cbuf,0,len);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4. Close the stream resource
            //method one:
// try {
// if(fw != null)
// fw. close();
// } catch (IOException e) {
// e. printStackTrace();
// }finally{
// try {
// if(fr != null)
// fr. close();
// } catch (IOException e) {
// e. printStackTrace();
// }
// }
            //Method 2:
            try {
                if(fw != null)
                    fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if(fr != null)
                    fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}
