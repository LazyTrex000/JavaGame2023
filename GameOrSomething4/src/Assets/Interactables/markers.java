package Assets.Interactables;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class markers extends Walker {
    public static final Shape ExitLevel = new BoxShape(1.5f ,2.5f);

     public markers(World w){
         super(w, ExitLevel);

     }
}
