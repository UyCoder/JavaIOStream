package dev.ahmed;

import org.junit.Test;

import java.io.*;

/**
 * One of the processing streams: the use of buffered streams
 * 1. Buffer stream:
         * BufferedInputStream
         * BufferedOutputStream
         * BufferedReader
         * BufferedWriter

 * 2. Role: Improve the speed of reading and writing streams
        * The reason for improving the reading and writing speed: a buffer is provided internally

 * 3. To process the stream is to "socket" on the basis of the existing stream.

 * @author ahmed Bughra
 * @create 2023 02 19
 */
public class BufferedTest {
    /**
         Implement copying of non-text files
          */
    @Test
    public void BufferedStreamTest() throws FileNotFoundException {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
    //1. Create a file
            File srcFile = new File("Love and friendship.jpg");
            File destFile = new File("Love and friendship 3.jpg");
    //2. Make flow
            //2.1 create node flow
            FileInputStream fis = new FileInputStream((srcFile));
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 Create a buffer stream
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

    //3. Copy details: read, write
            byte[] buffer = new byte[10];
            int len;
            while((len = bis. read(buffer)) != -1){
                bos.write(buffer,0,len);

            // bos.flush();//Refresh the buffer

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
    //4. Resource closing
            //Requirement: close the outer stream first, then close the inner stream
            if(bos != null){
                try {
                    bos. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(bis != null){
                try {
                    bis. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //Note: While closing the outer stream, the inner stream will also be automatically closed. Regarding the closing of the inner stream, we can ignore.
            // fos. close();
            // fis. close();
        }



    }

    // method to implement file copy
    public void copyFileWithBuffered(String srcPath,String destPath){
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
    //1. Create a file
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);
    //2. Make flow
            //2.1 create node flow
            FileInputStream fis = new FileInputStream((srcFile));
            FileOutputStream fos = new FileOutputStream(destFile);
            //2.2 Create a buffer stream
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);

    //3. Copy details: read, write
            byte[] buffer = new byte[1024];
            int len;
            while((len = bis. read(buffer)) != -1){
                bos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
    //4. Resource closed
            //Requirement: close the outer stream first, then close the inner stream
            if(bos != null){
                try {
                    bos. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if(bis != null){
                try {
                    bis. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            //Note: While closing the outer stream, the inner stream will also be automatically closed. Regarding the closing of the inner stream, we can omit the .
            // fos. close();
            // fis. close();
        }
    }

    @Test
    public void testCopyFileWithBuffered(){
        long start = System. currentTimeMillis();

        String srcPath = "C:\\Users\\Administrator\\Desktop\\01-video.avi";
        String destPath = "C:\\Users\\Administrator\\Desktop\\03-video.avi";


        copyFileWithBuffered(srcPath, destPath);


        long end = System. currentTimeMillis();

        System.out.println("The time spent on the copy operation is: " + (end - start));//618 - 176
    }


    /*
    Use BufferedReader and BufferedWriter to realize the copying of text files

     */
    @Test
    public void testBufferedReaderBufferedWriter(){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            //Create file and corresponding stream
            br = new BufferedReader(new FileReader(new File("dbcp.txt")));
            bw = new BufferedWriter(new FileWriter(new File("dbcp1.txt")));

            // read and write operations
            //Method 1: use char[] array
            // char[] cbuf = new char[1024];
            // int len;
            // while((len = br.read(cbuf)) != -1){
            // bw.write(cbuf,0,len);
            // // bw. flush();
            // }

            //Method 2: use String
            String data;
            while((data = br. readLine()) != null){
                //method one:
            //   bw.write(data + "\n");//data does not contain newline characters
                //Method Two:
                bw.write(data);//data does not contain newline characters
                bw.newLine();//Provide newline operation

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close the resource
            if(bw != null){

                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
