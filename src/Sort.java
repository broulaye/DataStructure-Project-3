import java.awt.*;
import java.nio.ByteBuffer;

public class Sort {
    //used for moving bytes
    private byte[] temp1 = new byte[4];
    private byte[] temp2 = new byte[4];
    private byte[] temp3 = new byte[4];
    private int array[] = new int[2];
    /**
     * Return key value at given position from buffer
     * @param A bufferpool
     * @param pos position
     * @return key value
     */
    private Short getKeyAt(BufferPool A, int pos){
        A.getBytes(temp3, 4, pos);
        ByteBuffer bb = ByteBuffer.wrap(temp3);
        return bb.getShort();
    }

    /**
     * Partition given buffer pool about pivot
     * @param A buffer pool
     * @param equalPointer left position
     * @param right right position
     * @param pivot pivot position
     * @return first position of right partition
     */
    private int[] partition(BufferPool A, int equalPointer, int right, Short pivot) {

        int left = equalPointer;
        while (equalPointer <= right) {// Move bounds inward until they meet
            int compare =  (getKeyAt(A, equalPointer)).compareTo(pivot);
            if (compare < 0)
                swap(A, left++, equalPointer++);
            else if(compare > 0)
                swap(A, equalPointer, right--);
            else
                equalPointer++;
        }

        array[0] = right;
        array[1] = left;
        return array;
    }

    private int findPivot(int i, int j) {
        return (i+j)/2;
    }

    /**
     * Sort given buffer pool
     * @param A buffer pool
     * @param i left index
     * @param j right index
     */
    public void quickSort(BufferPool A, int i, int j) {

        if (j-i < 50 && i < j) {
            doInsertionSort(A, i, j+1);
        }
        else {

            if (j <= i) return;//sorting is over
            int pivotIndex = findPivot(i, j); // Pick a pivot
            int [] array = partition(A, i, j, getKeyAt(A, pivotIndex));
            quickSort(A, i, array[1]- 1); // Sort left partition
            quickSort(A, array[0] + 1, j); // Sort right partition

        }

    }
    /**
     * Swap A[i] and A[j]
     * @param A array (buffer pool containing elements)
     * @param i (first argument)
     * @param j (second argument)
        */
    private byte[] swap(BufferPool A, int i, int j) {
        A.getBytes(temp2, 4, j); //temp2 = A[j]
        A.getBytes(temp1, 4, i); //temp1 = A[i]
        A.insert(temp2, 4, i);   //A[i] = A[j] (or temp2)
        A.insert(temp1, 4, j);   //A[j] = A[i] (or temp1)

        return temp1;
    }

    public void doInsertionSort(BufferPool list, int start, int end)
    {
        for (int x = start + 1; x < end; x++)
        {
            list.getBytes(temp2, 4, x);
            short val = getKeyAt(list, x);
            int j = (x - 1);
            while (j >= 0 && val < getKeyAt(list, j))
            {
                list.getBytes(temp1, 4, j);
                list.insert(temp1, 4, j+1);
                j--;
            }
            list.insert(temp2, 4, j+1);
        }
    }
}
