import org.junit.Assert;
import student.TestCase;

/**
 * @author: berth
 * @version:10/23/2016.
 */
public class SortTest extends TestCase {
    Sort sorter;
    BufferPool pool;
    FileGenerator generator;
    CheckFile checker = new CheckFile();
    int poolSize;
    /*******************
     * Variable setup
     *******************/
    public void setUp() {

        sorter = new Sort();
        poolSize = 10;
        generator = new FileGenerator();
    }

    /**
     * test sorting function
     */
    public void testSort() throws Exception{
        String filename = "test1";
        final String[] argsFile = {"-a", filename, "2"};
        generator.generateFile(argsFile);
        pool = new BufferPool(filename, 9);
        sorter.quickSort(pool, 0, pool.getNumRecord()-1);
        pool.close();
        try {
            Assert.assertTrue(checker.checkFile(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
