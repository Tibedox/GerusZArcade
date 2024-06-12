package com.mygdx.game;

import static com.mygdx.game.GerusZArcade.SCR_HEIGHT;
import static com.mygdx.game.GerusZArcade.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class ScreenSettings implements Screen {
    GerusZArcade gerusZArcade;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont fontLarge, fontSmall;

    Texture imgBackGround;

    SpaceButton btnName;
    SpaceButton btnSound;
    SpaceButton btnClearRecords;
    SpaceButton btnBack;

    InputKeyboard keyboard;
    boolean isKeyboardUse;

    public ScreenSettings(GerusZArcade gerusZArcade) {
        this.gerusZArcade = gerusZArcade;
        batch = gerusZArcade.batch;
        camera = gerusZArcade.camera;
        touch = gerusZArcade.touch;
        fontLarge = gerusZArcade.fontLarge;
        fontSmall = gerusZArcade.fontSmall;

        imgBackGround = new Texture("space3.png");

        loadSettings();
        btnName = new SpaceButton("Name: "+ gerusZArcade.playerName, 100, 1000, fontLarge);
        btnSound = new SpaceButton(gerusZArcade.isSoundOn ? "Sound ON" : "Sound OFF", 100, 850, fontLarge);
        btnClearRecords = new SpaceButton("Clear Records", 100, 700, fontLarge);
        btnBack = new SpaceButton("Back", 100, 550, fontLarge);

        keyboard = new InputKeyboard(fontSmall, SCR_WIDTH, SCR_HEIGHT/2, 8);
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

            if(isKeyboardUse){
                if (keyboard.endOfEdit(touch.x, touch.y)) {
                    gerusZArcade.playerName = keyboard.getText();
                    isKeyboardUse = false;
                    btnName.setText("Name: "+ gerusZArcade.playerName);
                }
            } else {
                if (btnName.hit(touch.x, touch.y)) {
                    isKeyboardUse = true;
                }
                if (btnSound.hit(touch.x, touch.y)) {
                    gerusZArcade.isSoundOn = !gerusZArcade.isSoundOn;
                    btnSound.setText(gerusZArcade.isSoundOn ? "Sound ON" : "Sound OFF");
                }
                if (btnClearRecords.hit(touch.x, touch.y)) {
                    gerusZArcade.screenGame.clearRecords();
                    btnClearRecords.setText("Records Cleared");
                }
                if (btnBack.hit(touch.x, touch.y)) {
                    gerusZArcade.setScreen(gerusZArcade.screenMenu);
                }
            }
        }

        // события

        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBackGround, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        fontLarge.draw(batch, btnName.text, btnName.x, btnName.y);
        fontLarge.draw(batch, btnSound.text, btnSound.x, btnSound.y);
        fontLarge.draw(batch, btnClearRecords.text, btnClearRecords.x, btnClearRecords.y);
        fontLarge.draw(batch, btnBack.text, btnBack.x, btnBack.y);
        if(isKeyboardUse){
            keyboard.draw(batch);
        }
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
        btnClearRecords.setText("Clear Records");
        saveSettings();
    }

    @Override
    public void dispose() {
        imgBackGround.dispose();
        keyboard.dispose();
    }

    private void saveSettings(){
        Preferences prefs = Gdx.app.getPreferences("SunArcadeSettings");
        prefs.putBoolean("sound", gerusZArcade.isSoundOn);
        prefs.putString("name", gerusZArcade.playerName);
        prefs.flush();
    }

    private void loadSettings(){
        Preferences prefs = Gdx.app.getPreferences("SunArcadeSettings");
        if(prefs.contains("sound")) gerusZArcade.isSoundOn = prefs.getBoolean("sound");
        if(prefs.contains("name")) gerusZArcade.playerName = prefs.getString("name");
    }
}
