package GuiClasses;

import BackEndOperation.Computer;
import BackEndOperation.Handler;
import BackEndOperation.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class Box extends JButton implements ActionListener {

    Handler handler = Window.handler;
    private static final int SIZE = 40; // size of each bytton
    BoxId id;
    Color color;
    int horizontal;
    int vertical;

    public Box(Color color, int horizontal, int vertical, BoxId id) {
        this.color = color;
        this.id = id;
        setBackground(color);
        this.horizontal = horizontal;
        this.vertical = vertical;
        setPreferredSize(new Dimension(40, 40));
    }

    public Box(int horizontal, int vertical, BoxId id) {
        this.id = id;
        this.horizontal = horizontal;
        this.vertical = vertical;
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(40, 40));
    }

    //getters
    public Color getColor() {
        return this.color;
    }

    public int getHorizontal() {
        return this.horizontal;
    }

    public int getVertical() {
        return this.vertical;
    }

    public BoxId getId() {
        return this.id;
    }

    //setters
    public void setHorizontal(int value) {
        this.horizontal = value;
    }

    public void setVertical(int value) {
        this.vertical = value;
    }

    public void setColor(Color value) {
        this.color = value;
    }

    public void setId(BoxId id) {
        this.id = id;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Box tmpBox = (Box) e.getSource();
        if (!Window.startingPhase) {
            prePhase(tmpBox);
        } else {
            afterPhase(tmpBox);
        }
    }

    // loop the player box array in the handler that handles the important buttons in the shipboard
    // this function when we press a button if the button is one of the ships button in the shipboard
    // then it disables all those buttons in the row and returns the ship size that we will need use to the player board
    public int pressedShip(Box box) {
        int horizontal = box.getHorizontal();
        int counter = 0;
        // getting the static handler from the main window 
        for (int i = 0; i < handler.getPlayerBox().size(); i++) {
            if (handler.getPlayerBox().get(i).getHorizontal() == horizontal) {
                handler.getPlayerBox().get(i).setEnabled(false);
                handler.getPlayerBox().get(i).setBackground(Color.LIGHT_GRAY);
                counter++;
            }
        }
        return counter;
    }

    public void prePhase(Box tmpBox) {
        Player player = Window.player;
        // check the comments in the pressedShip function below to see what exactly the function do
        if (tmpBox.getId() == BoxId.Ship) {
            int number = pressedShip(tmpBox);
            System.out.println(number);
            System.out.println(player.getSelectedShip());
            player.setSelectedShip(number);
        }
        //------------------------------------------------------------------------------------------

        // if the button that is pressed is in the board section then
        if (tmpBox.getId() == BoxId.Board) {
            boolean rotate = player.getRotate();
            if (player.placeShip(tmpBox.getHorizontal(), tmpBox.getVertical(),0,rotate)) {
                //we loop throught the player array in the player class and if we find a non zero value in any cell then we colorize the buttons
                // with non zero value
                for (int i = 0; i < handler.getBox().size(); i++) {
                    if ((player.getArray()[handler.getBox().get(i).getHorizontal()][handler.getBox().get(i).getVertical()] != 0)
                            && handler.getBox().get(i).getId() == BoxId.Board) {
                        handler.getBox().get(i).setBackground(Color.DARK_GRAY);
                    }

                    // at the end we make the number of the ship 0 so we can use it for the next button press
                    player.setSelectedShip(0);
                }
                JOptionPane.showMessageDialog(null, "Ship inserted successfull");
            } else if (player.getSelectedShip() != 0) {
                for (int i = 0; i < handler.getShips().size(); i++) {
                    if (handler.getShips().get(i).getSize() == player.getSelectedShip()) {
                        JOptionPane.showMessageDialog(null, "Error on placing the ship you still have selected the ship :"
                                + handler.getShips().get(i).getType().toUpperCase() + " and size :" + handler.getShips().get(i).getSize() + "  try again",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        JOptionPane.showMessageDialog(null,"This is a test");
                    }
                }
            }
        }
    }

    // after the start button the fight start 
    public void afterPhase(Box box) {
        Computer computer = Window.computer;
        Player player = Window.player;
        // we get the data of each button we pressed and then we set to 0 the same position in the computer array
        int horizontal = box.getHorizontal();
        int vertical = box.getVertical();
        if (computer.getArray()[horizontal][vertical] != 0) {
            box.setBackground(Color.RED);
            box.setEnabled(false);
            computer.getArray()[horizontal][vertical] = 0;
            computer.setShipsAlive(computer.getShipsAlive() - 1);
        } else {    
            box.setBackground(Color.WHITE);
            box.setEnabled(false);
        }
        computerAttack(computer, player, box); // see below

        calculateWinner(player, computer);

    }

    // the method that the computer chooses the box to attack
    public void computerAttack(Computer computer, Player player, Box tmpbox) {
        int[] data = computer.attack(); // the attack method returns random points for the box to attack
        int horizontal = data[0];
        int vertical = data[1];
        for (int i = 0; i < Window.handler.getBox().size(); i++) {
            Box box = Window.handler.getBox().get(i); // create a temp instance of the button that the computer chose to destroy
            if (box.getHorizontal() == horizontal && box.getVertical() == vertical) {
                if (player.getArray()[horizontal][vertical] != 0) { // then if the button is not 0 in the player array we set it to 0 = destroyed
                    box.setBackground(Color.RED);
                    player.getArray()[horizontal][vertical] = 0;
                    player.setShipsAlive(player.getShipsAlive() - 1);
                } else {
                    box.setBackground(Color.WHITE);
                }
            }
        }
    }
    // this methos is used to check after every round of hits to see if someones array has alla positions =0 then the winner is been chosen
    public void calculateWinner(Player player, Computer computer) {
        if (player.getShipsAlive() == 0) {
            JOptionPane.showMessageDialog(null, "Computer won the game!");
        } else if (computer.getShipsAlive() == 0) {
            JOptionPane.showMessageDialog(null, player.getName() + " won the game!");
        }

    }

}
