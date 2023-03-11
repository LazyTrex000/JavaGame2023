package Assets.Enemies;

import city.cs.engine.*;


public class Enemy extends Walker {
    public static final Shape TestenemyShape = new BoxShape(1, 2);
    public Enemy(World world) {
        super(world, TestenemyShape);


    }
}
