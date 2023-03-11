package Assets.Player;

import Assets.Enemies.Enemy;
import city.cs.engine.BodyImage;
import org.jbox2d.common.Vec2;
import java.awt.event.*;

public class PlayerController implements KeyListener{
    private static float WALKING_SPEED = 16;
     private Player player;
     boolean enemyKilled = false;
    public boolean Attack, Left, Right, Sprint;

    public PlayerController(Player s){
        player = s;
        player.setGravityScale(9);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyCode()){

        }

        }


    @Override
    public void keyPressed(KeyEvent e) {
       switch(e.getKeyCode()){
           case KeyEvent.VK_A:
               Left = true;
               Right = false;
               break;
           case KeyEvent.VK_D:
               Left = false;
               Right = true;
               break;
           case KeyEvent.VK_SHIFT:
               Sprint = true;
               break;
           case KeyEvent.VK_X:
               Attack =true;
               break;
           case KeyEvent.VK_W:
           case KeyEvent.VK_SPACE:
               player.stopWalking();
               player.jump(32);
               break;
        }
       setPlayerMovement();
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                Left = false;
                Right = false;
                player.stopWalking();
            case KeyEvent.VK_D:
                Right = false;
                Left = false;
                player.stopWalking();
            case KeyEvent.VK_X:
               Attack = false;
                break;
            case KeyEvent.VK_SHIFT:
                break;
        }
        setPlayerMovement();

    }

    public void setPlayerMovement(){
        if(Attack == true && Right == true && Left == false){
            player.removeAllImages();
            player.stopWalking();
            player.addImage(new BodyImage("res/Player/Attack1.png", 7));
            if(player.GetContact() == true){
                if(player.GetEnemy() instanceof Enemy){
                    player.GetEnemy().destroy();
                    player.SetAttack(true);
                    setEnemyKilled();
                }
            }
        }
        else if(Attack == true && Left == true && Right == false){
            player.removeAllImages();
            player.stopWalking();
            player.addImage(new BodyImage("res/Player/Attack1.png", 7)).flipHorizontal();
            if(player.GetContact() == true){
                if(player.GetEnemy() instanceof Enemy){
                    player.SetAttack(true);
                    player.GetEnemy().destroy();
                    setEnemyKilled();
                }
            }
        }
        else if(Attack == true){
            player.removeAllImages();
            player.stopWalking();
            player.addImage(new BodyImage("res/Player/Attack1.png", 7));
            if(player.GetContact() == true){
                if(player.GetEnemy() instanceof Enemy){
                    player.GetEnemy().destroy();
                    enemyKilled = true;
                    setEnemyKilled();
                }

            }

        }
        else if(Left == true && Right == false){
            player.startWalking(-WALKING_SPEED);
            player.removeAllImages();
            player.addImage(new BodyImage("res/Player/Run.gif", 7)).flipHorizontal();

        }
        else if(Left == false && Right == true){
            player.startWalking( WALKING_SPEED);
            player.removeAllImages();
            player.addImage(new BodyImage("res/Player/Run.gif", 7));
        }
        else {
            player.removeAllImages();
            player.addImage(new BodyImage("res/Player/Idle.gif", 7));
            player.stopWalking();
            enemyKilled = false;
        }
    }

    public void setEnemyKilled(){
        player.GetEnemy().setPosition(new Vec2(0,-100));
    }
}
