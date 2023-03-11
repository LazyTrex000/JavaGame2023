package Main;

import city.cs.engine.UserView;
import city.cs.engine.World;
import org.w3c.dom.Text;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameView extends UserView {
    Game game;

    private BufferedImage background, foreground;
    public GameView(World w, int width, int height) {
        super(w, width, height);
        game = new Game();

        try {
            background = ImageIO.read(new File("res/Enviroment/Sky.png"));
            foreground = ImageIO.read(new File("res/Enemy/Attack.gif"));
        } catch (IOException e) {
        }
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        super.paintForeground(g);
        if(game.GetLevel() == 2) {
            g.drawImage(foreground, 0, 10, 500, 500, this);
            g.drawString("Helllooo My computer has virus", 100, 100);
        }
    }
}
