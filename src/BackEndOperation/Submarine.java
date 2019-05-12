package BackEndOperation;


public class Submarine extends Ship{
    
    public Submarine() {
        this.setSize(3);
        this.setType("Submarine");
        this.boatArray = new String[this.getSize()];
    }
    
}
