package project;
import java.awt.*;
import processing.core.PVector;

public class Dashboard implements Drawable {

    GameManager window;
    Player player;
    int xpos;
    int ypos;


    public Dashboard(GameManager win, Player p, int x, int y) {
        window = win;
        player = p;
        xpos = x;
        ypos = y;
    }

    @Override
    public void draw() {
        window.textSize(40);
        Thread speedometer = new Thread(() -> {
            window.text(String.format("%d km/h", (int)player.getSpeed() * 5), (float) (window.displayWidth - 200), (float) (window.displayWidth / 30) + 60);
        });

        Thread revometer = new Thread(() -> {
            window.text(String.format("%d RPM", (int)player.getRevs()), (float) (window.displayWidth - 200), (float) (window.displayWidth / 30) + 100);
        });

        Thread gearshow = new Thread(() -> {
            window.text(String.format("Gear %d", player.getCurrGear()), (float) (window.displayWidth - 200), (float) (window.displayWidth / 30) + 140);
        });

        speedometer.start();
        try {
            speedometer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        revometer.start();
        try {
            revometer.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gearshow.start();
        try {
            gearshow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
