import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
public class XORUtils {

    /**
     * @author xietansheng
     *  Retrieved from Java implementation of XOR algorithm encryption and decryption - https://programmer.help/blogs/java-implementation-of-xor-algorithm-encryption-and-decryption.html
     * Encrypt / decrypt file XOR algorithm
     *
     * @param inFile Input file (ciphertext / clear text)
     * @param outFile Result output file
     * @param key secret key
     */
    public static void encryptFile(File inFile, File outFile, byte[] key) throws Exception {
        InputStream in = null;
        OutputStream out = null;

        try {
            // File input stream
            in = new FileInputStream(inFile);
            // The result output stream, exclusive or operation, byte is a read and write, where cache stream must be used,
            // Wait until the cache reaches a certain number of bytes (10240 bytes) and then write to the disk (otherwise, write to the disk too many times and the speed will be very slow)
            out = new BufferedOutputStream(new FileOutputStream(outFile), 10240);

            int b;
            long i = 0;

            // Read one byte of the file in each cycle, and use the key byte array to cycle encryption or decryption
            while ((b = in.read()) != -1) {
                // Data is XOR with key, and then XOR with low 8 bits of cyclic variable (increasing complexity)
                b = (b ^ key[(int) (i % key.length)] ^ (int) (i & 0xFF));
                // Write a byte after encryption / decryption
                out.write(b);
                // Loop variable increment
                i++;
            }
            out.flush();

        } finally {
            close(in);
            close(out);
        }
    }

    private static void close(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }

}