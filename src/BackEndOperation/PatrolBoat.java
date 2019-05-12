package BackEndOperation;

public class PatrolBoat extends Ship{
    
    public PatrolBoat() {
        this.setSize(2);
        this.setType("PatrolBoat");
        this.boatArray = new String[this.getSize()];
    }
    
}
