package dev.ahmed;

import org.junit.Test;

import java.io.*;
/**
 * Use of other streams
     * 1. Standard input and output streams
     * 2. Print stream
     * 3. Data flow
 *
 * @author ahmed Bughra
 * @create 2023 02 19
 */
public class OtherStreamTest {

    /*
    1. Standard input and output streams
    1.1
    System.in: standard input stream, default input from the keyboard
    System.out: Standard output stream, output from the console by default
    1.2
    The setIn(InputStream is) / setOut(PrintStream ps) method of the System class redesignates the input and output streams.

    1.3 Exercises:
    Input a character string from the keyboard, and it is required to convert the entire line of character strings read into uppercase output. Then proceed with the input operation,
    Until when "e" or "exit" is input, the program exits.

    Method 1: Use Scanner to implement, call next() to return a string
    Method 2: Use System.in to achieve. System.in ---> Conversion Stream ---> BufferedReader's readLine()

     */
    public static void main(String[] args) {
        BufferedReader br = null;
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);

            while (true) {
                System.out.println("Please enter a string: ");
                String data = br. readLine();
                if ("e".equalsIgnoreCase(data) || "exit".equalsIgnoreCase(data)) {
                    System.out.println("The end of the program");
                    break;
                }

                String upperCase = data.toUpperCase();
                System.out.println(upperCase);

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
/*
     2. Print stream: PrintStream and PrintWriter

     2.1 provides a series of overloaded print() and println()
     2.2 Exercise:



      */

    @Test
    public void test2() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream(new File("D:\\IO\\text.txt"));
            // Create a print output stream, set to auto-flush mode (the output buffer will be flushed when a newline character or byte '\n' is written)
            ps = new PrintStream(fos, true);
            if (ps != null) {// Change the standard output stream (console output) to a file
                System. setOut(ps);
            }


            for (int i = 0; i <= 255; i++) { // output ASCII characters
                System.out.print((char) i);
                if (i % 50 == 0) { // every 50 data rows
                    System.out.println(); // Newline
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }

    }
    /*
         3. Data flow
         3.1 DataInputStream and DataOutputStream
         3.2 Function: used to read or write variables or strings of basic data types

         Exercise: Write strings in memory and variables of basic data types to files.

         Note: To handle exceptions, try-catch-finally should still be used.
          */
    @Test
    public void test3() throws IOException {
        //1.
        DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));
        //2.
        dos.writeUTF("Liu Jianchen");
        dos.flush();//refresh operation, write the data in the memory to the file
        dos. writeInt(23);
        dos. flush();
        dos. writeBoolean(true);
        dos. flush();
        //3.
        dos. close();


    }
    /*
    Read the basic data type variables and character strings stored in the file into the memory and save them in the variables.

    Note: the order of reading different types of data should be the same as the order of the saved data when the file was originally written!

     */
    @Test
    public void test4() throws IOException {
        //1.
        DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
        //2.
        String name = dis.readUTF();
        int age = dis.readInt();
        boolean isMale = dis.readBoolean();

        System.out.println("name = " + name);
        System.out.println("age = " + age);
        System.out.println("isMale = " + isMale);

        //3.
        dis.close();

    }

}
