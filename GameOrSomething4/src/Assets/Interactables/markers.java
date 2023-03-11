package Assets.Interactables;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
public class markers extends Walker {
    public static final Shape ExitLevel = new BoxShape(1.5f ,2.5f);
public static final BodyImage portal = new BodyImage("res/Enviroment/Portal.png",6);

     public markers(World w){
         super(w, ExitLevel);
        addImage(portal).setOffset(new Vec2(0,2));

     }
}
