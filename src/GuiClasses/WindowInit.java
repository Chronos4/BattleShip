/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuiClasses;

import java.awt.Dimension;
import javax.swing.JFrame;

public class WindowInit {
    // THOSE ARE THE FINALS VALUES OF THE MAIN JFRAME 
    // YOU CAN CHANGE THE WIDTH,HEIGHT,TITLE OF THE WINDOW AS YOU DESIRE
    // this class is use to initliaze the window
    private static final int WIDTH = 1050;
    private static final int HEIGHT = WIDTH / 12 * 9;
    private static final String TITLE = "BattleShip";
    
    public WindowInit(JFrame frame){
        frame.setTitle(TITLE);
        frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    
}
