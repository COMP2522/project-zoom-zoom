package project;

public class Dashboard implements Drawable {

    private GameManager window;
    private Player player;
    private Player player2;

    /**
     * Dashboard constructor for one player game.
     *
     * @param win window of screen.
     * @param p player1
     */
    public Dashboard(GameManager win, Player p) {
        window = win;
        player = p;
    }

    /**
     * Dashboard constructor for two players.
     *
     * @param win window of screen
     * @param p1 player1
     * @param p2 player2
     */
    public Dashboard(GameManager win, Player p1, Player p2) {
        window = win;
        player = p1;
        player2 = p2;
    }

    @Override
    public void draw() {
        window.textSize(40);
        Thread speedometer = new Thread(() -> {
            window.fill(255,255,255);
            window.text(String.format("%d km/h", (int)player.getSpeed() * 5), (float) (window.displayWidth - 200), (float) (window.displayWidth / 30) + 60);
        });

        Thread revometer = new Thread(() -> {
            window.fill(255,255,255);
            window.text(String.format("%d RPM", (int)player.getRevs()), (float) (window.displayWidth - 200), (float) (window.displayWidth / 30) + 100);
        });

        Thread gearshow = new Thread(() -> {
            window.fill(255,255,255);
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

    public void drawTwoPlayer() {
        window.textSize(40);
        Thread speedometer2 = new Thread(() -> {
            window.fill(255,255,255);
            window.text(String.format("%d km/h", (int)player2.getSpeed() * 5), (float) (window.displayWidth - 200), (float) (window.displayWidth / 30) + 560);
        });

        Thread revometer2 = new Thread(() -> {
            window.fill(255,255,255);
            window.text(String.format("%d RPM", (int)player2.getRevs()), (float) (window.displayWidth - 200), (float) (window.displayWidth / 30) + 600);
        });

        Thread gearshow2 = new Thread(() -> {
            window.fill(255,255,255);
            window.text(String.format("Gear %d", player2.getCurrGear()), (float) (window.displayWidth - 200), (float) (window.displayWidth / 30) + 640);
        });

        speedometer2.start();
        try {
            speedometer2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        revometer2.start();
        try {
            revometer2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        gearshow2.start();
        try {
            gearshow2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
