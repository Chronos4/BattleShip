package BackEndOperation;

public class Destroyer extends Ship{
    
    public Destroyer() {
        this.setSize(3);
        this.setType("Destroyer");
        this.boatArray = new String[this.getSize()];
    }
    
}
