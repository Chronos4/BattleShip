package GuiClasses;

import BackEndOperation.Handler;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ShipBorder extends JPanel {

    Handler handler;

    public ShipBorder(Handler handler) {
        this.handler = handler;
        setLayout(new GridLayout(15, 1, 0, 0)); // the reason i have more positions in rows is that it has betteer looking
        makeTheShips();
        setVisible(true);

    }

    // the main panel for the shipboard is a gridlayout 
    // which contains a flowlayout.
    //Each flowlayout contains boxes equal the size of each ship and useless boxes as the example has
    public void makeTheShips() {
        JPanel tempPanel = new JPanel(new FlowLayout());
        this.add(tempPanel);
        // adding the label "Ships to be placed in the left side of the main border"
        JLabel label = new JLabel("Ships to be Placed");
        label.setFont(new Font("Serif", Font.BOLD, 22));
        tempPanel.add(label);
        //----------------
        JLabel tempLabel;
        int shipSize = 0;
        Color color;
        for (int i = 0; i < handler.getShips().size(); i++) {
            // making the label that is going to display the type of the ship e.x. Submarine etc..
            tempLabel = new JLabel(handler.getShips().get(i).getType());
            tempLabel.setForeground(Color.RED);
            tempLabel.setFont(new Font("Serif", Font.BOLD, 17));
            this.add(tempLabel);
            //-------------------------------------------------------------------
            tempPanel = new JPanel(new FlowLayout());
            shipSize = handler.getShips().get(i).getSize();
            this.add(tempLabel);

            // adding the boxes we have a temporary attribute shipsize that is equal with the size of the current shipand gets lower 
            for (int j = 0; j < 6; j++) {
                if (shipSize != 0) {
                    Box tmpBox = new Box(Color.DARK_GRAY, i, j, BoxId.Ship);
                    tmpBox.addActionListener(tmpBox);
                    tempPanel.add(tmpBox);
                    handler.addPlayerBox(tmpBox);
                    shipSize--;
                } else {
                    Box tmpBox = new Box(i, j, BoxId.Ship);
                    tempPanel.add(tmpBox);
                    handler.addBox(tmpBox);
                }
            }
            this.add(tempPanel);
        }
    }
}
