package Assets.Enemies;


import Assets.Player.Player;
import Main.Collisions;
import Main.Game;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnemyController implements ActionListener {
    Player player;
    Enemy enemy;
    Timer timer;
    float enemySpeed = 8;
    boolean RightDirection = true;
    boolean alreadyWalking = false;
    boolean AttackingLeft, AttackingRight = false;
    Vec2 InitialPosition;
    Vec2 WalkingDistance;
    Vec2 UpdatedPosition;
    Vec2 Offset;
    BodyImage Run, Attack, Death;


    public EnemyController(Enemy _enemy, Player _player) {
        enemy = _enemy;
        player = _player;
        timer = new Timer(500, this);
        timer.start();
        InitialPosition = enemy.getPosition();
        WalkingDistance = new Vec2(20, 0);
        UpdatedPosition = InitialPosition.add(WalkingDistance);
        Run = new BodyImage("res/Enemy/Run.gif", 19);
        Attack = new BodyImage("res/Enemy/Attack.gif", 19);
        Death = new BodyImage("res/Enemy/Death.gif", 19);
        Offset = new Vec2(0, 1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.setDelay(700);
        enemyMovement();
        timer.setDelay(700);
        if (AttackingLeft || AttackingRight) {
            enemyAttack();
        }
    }
    public void enemyMovement() {
        timer.start();
        enemy.removeAllImages();
        AttachedImage enemyRun = enemy.addImage(Run);
        enemyRun.setOffset(Offset);
        if (!RightDirection) {
            enemyRun.flipHorizontal();
        }

        if (player.getPosition().sub(enemy.getPosition()).x < -4 && player.getPosition().sub(enemy.getPosition()).x > -10 &&
                player.getPosition().sub(enemy.getPosition()).y < 8) {
            enemy.stopWalking();
            enemy.startWalking(-enemySpeed);
            AttackingLeft = false;
            AttackingRight = false;
            RightDirection = false;
        } else if (player.getPosition().sub(enemy.getPosition()).x < 10 && player.getPosition().sub(enemy.getPosition()).x > 4 &&
                player.getPosition().sub(enemy.getPosition()).y < 8) {
            enemy.stopWalking();
            enemy.startWalking(enemySpeed);
            AttackingLeft = false;
            AttackingRight = false;
            RightDirection = true;
        }
        //this checks if the player in in attackRange of the enemy
        else if (player.getPosition().sub(enemy.getPosition()).x < 0 && player.getPosition().sub(enemy.getPosition()).x > -4 &&
                player.getPosition().sub(enemy.getPosition()).y < 4) {
            enemy.stopWalking();
            AttackingLeft = true;
        } else if (player.getPosition().sub(enemy.getPosition()).x < 4 && player.getPosition().sub(enemy.getPosition()).x > 0
                && player.getPosition().sub(enemy.getPosition()).y < 4) {
            enemy.stopWalking();
            AttackingRight = true;
        } else if (enemy.getPosition().x > UpdatedPosition.x) {
            //System.out.println("current position out of range. current position " + enemy.getPosition().x);
            enemy.stopWalking();
            alreadyWalking = false;
            RightDirection = false;
            AttackingLeft = false;
            AttackingRight = false;
            if (!alreadyWalking) {
                enemy.startWalking(-enemySpeed);
                alreadyWalking = true;
            } else {
            }
        } else if (enemy.getPosition().x < InitialPosition.x) {
            enemy.stopWalking();
            alreadyWalking = false;
            RightDirection = true;
            if (!alreadyWalking) {
                enemy.startWalking(enemySpeed);
                alreadyWalking = true;
            } else {
            }
        } else {
            if (!alreadyWalking) {
                enemy.startWalking(enemySpeed);
                AttackingLeft = false;
                AttackingRight = false;
                RightDirection = true;
            }
        }

    }

    public void enemyAttack() {
        enemy.removeAllImages();
        AttachedImage enemyAttack = enemy.addImage(Attack);
        if (AttackingLeft) {
            enemyAttack.flipHorizontal();
            enemyAttack.setOffset(Offset);
        } else if (AttackingRight) {
            enemyAttack.setOffset(Offset);
        }

        if (AttackingLeft == true || AttackingRight == true) {
            player.playerHealthHandler();
        }
    }



    }

