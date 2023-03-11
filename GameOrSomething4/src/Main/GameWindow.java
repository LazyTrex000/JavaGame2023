package Main;

import Assets.Enemies.Enemy;
import Assets.Enemies.EnemyController;
import Assets.Enemies.EnemyThrower;
import Assets.Interactables.LevelBuilding;
import Assets.Interactables.markers;
import Assets.Player.Player;
import Assets.Player.PlayerController;
import city.cs.engine.Shape;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class GameWindow extends World{

    Player player;
    Sensor sensor1;
    SensorListener sensorListner;
 boolean inContact = false;
    PlayerController Pc;
    public World world, world2, TutLevel;
    Collisions collider;
    private GameView view;
    private SoundClip audio;
    JFrame frame=null;
    public GameWindow(Game game) {

        world = new World();
        world2 = new World();
        TutLevel = new World();

    PlayerCodes(game);
    NonPlayerCodes();
    levelObjectCodes();
    loadAudio();

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
        Pc = new PlayerController(player);
        Pc.setPlayerMovement();

       if(_game.GetLevel()==1)
        {
            player.setPosition(new Vec2(0,10));
        }
        else {
            _game.GetLevel();
        }

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
       enemy.setPosition(new Vec2(120, -1));
        Enemy enemy1 = new Enemy(world);
        enemy1.setPosition(new Vec2(70, -1));
        Enemy enemy2 = new Enemy(world);
        enemy2.setPosition(new Vec2(50, -1));
        Enemy enemy3 = new Enemy(world);
        enemy3.setPosition(new Vec2(30, -1));
        Enemy enemy4 = new Enemy(world);
        enemy4.setPosition(new Vec2(190,50));
        Enemy enemy5 = new Enemy(world);
        enemy5.setPosition(new Vec2(180,50));


        EnemyThrower ET1 = new EnemyThrower(player, enemy3);
        EnemyThrower ET2 = new EnemyThrower(player,enemy);
        EnemyThrower ET3 = new EnemyThrower(player,enemy5);

        EnemyController EC1 = new EnemyController(enemy1,player);
        EnemyController EC2 = new EnemyController(enemy2,player);
        EnemyController EC3 = new EnemyController(enemy4,player);

        markers door = new markers(world);
        door.setFillColor(new Color(50, 50, 255));
        door.setPosition(new Vec2(240,48));
        door.setGravityScale(9000);
    }
    public void levelObjectCodes(){
        LevelBuilding level = new LevelBuilding(world);
        StaticBody MessageBox = new StaticBody(world2,new BoxShape(576,324));
        MessageBox.addImage(new BodyImage("res/Enviroment/Text.png",20));

        StaticBody InstructionSheet = new StaticBody(world);
        GhostlyFixture instrucions = new GhostlyFixture(InstructionSheet,new BoxShape(288,162));
        InstructionSheet.addImage(new BodyImage("res/Enviroment/Instructions.png",20));
        InstructionSheet.setPosition(new Vec2(-10,30));
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

    public void loadAudio(){
        try {
            audio = new SoundClip("res/Audio/continue.au");
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        audio.loop();

    }

    public Player getPlayer() {
        return player;
    }

    public void repaint() {
        frame.repaint();
    }
}



