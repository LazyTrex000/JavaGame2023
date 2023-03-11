package Assets.Interactables;

import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class LevelBuilding {

    public LevelBuilding(World W) {

        Shape shape = new BoxShape(60, 20);
        StaticBody ground = new StaticBody(W, shape);
        ground.setPosition(new Vec2(40, -5));

        StaticBody ground2 = new StaticBody(W, shape);
        ground2.setPosition(new Vec2(100, -5));

        StaticBody ground3 = new StaticBody(W, shape);
        ground3.setPosition(new Vec2(20, -5));

        StaticBody Acid = new StaticBody(W);
        GhostlyFixture acid = new GhostlyFixture(Acid, new BoxShape(20,10),0);
        Acid.setPosition(new Vec2(0,-5));
        Acid.setFillColor(Color.pink);
       Shape platformShape = new BoxShape(3, 0.5f);
        StaticBody platform1 = new StaticBody(W, platformShape);
        platform1.setPosition(new Vec2(8f, 2f));

        StaticBody platform3 = new StaticBody(W, platformShape);
        platform3.setPosition(new Vec2(14f, 8f));

        StaticBody platform4 = new StaticBody(W, platformShape);
        platform4.setPosition(new Vec2(12f, 14f));


        StaticBody platform5 = new StaticBody(W, platformShape);
        platform5.setPosition(new Vec2(16f, 8f));

        StaticBody platform6 = new StaticBody(W, platformShape);
        platform6.setPosition(new Vec2(20f, 15f));


        StaticBody platform7 = new StaticBody(W, platformShape);
        platform7.setPosition(new Vec2(26f, 12f));

        StaticBody platform8 = new StaticBody(W, platformShape);
        platform8.setPosition(new Vec2(30f, 14f));

        Shape Boundaries = new BoxShape(2,30);
        StaticBody BoundStart = new StaticBody(W,Boundaries);
        BoundStart.addImage(new BodyImage("res/Enviroment/Sky.png"));
        StaticBody BoundEnd = new StaticBody(W,Boundaries);
        BoundEnd.addImage(new BodyImage("res/Enviroment/Sky.png"));

        BoundStart.setPosition(new Vec2(-20,0));
        BoundEnd.setPosition(new Vec2(300,-5));
    }

    public void movingPlatform(StaticBody Platform){

    }
}
