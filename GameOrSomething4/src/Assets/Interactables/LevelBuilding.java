package Assets.Interactables;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LevelBuilding {
    Color Cyan, Black;
    public LevelBuilding(World W) {
        InteractivePlatform Platform = new InteractivePlatform(W);

        Cyan = new Color(0,100,100);
        Black = new Color(0,0,0);
        Shape shape = new BoxShape(60, 20);
        StaticBody ground = new StaticBody(W, shape);
        ground.setPosition(new Vec2(40, -5));
        ground.setFillColor(Cyan);

        StaticBody ground2 = new StaticBody(W, shape);
        ground2.setPosition(new Vec2(140, -5));
        ground2.setFillColor(Cyan);

        StaticBody ground3 = new StaticBody(W, shape);
        ground3.setPosition(new Vec2(220, 25));
        ground3.setFillColor(Cyan);

       Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(W, platformShape);
        platform1.setPosition(new Vec2(140,18));
        platform1.setFillColor(Black);

        StaticBody platform2= new StaticBody(W, platformShape);
        platform2.setPosition(new Vec2(145,23));
        platform2.setFillColor(Black);

        StaticBody platform3= new StaticBody(W, platformShape);
        platform3.setPosition(new Vec2(153,26));
        platform3.setFillColor(Black);

        StaticBody platform4 = new StaticBody(W, platformShape);
        platform4.setPosition(new Vec2(160,29));
        platform4.setFillColor(Black);

        StaticBody platform5 = new StaticBody(W, platformShape);
        platform5.setPosition(new Vec2(150,33));
        platform5.setFillColor(Black);

        StaticBody platform6 = new StaticBody(W, platformShape);
        platform6.setPosition(new Vec2(145,37));
        platform6.setFillColor(Black);

        StaticBody platform7 = new StaticBody(W, platformShape);
        platform7.setPosition(new Vec2(158,40));
        platform7.setFillColor(Black);

        StaticBody platform8 = new StaticBody(W, platformShape);
        platform8.setPosition(new Vec2(190,49));
        platform8.setFillColor(Black);

        StaticBody platform9 = new StaticBody(W, platformShape);
        platform9.setPosition(new Vec2(200,54));
        platform9.setFillColor(Black);

        StaticBody platform10 = new StaticBody(W,new BoxShape(10f,4.5f));
        platform10.setPosition(new Vec2(240,48));
        platform10.setFillColor(Cyan);

        DynamicBody Box = new DynamicBody(W, new BoxShape(2,2));
        Box.setFillColor(Black);
        Box.setPosition(new Vec2(215,64));

        Platform.setPosition(new Vec2(215,61));

        Shape Boundaries = new BoxShape(2,30);
        StaticBody BoundStart = new StaticBody(W,Boundaries);
        BoundStart.addImage(new BodyImage("res/Enviroment/Sky.jpg"));
        StaticBody BoundEnd = new StaticBody(W,Boundaries);
        BoundEnd.addImage(new BodyImage("res/Enviroment/Sky.jpg"));

        BoundStart.setPosition(new Vec2(-20,0));
        BoundEnd.setPosition(new Vec2(270,-5));
    }

}
