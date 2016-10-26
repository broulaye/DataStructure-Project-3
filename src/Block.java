/**
 * This class represent a block in the file
 * @author Broulaye Doumbia
 * @version 10-21-2016
 */
public class Block {
    private byte[] block;
    private int pos;
    private boolean isDirty;
    
    /**
     * Default constructor
     */
    public Block() {
        block = new byte[4096];
        pos = 0;
        isDirty = false;
    }
    
    /**
     * Constructor setting values of parameters
     * @param block represent the new block
     * @param pos represent new block position
     * @param isDirty represent new block state
     */
    public Block(byte[] block, int pos, boolean isDirty) {
        this.block = block;
        this.pos = pos;
        this.isDirty = isDirty;
    }

    public void setBlock(Block newBlock){
        System.arraycopy(newBlock.getBlock(), 0, block, 0, 4096);
        this.pos = newBlock.pos;
        this.isDirty = newBlock.isDirty;
    }

    /**
     * get the block
     * @return the block
     */
    public byte[] getBlock() {
        return block;
    }

    /**
     * set block to new value
     * @param block1 new value
     */
    public void setBlock(byte[] block1) {
        System.arraycopy(block1, 0, this.block,0, 4096);
    }

    /**
     * get position of block
     * @return block position
     */
    public int getPos() {
        return pos;
    }

    /**
     * set position of block 
     * @param pos new block position
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * get block state
     * @return block state
     */
    public boolean isDirty() {
        return isDirty;
    }

    /**
     * set block state
     * @param isDirty block new state
     */
    public void setDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }
    
    
    
}
