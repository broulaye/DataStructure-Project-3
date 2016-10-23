import student.TestCase;

import java.io.IOException;

/**
 * @author Cheick Berthe
 * @author Broulaye Doumbia
 * @version 10/22/2016
 */
public class QuicksortTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization.
     */
    public void setUp() {
        // Nothing Here
    }

    /**
     * Get code coverage of the class declaration.
     */
    public void testQInit() {
        FileGenerator generator = new FileGenerator();
        String[] argsFile = {"-a", "testinput1", "10"};
        String[] argsSort = {"testinput1", "1", "teststat"};
        try {
            generator.generateFile(argsFile);
            Quicksort tree = new Quicksort();
            assertNotNull(tree);
            Quicksort.main(argsSort);

        }
        catch (IOException e) {
            
            e.printStackTrace();
        }
        
    }
}
