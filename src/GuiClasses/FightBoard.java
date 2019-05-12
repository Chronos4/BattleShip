package GuiClasses;

import BackEndOperation.Computer;
import BackEndOperation.Handler;
import BackEndOperation.Player;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class FightBoard extends JFrame {

    private Player player;
    private Computer computer;
    private String playerName;
    private Handler handler;
    Board boardPlayer, boardComputer;
    private Handler handler2;

    public FightBoard(String name, Player player, Computer computer, Handler handler, JFrame frame) {
        this.playerName = name;
        this.player = player;
        this.computer = computer;
        this.handler = handler;
        this.createTheMainBorder(frame);

    }

    public void createTheMainBorder(JFrame frame) {
        JPanel mainPanel = new JPanel(new FlowLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        //handler1 = new Handler();
        // boardPlayer.prepareForTheFight();
        handler2 = new Handler();
        addBoard(playerName, player.getID(), Window.handler, mainPanel);
        addBoard(null, computer.getID(), handler2, mainPanel);
       // JOptionPane.showMessageDialog(null, "You are ready to start!");
    }

    // the method that adds the boards in the new frame 
    public void addBoard(String playerName, ID id, Handler handler, JPanel panel) throws NullPointerException {
        JPanel panel2 = new JPanel(new BorderLayout());
        JLabel label;
        if (id == ID.PLAYER) {
            label = new JLabel(playerName + "'s" + " " + "Board");
        } else {
            label = new JLabel("Computer's Board");
        }
        label.setHorizontalAlignment(JLabel.CENTER);
        panel2.add(label, BorderLayout.PAGE_START);
        if (id == ID.PLAYER) {
            Board board = new Board(id, handler);
            board.prepareForTheFight();
            panel2.add(board, BorderLayout.CENTER);
        } else {
            panel2.add(new Board(id, handler), BorderLayout.CENTER);
        }
        panel.add(panel2);
    }

}
