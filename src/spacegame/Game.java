package spacegame;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class Fire {
    private int x;
    private int y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Game extends JPanel implements KeyListener, ActionListener {
    Timer timer = new Timer(4, this);
    private int passing_time = 0;
    private int spent_fire = 0;
    private BufferedImage image;
    private BufferedImage imageBack, backgroundShip, bullet,ball;
    private ArrayList<Fire> fires = new ArrayList<Fire>();
    private int firedirY = 1;
    private int ballX = 0;
    private int balldirX = 2;
    private int spaceshipX = 0;
    private int dirspaceX = 20;

    public boolean control() {
        for (Fire fire : fires) {
            if (new Rectangle(fire.getX(), fire.getY(), 10, 20).intersects(new Rectangle(ballX, 0, 20, 20))) {
                return true;

            }

        }
        return false;

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        passing_time += 5;
        g.setColor(Color.cyan);
        g.drawImage(backgroundShip, 0, 0, backgroundShip.getWidth(), backgroundShip.getHeight(), this);


        g.drawImage(image, spaceshipX, 490, image.getWidth() / 10, image.getHeight() / 10, this);


        g.fillOval(ballX, 0, 20, 20);



        for (Fire fire : fires) {
            if (fire.getY() < 0) {
                fires.remove(fire);
            }

        }

        g.setColor(Color.RED);
        for (Fire fire : fires) {
//            g.fillRect(fire.getX(), fire.getY(), 10, 20);
            g.drawImage(bullet, fire.getX(), fire.getY(), 20, 30, this);
        }
        if (control()) {
            timer.stop();
            String message = "Win...\n" +
                    "Spent Fire :" + spent_fire +
                    "\nPassing Time :" + passing_time / 1000.0 + " second";
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint();

    }

    public Game() {
        try {

            image = ImageIO.read(new FileImageInputStream(new File("spaceship.1.png")));
            bullet = ImageIO.read(new FileImageInputStream(new File("redlaser.png")));
            backgroundShip = ImageIO.read(new FileImageInputStream(new File("finished.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
        timer.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {

            if (spaceshipX <= 0) {
                spaceshipX = 0;
            } else {
                spaceshipX -= dirspaceX;
            }
        } else if (c == KeyEvent.VK_RIGHT) {
            if (spaceshipX >= 750) {
                spaceshipX = 750;

            } else {
                spaceshipX += dirspaceX;
            }

        } else if (c == KeyEvent.VK_CONTROL) {
            fires.add(new Fire(spaceshipX + 26, 480));
            spent_fire++;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Fire fire : fires) {
            fire.setY(fire.getY() - firedirY);

        }


        ballX += balldirX;
        if (ballX >= 780) {
            balldirX = -balldirX;


        }
        if (ballX <= 0) {
            balldirX = -balldirX;

        }
        repaint();


    }
}
