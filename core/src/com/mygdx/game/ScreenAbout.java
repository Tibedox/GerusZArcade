package com.mygdx.game;

import static com.mygdx.game.GerusZArcade.SCR_HEIGHT;
import static com.mygdx.game.GerusZArcade.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenAbout implements Screen {
    GerusZArcade gerusZArcade;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont font;

    Texture imgBackGround;

    SpaceButton btnBack;
    String textAbout = "Это супер игра.\n" +
            "В неё можно играть.\n"+
            "А можно не играть";

    public ScreenAbout(GerusZArcade gerusZArcade) {
        this.gerusZArcade = gerusZArcade;
        batch = gerusZArcade.batch;
        camera = gerusZArcade.camera;
        touch = gerusZArcade.touch;
        font = gerusZArcade.fontLarge;

        imgBackGround = new Texture("space2.png");

        btnBack = new SpaceButton("Back", 300, 550, font);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // касания
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);

            if(btnBack.hit(touch.x, touch.y)){
                gerusZArcade.setScreen(gerusZArcade.screenMenu);
            }
        }

        // события

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch, textAbout, 100, 1200);
        font.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        imgBackGround.dispose();
    }
}
