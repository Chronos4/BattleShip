package GuiClasses;
import BackEndOperation.Handler;
import BackEndOperation.Player;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class Board extends JPanel {

    private static final int BOARDSIZE = 10;
    ID id;
    Handler handler;

    public Board(ID id, Handler handler){
        setLayout(new GridLayout(10, 10));
        this.handler = handler;
        this.id = id; // we use the id to check which board we use (board for the player or board for the computer)

        for (int i = 0; i < BOARDSIZE; i++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                Box box = new Box(Color.CYAN, j, i, BoxId.Board);
                box.addActionListener(box);
                handler.addBox(box);
                add(box);
            }
        }
    }

    // this method when the start button is pressed and the 2 boards 1 for computer and 1 for player are showing up 
    // then it will fill the players board with the ships that he has place
    public void prepareForTheFight() {
        Player player = Window.player;
        if ((Window.startingPhase) && this.id == ID.PLAYER) {
            for (int i = 0; i < handler.getBox().size(); i++) {
                if ((player.getArray()[handler.getBox().get(i).getHorizontal()][handler.getBox().get(i).getVertical()] != 0)
                        && handler.getBox().get(i).getId() == BoxId.Board) {
                    handler.getBox().get(i).setBackground(Color.DARK_GRAY);
                }
            }
        }
    }

}
