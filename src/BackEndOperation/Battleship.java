package BackEndOperation;

public class Battleship extends Ship {
    
    public Battleship() {
        this.setSize(4);
        this.setType("Battleship");
        this.boatArray = new String[this.getSize()];
    }
    
}
