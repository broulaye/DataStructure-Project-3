/**
 * 
 */

/**
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 *
 */
public interface BufferPoolADT {
    // Copy "sz" bytes from "space" to position "pos" in the buffered storage
    public void insert(byte[] space, int sz, int pos);

    // Copy "sz" bytes from position "pos" of the buffered storage to "space"
    public void getBytes(byte[] space, int sz, int pos);
}
