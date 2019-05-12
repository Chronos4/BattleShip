package GuiClasses;

import BackEndOperation.Computer;
import BackEndOperation.Handler;
import BackEndOperation.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements Runnable {
    public static boolean startingPhase = false;   // we use this attribute to make it true when the button star game will be pressed 
    // do not change startingPhase 
    private String playerName = null;

    // static instances
    public static Handler handler;
    public static Computer computer;
    public static Player player;
    public static ID turn;
    private Thread thread;  // this thread is used check the run method below to see why we need te thread
    private JButton rotateShip, startGameButton;
    private JPanel borderPanel;

    public Window() {
        new WindowInit(this);
        while (playerName == null || playerName.equals("")) {
            playerName = getTheUserName();   // creating the optionpane for the player to get his name 
        }
        handler = new Handler();// a class that has information about the ships and  the boxes 
         // our instance of the computer opponent
        player = Player.getInstance(playerName, handler, ID.PLAYER);
        computer = Computer.getInstance(handler, ID.COMPUTER);// our instance of our player     
        // create the jframe the windowinit set the settings to the jframe
        createMainBorder(); // CREATING THE MAIN BORDER LAYOUT
        thread = new Thread(this);
        thread.start();
    }
    

    public Player getPlayer() {
        return player;
    }

    public Computer getComputer() {
        return computer;
    }

    public Handler getHandler() {
        return handler;
    }

    public String getPlayerName() {
        return playerName;
    }

    public JFrame getFrame() {
        return this;
    }

    private void createMainBorder() {
        JPanel mainBorder = new JPanel(new BorderLayout());
        this.add(mainBorder);

        // adding the header of the main Border layout 
        JLabel topLabel = new JLabel("Please select ships from the left and place them in your board.Press 'Start Game' when you are ready.");
        topLabel.setFont(new Font("Serif", Font.BOLD, 23));
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        mainBorder.add(topLabel, BorderLayout.PAGE_START);
        mainBorder.add(new JSeparator());
        // calling the class to add the buttons
        creatingTheButtons(mainBorder);
        // creating the 2 boards ->> 1 for the ships and 1->>> main board to place them
        mainBorder.add(new Board(ID.PLAYER, handler), BorderLayout.CENTER);
        mainBorder.add(new ShipBorder(handler), BorderLayout.LINE_START);
    }

    private void creatingTheButtons(JPanel panel) {
        borderPanel = new JPanel(new BorderLayout());
        panel.add(borderPanel, BorderLayout.PAGE_END);

        // creating rotae ship button
        rotateShip = new JButton("Rotate Ship");
        rotateShip.setPreferredSize(new Dimension(130, 30));
        rotateShip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotate();
            }
        });
        borderPanel.add(rotateShip, BorderLayout.LINE_START);

        // a thread that is running all the time to check when the button will enabled then it stops
        startGameButton = new JButton("Start Game");
        startGameButton.setPreferredSize(new Dimension(130, 30));
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // fight class is in the 2econd phase of the game
                startingPhase = true;
                getContentPane().removeAll();
                getContentPane().repaint();
                computer.setTurn(true);
                computer.run();
                new FightBoard(getPlayerName(), getPlayer(), getComputer(), getHandler(), getFrame());
                turn = ID.PLAYER;
                setVisible(true);
            }
        });
        borderPanel.add(startGameButton, BorderLayout.LINE_END);

    }

    // THIS METHOD IS USED TO CREATE THE J OPTION PANEL FOR THE PLAYER TO GET THE NAME OF THE PLAYER
    private String getTheUserName() {
        playerName = (String) JOptionPane.showInputDialog(null, "Please insert your name and press 'OK'");
        return playerName;
    }

    //Player class contains a rotate attribute we use this method to change the attribute when the button is clicked
    public void rotate() {
        if (player.getRotate()) {
            player.setRotate(false);
        } else {
            player.setRotate(true);
        }
    }

    // we need the thread so all the time it is running and checking when the player will place all the ships so the start button will
    // set to on
    @Override
    public void run() {
        while (player.getTotalNumberOfShips() < 5) {
            startGameButton.setEnabled(false);
            System.out.println("ship"+player.getTotalNumberOfShips());
        }
        startGameButton.setEnabled(true);
    }

}
