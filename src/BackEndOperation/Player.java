package BackEndOperation;

import GuiClasses.ID;
import javax.swing.JOptionPane;


// SINGLETON CLASS
public class Player extends GameEntity{
    private static Player INSTANCE;
    private int[][] playerArray= new int[SIZE][SIZE];
    private static int shipSelected = 0;
    private static int numberOfShipsBeingPlaced=0;
        
    private Player(String name,Handler handler,ID id) {
        super(name,handler,id);
    }

    public int[][] getArray() {
        return this.playerArray;
    }

    
    public int getShipsAlive(){
        return this.shipsAlive;
    }

    public int getSelectedShip() {
        return shipSelected;
    }
    
    public int getTotalNumberOfShips(){
        return numberOfShipsBeingPlaced;
    }
    

    public void setSelectedShip(int value) {
        shipSelected = value;
    }

    
    public void setShipsAlive(int value){
        this.shipsAlive=value;
    }
    
    // SINGLETON ACCESS
    public static Player getInstance(String name,Handler handler,ID id){
        if(INSTANCE==null)
            INSTANCE = new Player(name,handler,id);
        return INSTANCE;
    }


    // the function that places the ship in the player array
    public boolean placeShip(int horizontal, int vertical, int size,boolean rotate) {
        int[] tempArray = new int[horizontal * vertical];
        // initialize the error calculator array
        for (int j = 0; j < tempArray.length; j++) {
            tempArray[j] = 0;
        }
        // if shipSelect = 0 then it means that the user didnt choose a ship and just pressed the board buttons then we will raise an error
        if (shipSelected == 0) {
            JOptionPane.showMessageDialog(null, "Error pick a ship first!!!!", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            // if rotate is true then it places the ship horizontally else will replace it vertically
            if (checkAvailability(horizontal, vertical, shipSelected ,rotate)) {
                if (this.rotate) {
                    for (int i = 0; i < shipSelected; i++) {
                        this.playerArray[horizontal][vertical + i] = shipSelected;
                    }
                    //increase the total number of ships beign places by 1 
                    Player.numberOfShipsBeingPlaced++;
                    return true;
                } else {
                    for (int i = 0; i < shipSelected; i++) {
                        this.playerArray[horizontal + i][vertical] = shipSelected;
                    }
                    //increase the total number of ships beign places by 1 
                    Player.numberOfShipsBeingPlaced++;
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    // function that makes all the necessery checks if the ship is albe to be places
    // 1st check is if we have size to place the ship vertically or horizontally
    // if we pass the check then we check if there is another ship in the place that we want to place it
    public boolean checkAvailability(int horizontal, int vertical, int size,boolean rotate) {
        // the size of the array will be equal to size of the ship that we will pass because we will iterate (size) * times
        int[] tmpArray = new int[size];

        // initialize the array with 0
        for (int i = 0; i < size; i++) {
            tmpArray[i] = 0;
        }
        //------------------------------------------------------------------
        //The check is starting 
        if ((horizontal + size > 10 && rotate==false  )|| (vertical + size > 10 && rotate == true)) {
            JOptionPane.showMessageDialog(null, "Error not enouph space for the ship to be places", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {

            // if rotate is true
            if (rotate) {
                //we fill the array with 1 if all the positions in the playerarray are 0 
                for (int i = 0; i < size; i++) {
                    if (playerArray[horizontal][vertical + i] == 0) {
                        tmpArray[i] = 1;
                    }
                }
                // when we fill the area we iterate through it and if we find a value !=1 then we will return false that means that the position
                // is filled in the player array by another ship
                for (int j = 0; j < size; j++) {
                    if (tmpArray[j] != 1) {
                        JOptionPane.showMessageDialog(null, "Another ship is laying there you cannot place the ship", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                return true;
            } else {
                for (int i = 0; i < size; i++) {
                    if (playerArray[horizontal + i][vertical] == 0) {
                        tmpArray[i] = 1;
                    }
                }
                for (int j = 0; j < size; j++) {
                    if (tmpArray[j] != 1) {
                        JOptionPane.showMessageDialog(null, "Another ship is laying there you cannot place the ship", "Error", JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                return true;
            }
        }
    }
}
