package Main;
public class Game {
    private int level=1;
    private GameWindow gameWindow=null;
    public Game() {
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

    }
    public void SetLevel(int _level)
    {
        level=_level;
    }
    public int GetLevel()
    {
        return level;
    }

    public static void main(String[] args) {
        Game g= new Game();
        g.Start();
    }

}

