
import student.TestCase;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 10/22/2016.
 */
public class BufferPoolTest extends TestCase {
    private String filename = "testinput1";
    private int poolSize;
    private String[] argsFile = {
        "-a", filename, "10"
    };
    private BufferPool pool;
    private byte[] bytes;
    private byte[] bytes2;

    /********************
     * Set up variables *
     ********************/
    public void setUp() {
        poolSize = 2;
        bytes = new byte[4];
        bytes2 = new byte[4];
        try {
            FileGenerator generator = new FileGenerator();
            generator.generateFile(argsFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test insertion to buffer pool
     */
    public void testInsertion() {
        pool = new BufferPool(filename, poolSize);
        
        
        pool.getBytes(bytes, bytes.length, 20);
        pool.insert(bytes, 4, 0);
        pool.getBytes(bytes2, bytes.length, 0);
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        ByteBuffer bb2 = ByteBuffer.wrap(bytes);
        assertEquals(bb.getShort(), bb2.getShort());
        
        pool.insert(bytes, 4, 50);
        pool.insert(bytes, 4, 0);
        pool.insert(bytes, 4, 1000);
        pool.insert(bytes, 4, 8000);
        pool.insert(bytes, 4, 1);
        pool.insert(bytes, 4, 13);
        pool.insert(bytes, 4, 2000);
        pool.insert(bytes, 4, 4000);
        pool.insert(bytes, 4, 80050);
        pool.insert(bytes, 4, 50040);
        pool.insert(bytes, 4, 0);
        pool.insert(bytes, 4, 50);
        pool.insert(bytes, 4, 3005);
        pool.insert(bytes, 4, 42);
        pool.insert(bytes, 4, 2);
        pool.getBytes(bytes, bytes.length, 20);
        pool.getBytes(bytes, bytes.length, 2000);
        pool.insert(bytes, 4, 8000);
        pool.close();
    }

    /**
     * Get data from buffer pool
     */
    public void testRetrieval() {
        pool = new BufferPool(filename, poolSize);
        pool.getBytes(bytes, bytes.length, 2);
        pool.getBytes(bytes2, bytes.length, 2);
        ByteBuffer bb = ByteBuffer.wrap(bytes);
        ByteBuffer bb2 = ByteBuffer.wrap(bytes);
        assertEquals(bb.getShort(), bb2.getShort());
        
        pool.getBytes(bytes, bytes.length, 2);
        pool.getBytes(bytes2, bytes.length, 2);
        pool.getBytes(bytes, bytes.length, 20);
        pool.getBytes(bytes2, bytes.length, 20);
        pool.getBytes(bytes, bytes.length, 15);
        pool.getBytes(bytes2, bytes.length, 15);
        pool.getBytes(bytes, bytes.length, 30);
        pool.getBytes(bytes2, bytes.length, 30);
        pool.getBytes(bytes, bytes.length, 60);
        pool.getBytes(bytes2, bytes.length, 60);
        pool.getBytes(bytes, bytes.length, 80);
        pool.getBytes(bytes2, bytes.length, 80);
        
        bb = ByteBuffer.wrap(bytes);
        bb2 = ByteBuffer.wrap(bytes);
        assertEquals(bb.getShort(), bb2.getShort());
        
        pool.close();
    }
}
