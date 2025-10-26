package com.yordin.rompe_bloques;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.yordin.rompe_bloques.entidades.Balas;
import com.yordin.rompe_bloques.entidades.Enemigos;
import com.yordin.rompe_bloques.entidades.Jugador;

import java.util.ArrayList;
import java.util.Iterator;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Core implements ApplicationListener {

    float anchoPantalla;
    float alturaPantalla;

    ShapeRenderer shape;

    SpriteBatch batch;
    float delta;

    Texture texturaPlayer;
    Jugador player;

    ArrayList<Balas> listaBalas;
    Texture balaTexture;
    private float tiempoUltimoDisparo;
    private float intervaloDisparo;

    ArrayList<Enemigos> listaEnemigos;
    Texture enemigoTextura;
    private float ultimoEnemigoTiempo;
    private float enemigoIntervalo;


    @Override
    public void create() {
        anchoPantalla = Gdx.graphics.getWidth();
        alturaPantalla = Gdx.graphics.getHeight();

        shape = new ShapeRenderer();

        batch = new SpriteBatch();
        delta = Gdx.graphics.getDeltaTime();

        texturaPlayer = new Texture("nave.png");
        player = new Jugador(texturaPlayer);

        listaBalas = new ArrayList<>();
        balaTexture = new Texture("bala.png");
        tiempoUltimoDisparo = 0;
        intervaloDisparo = 0.3f;

        listaEnemigos = new ArrayList<>();
        enemigoTextura = new Texture("enemigo.png");
        ultimoEnemigoTiempo = 0;
        enemigoIntervalo = 1f;
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
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        player.dibujar(batch);
        for (Balas b : listaBalas) b.dibujar(batch);
        for(Enemigos e : listaEnemigos) e.dibujar(batch);
        batch.end();


        shape.begin(ShapeRenderer.ShapeType.Line);
        player.dibujarHitBox(shape);
        for(Balas b : listaBalas) b.dibujarHitbox(shape);
        for(Enemigos e : listaEnemigos) e.dibujarHitBox(shape);
        shape.end();
    }
    public void logic(){
        delta = Gdx.graphics.getDeltaTime();
        tiempoUltimoDisparo += delta;
        player.mover(delta);

        crearEnemigo();

        Iterator<Balas> it = listaBalas.iterator();
        while (it.hasNext()){
            Balas b = it.next();
            b.mover(delta);

            if(b.getHitBox().y >= alturaPantalla){
                it.remove();
            }
        }



        Iterator<Enemigos> itEnem = listaEnemigos.iterator();
        while (itEnem.hasNext()){
            Enemigos e = itEnem.next();
            e.mover(delta);

            if(e.getHitBox().y <= -1){
                itEnem.remove();
            }
        }

        colisionBalasEnemigos();
    }

    public void input(){
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            if(tiempoUltimoDisparo >= intervaloDisparo){
                listaBalas.add(player.disparar(balaTexture));
                tiempoUltimoDisparo = 0;
            }
        }

    }

    public void crearEnemigo(){
        ultimoEnemigoTiempo += delta;
        if(ultimoEnemigoTiempo >= enemigoIntervalo){
            float t = MathUtils.random(10, anchoPantalla - enemigoTextura.getWidth() - 10);
            listaEnemigos.add(new Enemigos(enemigoTextura, t));
            ultimoEnemigoTiempo = 0;
        }
    }

    public void colisionBalasEnemigos(){
        Iterator<Balas> itBalas = listaBalas.iterator();
        while(itBalas.hasNext()){
            Balas b = itBalas.next();

            Iterator<Enemigos> itEnemigos = listaEnemigos.iterator();
            while (itEnemigos.hasNext()){
                Enemigos e = itEnemigos.next();

                if(b.getHitBox().overlaps(e.getHitBox())){
                    itBalas.remove();
                    itEnemigos.remove();
                    break;
                }
            }
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
