
package BackEndOperation;

import GuiClasses.ID;
import javax.swing.JOptionPane;


public abstract class GameEntity {
    
    public static final int SIZE=10;
    private int[][] array =new int[SIZE][SIZE];;
    protected Handler handler;
    protected ID id;
    protected int shipsAlive = 17;
    protected boolean rotate=false;
    protected String playerName;
    
    
    protected GameEntity(Handler handler,ID id){
        this.handler = handler;
        this.id = id;
    }
    
    
  
    protected GameEntity(String name, Handler handler, ID id){
     this.handler = handler;
     this.id = id;
     this.playerName = name;
    }
    
    
    //getters 
    public Handler getHandler(){
        return this.handler;
    }
    public ID getID(){
        return this.id;
    }
    
    public String getName(){
        return this.playerName;
    }
    
    public boolean getRotate(){
        return this.rotate;
    }
    
    //setters
    public void setHandler(Handler handler){
        this.handler = handler;
    }
    public void setID(ID id){
        this.id =id;
    }
    public void setName(String name){
        this.playerName = name;
    }
    
    public void setRotate(boolean value) {
        this.rotate = value;
        if(!(this.id==ID.COMPUTER))
            JOptionPane.showMessageDialog(null,"Ship Rotated");
    }
    
    
    protected int[][] constructArray(int[][] array){
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                array[i][j] = 0;
            }
        } 
        return array;
    }
    
    protected abstract boolean checkAvailability(int horizontal, int vertical, int size, boolean rotate);
    
    protected abstract boolean placeShip(int horizontal, int vertical, int size, boolean rotate);   
    
        
        
        
        
        
    
    
    
}
