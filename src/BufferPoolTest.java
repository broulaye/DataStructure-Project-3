import org.junit.Assert;
import student.TestCase;

import java.io.IOException;

/**
 * @author: Cheick Berthe
 * @author Broulaye Doumbia
 * @version:10/22/2016.
 */
public class BufferPoolTest extends TestCase {
    final String filename = "testinput1";
    int poolSize;
    int position;
    final String[] argsFile = {"-a", filename , "10"};
    BufferPool pool;
    byte[] bytes;

    /********************
     * Set up variables *
     ********************/
    public void setUp(){
        poolSize = 34;
        position = 12;
        bytes = new byte[56];
        try {
            FileGenerator generator = new FileGenerator();
            generator.generateFile(argsFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Test insertion to buffer pool
     */
    public void testInsertion(){
        pool = new BufferPool(filename, poolSize);
        pool.insert(bytes, bytes.length -10, 0);
        Assert.assertEquals(10240, pool.getNumRecord());
        pool.close();
    }

    /**
     * Get data from buffer pool
     */
    public void testRetrieval() {
        pool = new BufferPool(filename, poolSize);
        pool.getBytes(bytes, bytes.length - 1, 2);
        pool.close();
    }
}
