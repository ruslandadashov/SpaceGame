package spacegame;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JFrame {
    public GameScreen(String title) throws HeadlessException {
        super(title);
    }

    public static void main(String[] args) {
        GameScreen ekran = new GameScreen("Space Game");
        ekran.setResizable(false);
        ekran.setFocusable(false);
        ekran.setSize(800, 600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//-----------------------------------------------------------------------//

        Game game = new Game();
        game.requestFocus();
        game.addKeyListener(game);
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);//
        ekran.add(game);
        ekran.setVisible(true);
    }
}
