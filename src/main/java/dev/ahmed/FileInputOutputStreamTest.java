package dev.ahmed;

import org.junit.Test;

import java.io.*;

/**
 * Test the use of FileInputStream and FileOutputStream

 * in conclusion:
 * 1. For text files (.txt,.java,.c,.cpp), use character stream processing
 * 2. For non-text files (.jpg,.mp3,.mp4,.avi,.doc,.ppt,...), use byte stream processing

 *
 * @author ahmed Bughra
 * @create 2023 02 17
 */
public class FileInputOutputStreamTest {

    //Using the byte stream FileInputStream to process text files may cause garbled characters.
    @Test
    public void testFileInputStream() {
        FileInputStream fis = null;
        try {
            //1. Create a file
            File file = new File("hello.txt");

            //2. Make flow
            fis = new FileInputStream(file);

            //3. Read data
            byte[] buffer = new byte[5];
            int len;//Record the number of bytes read each time
            while((len = fis. read(buffer)) != -1){

                String str = new String(buffer,0,len);
                System.out.print(str);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
                //4. Close the resource
                try {
                    fis. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    /*
    Realize the copy operation of the picture
     */
    @Test
    public void testFileInputOutputStream() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File("Love and friendship.jpg");
            File destFile = new File("Love and friendship 2.jpg");

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //The process of copying
            byte[] buffer = new byte[5];
            int len;
            while((len = fis. read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                //
                try {
                    fos. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    //Copy the file under the specified path
    public void copyFile(String srcPath,String destPath){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            //
            File srcFile = new File(srcPath);
            File destFile = new File(destPath);

            //
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);

            //The process of copying
            byte[] buffer = new byte[1024];
            int len;
            while((len = fis. read(buffer)) != -1){
                fos.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fos != null){
                //
                try {
                    fos. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fis != null){
                try {
                    fis. close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }


    }

    @Test
    public void testCopyFile(){

        long start = System. currentTimeMillis();

        String srcPath = "C:\\Users\\Administrator\\Desktop\\01-video.avi";
        String destPath = "C:\\Users\\Administrator\\Desktop\\02-video.avi";


// String srcPath = "hello.txt";
// String destPath = "hello3.txt";

        copyFile(srcPath, destPath);


        long end = System. currentTimeMillis();

        System.out.println("The time spent on the copy operation is: " + (end - start));//618

    }

}
