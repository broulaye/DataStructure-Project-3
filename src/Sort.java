import java.nio.ByteBuffer;

/**
 * This class encapsulate quickSort
 * 
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 * @version 11/01/2016
 *
 */
public class Sort {
    // used for moving bytes
    private byte[] temp1 = new byte[4];
    private byte[] temp2 = new byte[4];
    private byte[] temp3 = new byte[4];

    /**
     * Return key value at given position from buffer
     * 
     * @param aBufferPool
     *            bufferpool
     * @param pos
     *            position
     * @return key value
     */
    private Short getKeyAt(BufferPool aBufferPool, int pos) {
        aBufferPool.getBytes(temp3, 4, pos);
        ByteBuffer bb = ByteBuffer.wrap(temp3);
        // return (short) ((temp1[0] << 4) + temp1[1]);
        return bb.getShort();
    }

    /**
     * Partition given buffer pool about pivot
     * 
     * @param aBufferPool
     *            buffer pool
     * @param left
     *            left position
     * @param right
     *            right position
     * @param pivot
     *            pivot position
     * @return first position of right partition
     */
    private int partition(BufferPool aBufferPool, int left, int right,
            Short pivot) {
        while (left <= right) { // Move bounds inward until they meet
            while ((getKeyAt(aBufferPool, left)).compareTo(pivot) < 0) {
                left++;
            }
            while ((right >= left) && ((getKeyAt(aBufferPool, right))
                    .compareTo(pivot) >= 0)) {
                right--;
            }
            if (right > left) {
                swap(aBufferPool, left, right); // Swap out-of-place values
            }
        }
        return left; // Return first position in right partition
    }

    /**
     * find the pivot
     * 
     * @param i
     *            left value
     * @param j
     *            right value
     * @return the pivot
     */
    private int findPivot(int i, int j) {
        return (i + j) / 2;
    }

    /**
     * Sort given buffer pool
     * 
     * @param aBufferPool
     *            buffer pool
     * @param i
     *            left index
     * @param j
     *            right index
     */
    public void quickSort(BufferPool aBufferPool, int i, int j) { // Quicksort

        boolean sorted = true;
        short firstValue = getKeyAt(aBufferPool, i);

        for (int k = i + 1; k <= j; k++) {
            if (firstValue != getKeyAt(aBufferPool, k)) {
                sorted = false;
                break;
            }
        }
        if (sorted) {
            return;
        }

        int pivotIndex = findPivot(i, j); // Pick a pivot
        ByteBuffer bb = ByteBuffer.wrap(
                swap(aBufferPool, pivotIndex, j)); // Stick pivot
                                                                 // at end
        // k will be the first position in the right subarray
        int k = partition(aBufferPool, i, j - 1, bb.getShort());
        swap(aBufferPool, k, j); // Put pivot in place
        if ((k - i) > 1) {
            quickSort(aBufferPool, i, k - 1); // Sort left partition
        }
        if ((j - k) > 1) {
            quickSort(aBufferPool, k + 1, j); // Sort right partition
        }
    }

    /**
     * Swap A[i] and A[j]
     * 
     * @param aBufferPool
     *            array (buffer pool containing elements)
     * @param i
     *            (first argument)
     * @param j
     *            (second argument)
     */
    private byte[] swap(BufferPool aBufferPool, int i, int j) {
        aBufferPool.getBytes(temp1, 4, i); // temp1 = A[i]
        aBufferPool.getBytes(temp2, 4, j); // temp2 = A[j]
        aBufferPool.insert(temp1, 4, j); // A[j] = A[i] (or temp1)
        aBufferPool.insert(temp2, 4, i); // A[i] = A[j] (or temp2)
        return temp1;
    }

}
