import org.junit.Assert;
import student.TestCase;

import java.io.IOException;

/**
 * @author: berth
 * @version:10/23/2016.
 */
public class SortTest extends TestCase {
    Sort sorter;
    BufferPool pool;
    final String filename = "testinput1";
    final String[] argsFile = {"-a", filename, "22"};
    CheckFile checker = new CheckFile();
    int poolSize;
    byte[] bytes;
    /*******************
     * Variable setup
     *******************/
    public void setUp() {
        sorter = new Sort();
        poolSize = 67;
        bytes = new byte[78];

        try {
            FileGenerator generator = new FileGenerator();
            generator.generateFile(argsFile);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * test sorting function
     */
    public void testSort() {
        pool = new BufferPool(filename, poolSize);
        sorter.quicksort(pool, 20, 50);
        try {
            Assert.assertFalse(checker.checkFile(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        pool.close();
    }

}
