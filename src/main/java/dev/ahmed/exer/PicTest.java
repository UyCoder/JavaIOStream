package dev.ahmed.exer;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
/**
 * @author Ahmed Bughra
 * @create 2023-02-19  2:03 PM
 */
public class PicTest {

    //Encrypt the image
    @Test
    public void test1() {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("Love and friendship.jpg");
            fos = new FileOutputStream("love and friendship secret.jpg");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fis. read(buffer)) != -1) {
                //Byte array to modify
                //Incorrect
                // for(byte b : buffer){
                // b = (byte) (b ^ 5);
                // }
                //correct
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    // Decrypt the image
    @Test
    public void test2() {

        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("Love and friendship secret.jpg");
            fos = new FileOutputStream("Love and friendship 4.jpg");

            byte[] buffer = new byte[20];
            int len;
            while ((len = fis. read(buffer)) != -1) {
                //Byte array to modify
                //Incorrect
                // for(byte b : buffer){
                // b = (byte) (b ^ 5);
                // }
                //correct
                for (int i = 0; i < len; i++) {
                    buffer[i] = (byte) (buffer[i] ^ 5);
                }

                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}