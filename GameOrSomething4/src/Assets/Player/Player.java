package Assets.Player;

import city.cs.engine.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.Body;
import org.jbox2d.common.Vec2;

public class Player extends Walker {


    public static final Shape playerShape = new BoxShape(1, 2);
    private int playerHealth = 5;
    private boolean inContact;
    private boolean Attack;
    private Body enemy;
    public Player(World world) {
        super(world, playerShape);
    }
    public boolean GetContact()
    {
        return inContact;
    }
    public void SetContact(boolean Con)
    {
        inContact=Con;
    }
        public Body GetEnemy()
        {
            return enemy;
        }
        public void SetEnemy(Body _enemy)
        {
            enemy=_enemy;
        }

        public void playerHealthHandler(){

        if(Attack==false) {
            System.out.println("Health" + " " + playerHealth);
            playerHealth--;
            if(playerHealth==0) {
                this.destroy();
            }
        }
        }


        public void SetAttack(boolean attack){
        this.Attack = attack;
        }


}



