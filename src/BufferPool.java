import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author Broulaye Doumbia
 * @author Cheick Berthe
 */
public class BufferPool implements BufferPoolADT{

    private Block[] Pool;
    private Byte[] block;
    private RandomAccessFile file;
    private int blockSize;
    private int maxPoolSize;
    private int size;
    
   /**
    * constructor that create a new buffer pool from file
    * @param file represent file name
    * @param poolSize represent pool size
    */
    public BufferPool(String file, int poolSize) {
        Pool = new Block[20];
        for(int i = 0; i < Pool.length; i++) {
            Pool[i] = new Block();
        }
        size = 0;
        block = new Byte[4096];
        blockSize = 4096;
        maxPoolSize = poolSize;
        try {
            this.file = new RandomAccessFile(file, "rw");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }

   
    /**
     * Copy "sz" bytes from "space" to position "pos" in the buffered storage
     * @param space source of bytes
     * @param sz size of bytes
     * @param pos position to be inserted in
     */
    @Override
    public void insert(byte[] space, int sz, int pos) {
        int blockPos = posToBlock(pos);
        for(int i=0; i < size; i++) {
            if(Pool[i].getPos() == blockPos) {
                System.arraycopy(space, 0, Pool[i].getBlock(),(4*pos)-blockPos,sz);
                Pool[i].setDirty(true);
                Block temp = Pool[i];
                for(int k = i; k >0; k--) {
                    Pool[k] = Pool[k-1];
                }
                Pool[0] = temp;
                return;
            }
        }
        
        if(size >= maxPoolSize) {
            if(Pool[size-1].isDirty()) {
                writeToFile(Pool[size-1].getBlock(), Pool[size-1].getPos());
            }
            for(int i = size-1; i > 0; i--) {
                Pool[i] = Pool[i-1];
            }
            
        }
        else {
            for(int i = size; i > 0; i--) {
                Pool[i] = Pool[i-1];
            }
            size++;

        }

        Pool[0].setPos(blockPos);
        getBlock(blockPos, Pool[0].getBlock());
        System.arraycopy(space, 0, Pool[0].getBlock(),(4*pos)-blockPos,sz);
        Pool[0].setDirty(true);
        
        
    }

    /**
     * private method that write bytes array to the file
     * at a specified position 
     * @param block2 block to be inserted
     * @param Bpos position it need to be put into
     */
    private void writeToFile(byte[] block2, int Bpos) {
        //long pos = Bpos;
        try {
            file.seek(Bpos);
            file.write(block2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Copy "sz" bytes from position "pos" of the buffered storage to "space"
     * @param space destination of bytes
     * @param sz size of bytes
     * @param pos position of bytes
     */
    @Override
    public void getbytes(byte[] space, int sz, int pos) {
        int blockPos = posToBlock(pos);
        for(int i=0; i < size; i++) {
            if(Pool[i].getPos() == blockPos) {
                System.arraycopy(Pool[i].getBlock(),(4*pos)-blockPos,space,0,sz);
                return;
            }
        }
        if(size >= maxPoolSize) {
            if(Pool[size-1].isDirty()) {
                writeToFile(Pool[size-1].getBlock(), Pool[size-1].getPos());
            }
            for(int i = size-1; i > 0; i--) {
                Pool[i] = Pool[i-1];
            }
        }
        else {
            for(int i = size; i > 0; i--) {
                Pool[i] = Pool[i-1];
            }
            size++;
        }
        Pool[0].setPos(blockPos);
        getBlock(blockPos, Pool[0].getBlock());
        Pool[0].setDirty(false);
        System.arraycopy(Pool[0].getBlock(),(4*pos)-blockPos,space, 0,sz);

    }
    
    /**
     * private method to get the block at 
     * position blockPos in the file
     * @param blockPos position of block
     * @param block new block to get
     */
    private void getBlock(int blockPos, byte[] block) {
        //int pos = blockPos ;
        try {
            file.seek(blockPos);

            if( file.read(block) <0) {
                throw new Exception("End of file reached");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * get the number of records in the file
     * @return number of records
     */
    public int getNumRecord(){
        int length = 0;
        try {
            length = (int)(file.length()/4);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }
    
    /**
     * convert a position to a block position
     * @param pos position to convert
     * @return the block number
     */
    private int posToBlock(int pos) {
        return ((int)((pos*4)/blockSize))*blockSize;
    }
    
    /**
     * This method flush the buffer pool and
     * then close the file stream
     */
    public void close() {
        for(int i = 0; i < Pool.length; i++) {
            if(Pool[i].isDirty()) {
                writeToFile(Pool[i].getBlock(), Pool[i].getPos());
            }
        }
        
        Pool = null;
        
        try {
            file.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    

}
