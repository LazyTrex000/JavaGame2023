package Main;

import Assets.Enemies.Enemy;
import Assets.Enemies.EnemyThrower;
import Assets.Interactables.LevelBuilding;
import Assets.Interactables.markers;
import Assets.Player.Player;
import Assets.Player.PlayerController;
import city.cs.engine.Shape;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow extends World{

    Player player;
    Sensor sensor1;
    SensorListener sensorListner;
 boolean inContact = false;
    PlayerController Pc;
    public World world;
    World world2;
    Collisions collider;
    EnemyThrower EC;

    /** A graphical display of the world (a specialised JPanel). */
    private GameView view;
    private SoundClip gameMusic;
    JFrame frame=null;
    public GameWindow(Game game) {
    world = new World();
    world2 = new World();
    PlayerCodes(game);
    NonPlayerCodes();
    levelObjectCodes();
    }
    public void Stop()
    {
        if(frame!=null) {
            frame.dispose();
        }
    }

    public void PlayerCodes(Game _game){

        player = new Player(world);
        player.addImage(new BodyImage("res/Player/Idle.gif", 7));
        player.isAlwaysOutline();
        player.setPosition(new Vec2(-10 ,10));
        Pc = new PlayerController(player);
        Pc.setPlayerMovement();

       /* if(_game.GetLevel()==1)
        {
            player.setFillColor(new Color(150,255,150));
            player.setPosition(new Vec2(5,0));
        }
        else {
            _game.GetLevel();
        } */

        collider = new Collisions(player,_game);
        player.addCollisionListener(collider);

        Shape sensorShape = new BoxShape(3,2);
        sensor1 = new Sensor(player,sensorShape);
        sensorListner = new SensorListener() {
            @Override
            public void beginContact(SensorEvent s) {
                player.SetContact(true);

                if(s.getContactBody() instanceof Enemy){
                    player.SetEnemy(s.getContactBody());
                }
            }
            @Override
            public void endContact(SensorEvent s) {
                player.SetContact(false);
                inContact = false;
            }
        };

        sensor1.addSensorListener(sensorListner);
    }
    public void NonPlayerCodes() {
        Enemy enemy = new Enemy(world);
        enemy.setPosition(new Vec2(100, -1));
        Enemy enemy1 = new Enemy(world);
        enemy1.setPosition(new Vec2(8, -1));
        Enemy enemy2 = new Enemy(world);
        enemy2.setPosition(new Vec2(18, -1));
        //   Enemy enemy3 = new Enemy(world);
        //   enemy3.setPosition(new Vec2(1, -1));
        //  enemy3.setFillColor(new Color(255,50,50));

        EC = new EnemyThrower(player, enemy2);
        //EnemyController EC2 = new EnemyController(enemy,player);

        markers door = new markers(world);
        door.setFillColor(new Color(50, 50, 255));
        door.setPosition(new Vec2(50, -5));
        door.setGravityScale(9000);



    }
    public void levelObjectCodes(){
        LevelBuilding level = new LevelBuilding(world);
    }
    public void InitialiseView(World _world)
    {
        // make a view
        view = new GameView(_world, 1152, 648);
        view.setZoom(17);
        PlayerController controller = new PlayerController(this.getPlayer());
        view.addKeyListener(controller);
        view.requestFocusInWindow();

        Tracker tracker = new Tracker(view, this.getPlayer());
        this.world.addStepListener(tracker) ;


        // add the view to a frame (Java top level window)
        frame = new JFrame("Game Level 1");
        frame.add(view);

        // enable the frame to quit the application
        // when the x button is pressed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.requestFocus();
        frame.addKeyListener(controller);
        frame.addWindowFocusListener(new WindowFocusListener(){
            @Override
            public void windowGainedFocus(WindowEvent e) {
            }

            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });
    }
    public Player getPlayer() {
        return player;
    }

    public void repaint() {
        frame.repaint();
    }
}



