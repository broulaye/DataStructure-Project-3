import java.io.BufferedReader;
import java.io.ObjectInputStream.GetField;
import java.nio.ByteBuffer;

public class Sort {
    //used for moving bytes
    private byte[] temp1 = new byte[4];
    private byte[] temp2 = new byte[4];
    private Short getKeyAt(BufferPool A, int pos){
        A.getbytes(temp1, 4, pos);
        ByteBuffer bb = ByteBuffer.wrap(temp1);
        //return (short) ((temp1[0] << 4) + temp1[1]);
        return bb.getShort();
    }
    private int partition(BufferPool A, int left, int right,
            Short pivot) {
        while (left <= right) { // Move bounds inward until they meet
            while (getKeyAt(A, left).compareTo(pivot) < 0)
                left++;
            while ((right >= left) && (getKeyAt(A, right).compareTo(pivot) >= 0))
                right--;
            if (right > left)
                swap(A, left, right); // Swap out-of-place values
        }
        return left; // Return first position in right partition
    }

    private int findpivot(int i, int j) {
        return (i + j) / 2;
    }

    public void quicksort(BufferPool pool, int i, int j) { // Quicksort
        int pivotindex = findpivot(i, j); // Pick a pivot
        swap(pool, pivotindex, j); // Stick pivot at end
        // k will be the first position in the right subarray
        short key = getKeyAt(pool, j);
        int k = partition(pool, i, j - 1, key);
        swap(pool, k, j); // Put pivot in place
        if ((k - i) > 1)
            quicksort(pool, i, k - 1); // Sort left partition
        if ((j - k) > 1)
            quicksort(pool, k + 1, j); // Sort right partition
    }
    /**
     * Swap A[i] and A[j]
     * @param A array (buffer pool containing elements)
     * @param i (first argument)
     * @param j (second argument)
     */
    private void swap(BufferPool A, int i, int j) {
        A.getbytes(temp1, 4, i); //temp1 = A[i]
        A.getbytes(temp2, 4, j); //temp2 = A[j]
        A.insert(temp2, 4, i);   //A[i] = A[j] (or temp2) 
        A.insert(temp1, 4, j);   //A[j] = A[i] (or temp1)
    }

}
