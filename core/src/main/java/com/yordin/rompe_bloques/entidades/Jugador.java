package com.yordin.rompe_bloques.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Jugador {
    private Texture textura;
    private Rectangle hitBox;
    private float velocidad;

    public Jugador(Texture textura) {
        this.textura = textura;

        hitBox = new Rectangle();

        hitBox.width = textura.getWidth() /4f;
        hitBox.height = textura.getHeight() /4f;
        hitBox.x = (float) Gdx.graphics.getWidth() / 2;
        hitBox.y = (float) Gdx.graphics.getHeight()  /100;

        velocidad = 1000;
    }

    public Jugador(Texture textura, Rectangle hitBox, float velocidad) {
        this.textura = textura;
        this.hitBox = hitBox;
        this.velocidad = velocidad;
    }

    public void mover(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) hitBox.x += velocidad*delta;
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) hitBox.x -= velocidad*delta;
        limites();
    }

    private void limites(){
        if(hitBox.x + hitBox.width >= Gdx.graphics.getWidth()){
            hitBox.x = Gdx.graphics.getWidth() - hitBox.width - 1;
        }
        if(hitBox.x <= 0){
            hitBox.x = 1;
        }
    }

    public void dibujar(SpriteBatch batch){
        batch.draw(textura, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public Balas disparar(Texture texturaBala){
        return new Balas(texturaBala, hitBox.x, hitBox.y);
    }

    public void dibujarHitBox(ShapeRenderer shape){
        shape.setColor(Color.GREEN);
        shape.rect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public Texture getTextura() {
        return textura;
    }

    public void setTextura(Texture textura) {
        this.textura = textura;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setHitBox(Rectangle hitBox) {
        this.hitBox = hitBox;
    }
}
