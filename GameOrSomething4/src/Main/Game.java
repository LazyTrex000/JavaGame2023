package Main;

/**
 * A world with some bodies.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    private int level=1;
    private GameWindow gameWindow=null;

    /** Initialise a new Main.Game. */
    public Game() {
    }
    public Game(int _level) {
        level=_level;
    }


    public void Start()
    {
        if(level==1)
        {
            // make the world##
            if(gameWindow!=null)
            {
                gameWindow.Stop();
            }

            gameWindow= new GameWindow(this);
            gameWindow.InitialiseView(gameWindow.world);
            gameWindow.world.start();
        }
        else if (level==2)
        {
            if(gameWindow!=null)
            {
                gameWindow.Stop();
            }
            // make the world
            gameWindow = new GameWindow(this);
            gameWindow.InitialiseView(gameWindow.world2);
            gameWindow.world2.start();
        }
        else if(level>2)
        {
            if(gameWindow!=null)
            {
                gameWindow.Stop();
            }
            System.out.println("invalid level. Game over");
        }

    }

    public void SetLevel(int _level)
    {
        level=_level;
    }

    public int GetLevel()
    {
        return level;
    }


    /** Run the game. */
    public static void main(String[] args) {
        Game g= new Game();
        g.Start();
    }

}

