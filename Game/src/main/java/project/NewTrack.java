package project;

import processing.core.PImage;

public class NewTrack {

    static NewTrack instance;
    GameManager window;
    PImage trackImg = window.loadImage("Game/images/Track2.png");

    public static NewTrack getInstance(GameManager window) {
        if (instance == null) {
            instance = new NewTrack(window);
        }
        return instance;
    }
    private NewTrack(GameManager window){
        this.window = window;
    }

    public void draw(){
        window.image(trackImg, 0, 0, window.displayWidth, window.displayHeight);
    }


}
