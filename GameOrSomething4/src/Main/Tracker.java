package Main;

import Assets.Enemies.Enemy;
import Assets.Player.Player;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

public class Tracker implements StepListener {
    private GameView view;
    private Player player;
    public Tracker(GameView view, Player Plr) {
        this.player = Plr;
        this.view = view;

    }
    public void preStep(StepEvent e) {}
    public void postStep(StepEvent e) {
        view.setCentre(player.getPosition().add(new Vec2(0, 10)));
    }
}

