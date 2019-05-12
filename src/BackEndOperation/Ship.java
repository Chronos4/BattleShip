package BackEndOperation;

public abstract class Ship {

    private String type;
    private int size;
    private boolean leftAlive = true;
    String[] boatArray;

    public Ship() {
        
    }

    //getters
    public String getType() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public boolean getLeft() {
        return this.leftAlive;
    }

    //setters
    public void setType(String value) {
        this.type = value;
    }

    public void setSize(int value) {
        this.size = value;
    }

    public void setLeft(boolean value) {
        this.leftAlive = value;
    }

}
