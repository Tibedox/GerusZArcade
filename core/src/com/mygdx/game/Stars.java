package com.mygdx.game;

import static com.mygdx.game.GerusZArcade.SCR_HEIGHT;
import static com.mygdx.game.GerusZArcade.SCR_WIDTH;

public class Stars extends SpaceObject{
    public Stars(float y) {
        this.y = y;
        vy = -3;
        width = SCR_WIDTH;
        height = SCR_HEIGHT;
    }

    @Override
    void move() {
        super.move();
        if(y<-SCR_HEIGHT){
            y = SCR_HEIGHT;
        }
    }
}
