package Assets.Enemies;

import city.cs.engine.*;

public class Projectile extends DynamicBody {
    static final Shape MagicBall = new CircleShape(0.5F);
    static final BodyImage MagicBallimage = new BodyImage("res/Enemy/MagicBall.png",3);
    public Projectile(World world){
        super(world, MagicBall);
   addImage(MagicBallimage);
    }
}
