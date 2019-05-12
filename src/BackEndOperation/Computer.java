package BackEndOperation;

import GuiClasses.ID;
import java.util.Random;

// SINGLETON CLASS
public class Computer extends GameEntity{
    private static Computer INSTANCE;
    private boolean turn = false;
    private int[][] computerArray= new int[SIZE][SIZE];
    private Random r;

    private Computer(Handler handler, ID id) {
        super(handler,id);
        r = new Random();
        // initialize the board array with 0 
        computerArray=constructArray(computerArray); 
    }

    //getters
    public int[][] getArray() {
        return this.computerArray;
    }

    public boolean getTurn() {
        return this.turn;
    }

    public int getShipsAlive() {
        return this.shipsAlive;
    }

    // setters
    public void setTurn(boolean value) {
        this.turn = value;
    }

    public void setShipsAlive(int value) {
        this.shipsAlive = value;
    }
    
    // SINGLETON ACCESS
    public static Computer getInstance(Handler handler,ID id){
        if (INSTANCE==null){
            INSTANCE = new Computer(handler,id);
        }
        return INSTANCE;
    }

    
    public void run() {
        if (turn) {
            // i<5 because we have 5 types of ships 
            for (int i = 0; i < handler.getShips().size(); i++) {
                int tmpHorizontal = r.nextInt(SIZE);
                int tmpVertical = r.nextInt(SIZE);
                rotate = checkRotate(); // randomly rotate the ship
                // if checkRotate is true then the computer will place the ship horizontal else will place it vertical
                int shipSize = handler.getShips().get(i).getSize();
                while (!checkAvailability(tmpHorizontal, tmpVertical, shipSize, rotate)) {
                    tmpHorizontal = r.nextInt(SIZE);
                    tmpVertical = r.nextInt(SIZE);
                    rotate = checkRotate();
                }
                placeShip(tmpHorizontal, tmpVertical, shipSize, rotate);
            }
            for (int k = 0; k < SIZE; k++) {
                for (int l = 0; l < SIZE; l++) {
                    System.out.println("Position " + k + "-" + l + ":" + computerArray[k][l]);
                }
            }
        }
    }

// we use this method so the computer to be able randomly rotate the ship or no
    public boolean checkRotate() {
        int tempNumber = r.nextInt(20);
        if (tempNumber % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkAvailability(int horizontal, int vertical, int size, boolean rotate) {
        int[] tmpArray = new int[size];
        for (int k = 0; k < tmpArray.length; k++) {
            tmpArray[k] = 0;
        }
        if (rotate) {
            if ((vertical + size) <= 9) {
                for (int j = 0; j < size; j++) {
                    if (computerArray[horizontal][vertical + j] == 0) {
                        tmpArray[j] = 1;
                    }
                }
                for (int i = 0; i < tmpArray.length; i++) {
                    if (tmpArray[i] != 1) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            if ((horizontal + size) <= 9) {
                for (int j = 0; j < size; j++) {
                    if (computerArray[horizontal + j][vertical] == 0) {
                        tmpArray[j] = 1;
                    }
                }
                for (int i = 0; i < tmpArray.length; i++) {
                    if (tmpArray[i] != 1) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }

    // after the check when we are sure that we can place the ships to the specific area then we place them with this function
    public boolean placeShip(int horizontal, int vertical, int size, boolean rotate) {
        if (rotate) {
            for (int i = 0; i < size; i++) {
                computerArray[horizontal][vertical + i] = 2;
            }
        } else {
            for (int i = 0; i < size; i++) {
                computerArray[horizontal + i][vertical] = 1;
            }
        }
        return true;
    }

    public int[] attack() {
        int[] data = new int[2];
        data[0] = r.nextInt(10);
        data[1] = r.nextInt(10);
        return data;
    }
}
