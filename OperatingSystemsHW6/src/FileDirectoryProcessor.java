import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.*;
/**
 * Project Title: FileDirectoryProcessor.java
 * Version: 08/30/2021
 * Written By: Kimberly Villatoro
 */
public class FileDirectoryProcessor {
    //fields
    File file;
    File[] paths;

    public static void main(String[] args) throws Exception {
        new FileDirectoryProcessor().menu();
    }//End main class


    public void menu() throws Exception {
        Scanner input = new Scanner(System.in);
        System.out.println("\nSelect from the following options: ");
        System.out.println("0 - Exit");
        System.out.println("1 - Select directory ");
        System.out.println("2 - List directory content (first level)");
        System.out.println("3 - List directory content (all levels)");
        System.out.println("4 - Delete File");
        System.out.println("5 - Display file (hexadecimal view)");
        System.out.println("6 - Encrypt file (XOR with password)");
        System.out.println("7 - Decrypt file (XOR with password)");
        System.out.println("Enter selection: \n");
        int selected = input.nextInt();

        switch (selected) {
            case 0:
                System.out.println("End of Program.");
                //option ends the program
                System.exit(0);
            case 1:
                selectDirectory();
                menu();
            case 2:
                displayDirectory();
                menu();
            case 3:
                System.out.println("All levels of directory " + file);
                System.out.println("**************************************************************************");
                displayAllLevels(file);
                menu();
            case 4:
                safeDelete();
                menu();
            case 5:
                displayHexadecimal();
                menu();
            case 6:
                encryption();
                menu();
            case 7:
                decryption();
                menu();
        }
    }

    /**
     */
    public void selectDirectory() {
        Scanner input = new Scanner(System.in);

        try {
            System.out.println("Select a directory, enter an absolute path to desired directory: \n");

            String directory = input.next();
            file = new File(directory);
            new JFileChooser().setCurrentDirectory(new File(directory));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method displays the files inside the selected directory on the screen.
     */
    public void displayDirectory() {
        try {
            paths = file.listFiles();
            assert paths != null;
            // Prints all files and directories
            Arrays.stream(paths).forEach(pathName -> {
                if (!pathName.isFile()) {
                    if (pathName.isDirectory()) {
                        System.out.print("Directory " + pathName.getName() + "\n");
                    }
                } else {
                    System.out.print("File " + pathName.getName() + "\n");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param file
     */
    public void displayAllLevels(File file) {

        File nextLevel;
        int i = 0;

        String[] fileList = file.list();
        while (true) {
            assert fileList != null;
            if (i < fileList.length) {
                nextLevel = new File(file.getAbsoluteFile() + "/" + fileList[i]);

                System.out.println((i + 1) + ") " + "\t" + fileList[i] + " | Dir |Parent " + nextLevel.getParentFile());

                if (!nextLevel.isDirectory()) {
                } else {
                    displayAllLevels(nextLevel);
                } //if ends
                i++;
            } else {
                break;
            }
        }

        System.out.println();
    }

    /**
     */
    public void safeDelete() {
        String fileName;
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the name of a file you would like to safely delete: \n");
            fileName = input.next();
        }

        File deleteFile = new File(file + "/" + fileName);

        System.out.println(deleteFile.delete() ? "File safely deleted." : "Failed to delete file.");
    }

    /**
     * @throws IOException
     */
    /* https://kodehelp.com/display-file-contents-hexadecimal-format-java/ */
    public void displayHexadecimal() throws IOException {
        String fileName;
        try (Scanner input = new Scanner(System.in)) {
            System.out.println("Enter the name of a file you would like to view in hexadecimal: \n");
            fileName = input.next();
        }

        String path = file + "/" + fileName;

        try (BufferedReader hexadecimalFile = new BufferedReader(new FileReader(path))) {

            // Open the file using FileInputStream
            int i;
            try (FileInputStream fis = new FileInputStream(String.valueOf(hexadecimalFile))) {
                // A counter to print a new line every 16 bytes read.
                int cnt = 0;

                // Read till the end of the file and print the byte in hexadecimal
                // valueS.
                while ((i = fis.read()) != -1) {
                    System.out.printf("%02X ", i);
                    cnt++;

                    if (cnt == 16) {
                        System.out.println();
                        cnt = 0;
                    }
                }
            }
        }
    }

    /**
     * @throws Exception
     * Retrieved from Java implementation of XOR algorithm encryption and decryption - https://programmer.help/blogs/java-implementation-of-xor-algorithm-encryption-and-decryption.html
     * */
    public void encryption() throws Exception {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the file name you want to encrypt: \n");
        String fileName = input.next();
        File encryptFile = new File(file + "/" + fileName);
        System.out.println(encryptFile);

        System.out.print("\nEnter Password: ");
        String key = input.next(); // Original password for XOR encryption / decryption

        // Encrypt the file demo.txt and output it to a file
        XORUtils.encryptFile(encryptFile, new File(file + "/" + "cipher.txt"), key.getBytes());
    }


    public void decryption() throws Exception {

        String decryptionKey;
        String fileName;
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("\nEnter Password: ");
            decryptionKey = input.next();
            System.out.println("Enter the name of a file you want to decrypt: \n");
            fileName = input.next();
        }

        File decryptFile = new File(file + "/" + fileName);
        XORUtils.encryptFile(decryptFile, new File(file + "/" + "replica.txt"), decryptionKey.getBytes());

    }

}//End FileDirectoryProcessor class




















