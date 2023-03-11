package Main;

import Assets.Enemies.Enemy;
import Assets.Enemies.Projectile;
import Assets.Player.Player;
import Assets.Interactables.markers;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Collisions implements CollisionListener {
    private Player player;
    private Game game;
    private Enemy Emy;
    boolean Attack = false;
    private boolean nextLevel= false;

    @Override
    public void collide(CollisionEvent e) {

        if(e.getOtherBody() instanceof markers){
            System.out.println("Level Complete");
            setNextLevel(true);
            // Start new Game with next level....
            this.game.SetLevel(this.game.GetLevel()+1);
            this.game.Start();

            if(e.getOtherBody() instanceof Projectile){
                e.getReportingBody().destroy();
            }
        }
    }
    public Collisions(Player p, Game g) {
        this.player = p;
        this.game = g;
    }

    public void setNextLevel(boolean n){
        this.nextLevel = n;
    }

    public boolean getNextLevel(){
        return nextLevel;
    }


public void setAttack(boolean attack){
        this.Attack = attack;
}
}




