package Assets.Interactables;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

import java.awt.*;

public class InteractivePlatform extends StaticBody {
    final static Shape platformShape = new BoxShape(3, 0.5f);
    public InteractivePlatform(World w) {
        super(w,platformShape);
        setFillColor(new Color(50,50,50));
    }
}
