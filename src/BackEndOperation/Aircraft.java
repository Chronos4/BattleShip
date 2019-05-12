package BackEndOperation;


public class Aircraft extends Ship{
    
    public Aircraft() {
         this.setSize(5);
         this.setType("Aircraft");
         this.boatArray = new String[this.getSize()];
    }
    
}
