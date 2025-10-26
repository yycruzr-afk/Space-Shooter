package com.yordin.rompe_bloques;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yordin.rompe_bloques.entidades.Balas;
import com.yordin.rompe_bloques.entidades.Jugador;

import java.util.ArrayList;
import java.util.Iterator;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core implements ApplicationListener {

    float anchoPantalla;
    float alturaPantalla;

    SpriteBatch batch;
    float delta;

    Texture texturaPlayer;
    Jugador player;

    ArrayList<Balas> listaBalas;
    Texture balaTexture;

    @Override
    public void create() {
        anchoPantalla = Gdx.graphics.getWidth();
        alturaPantalla = Gdx.graphics.getHeight();

        batch = new SpriteBatch();
        delta = Gdx.graphics.getDeltaTime();

        texturaPlayer = new Texture("nave.png");
        player = new Jugador(texturaPlayer);

        listaBalas = new ArrayList<>();
        balaTexture = new Texture("bala.png");
    }

    @Override
    public void resize(int width, int height) {
        // If the window is minimized on a desktop (LWJGL3) platform, width and height are 0, which causes problems.
        // In that case, we don't resize anything, and wait for the window to be a normal size before updating.
        if(width <= 0 || height <= 0) return;

        // Resize your application here. The parameters represent the new window size.
    }

    @Override
    public void render() {
        logic();
        input();
        draw();
    }

    public void draw(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        player.dibujar(batch);

        for (Balas b : listaBalas){
            b.dibujar(batch);
        }

        batch.end();
    }
    public void logic(){
        delta = Gdx.graphics.getDeltaTime();
        player.mover(delta);

        Iterator<Balas> it = listaBalas.iterator();
        while (it.hasNext()){
            Balas b = it.next();
            b.mover(delta);

            if(b.getHitBox().y >= alturaPantalla){
                it.remove();
            }
        }

    }
    public void input(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            listaBalas.add(player.disparar(balaTexture));
        }

    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void dispose() {
        batch.dispose();
        texturaPlayer.dispose();
    }
}
