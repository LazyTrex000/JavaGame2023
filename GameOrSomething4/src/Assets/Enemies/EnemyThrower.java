package Assets.Enemies;

import Assets.Player.Player;
import city.cs.engine.AttachedImage;
import city.cs.engine.BodyImage;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyThrower implements ActionListener {
    Player player;
    Enemy enemy;
    Timer magicTimer;
    boolean left,right;
 Vec2 Offset;
AttachedImage enemyIdle, enemySummon;
    public EnemyThrower(Player _player, Enemy _enemy){
        player = _player;
        enemy = _enemy;
        magicTimer = new Timer(1900,this);
        magicTimer.setInitialDelay(100);
        magicTimer.start();

        Offset = new Vec2(0,1);



    }
    public void longRangedBoiss() {
        enemy.removeAllImages();
        enemyIdle = enemy.addImage(new BodyImage("res/Enemy/Idle.gif",19));
        enemyIdle.setOffset(Offset);
        if (player.getPosition().sub(enemy.getPosition()).x < -5 && player.getPosition().sub(enemy.getPosition()).x > -30 &&
                player.getPosition().sub(enemy.getPosition()).y < 6) {
            Projectiles();
            left = true;
            right = false;

            } else if (player.getPosition().sub(enemy.getPosition()).x < 30 && player.getPosition().sub(enemy.getPosition()).x > 5 &&
                player.getPosition().sub(enemy.getPosition()).y < 6) {
            Projectiles();
            left = false;
            right = true;
        }
        else {}
    }
    public void Projectiles(){
        enemy.removeAllImages();
        enemySummon = enemy.addImage(new BodyImage("res/Enemy/Summoning.gif",19));
        enemySummon.setOffset(Offset);

        Projectile MagicBall = new Projectile(enemy.getWorld());
        MagicBall.setPosition(enemy.getPosition().add(new Vec2(0,5)));
        MagicBall.setGravityScale(9);

        if(left && !right) {
            MagicBall.applyImpulse(player.getPosition().sub(new Vec2(30, 0)).sub(MagicBall.getPosition()));
            enemySummon.flipHorizontal();
        }
        else if(right && !left){
            MagicBall.applyImpulse(player.getPosition().add(new Vec2(30, 0)).sub(MagicBall.getPosition()));
        }

        CollisionListener magicChecker = new CollisionListener() {
            @Override
            public void collide(CollisionEvent c) {
                if (c.getReportingBody() instanceof Projectile){
                    c.getReportingBody().destroy();
                    if(c.getOtherBody() instanceof Player){
                        c.getReportingBody().destroy();
                        player.playerHealthHandler();
                    }
                }
            }
        };

        MagicBall.addCollisionListener(magicChecker);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
longRangedBoiss();  }
}
