package BackEndOperation;

import GuiClasses.Box;
import java.util.ArrayList;

public class Handler {

    //Singleton variable
    private static Handler instance = new Handler();
    // object is a list that contains all the boxes and their information in the board section
    ArrayList<Box> object = new ArrayList<Box>();

    // ship list is a a list that contains the ships sorted
    private ArrayList<Ship> ship = new ArrayList<Ship>();

    // player box list is the array that contains all the important buttons(dark grey buttons) in the ship board
    private ArrayList<Box> playerBox = new ArrayList<Box>();

    public Handler() {
        ship.add(new Other());
        ship.add(new Aircraft());
        ship.add(new Battleship());
        ship.add(new Submarine());
        ship.add(new Destroyer());
        ship.add(new PatrolBoat());
    }

    public ArrayList<Box> getBox() {
        return this.object;
    }

    public ArrayList<Ship> getShips() {
        return this.ship;
    }

    public ArrayList<Box> getPlayerBox() {
        return this.playerBox;
    }

    public void addBox(Box tmpBox) {
        this.object.add(tmpBox);
    }

    public void removeBox(Box tmpBox) {
        this.object.remove(tmpBox);
    }

    public void addPlayerBox(Box tmpBox) {
        this.playerBox.add(tmpBox);
    }

    public void removePlayerBox(Box tmpBox) {
        this.playerBox.remove(tmpBox);
    }
}
