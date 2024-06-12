package com.mygdx.game;

import static com.mygdx.game.GerusZArcade.SCR_HEIGHT;

public class Shot extends SpaceObject{
    public Shot(Ship ship) {
        width = height = 200;
        x = ship.x;
        y = ship.y;
        vy = 12;
    }

    boolean outOfScreen() {
        return y > SCR_HEIGHT+height/2;
    }
}
