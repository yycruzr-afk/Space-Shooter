package com.yordin.rompe_bloques.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import sun.awt.OverrideNativeWindowHandle;

public class Enemigos {
    private Texture textura;
    private Rectangle hitBox;
    private float velocidad;

    public Enemigos(Texture textura, float x) {
        this.textura = textura;
        hitBox = new Rectangle(x, Gdx.graphics.getHeight() + 10, textura.getWidth(), textura.getHeight());
        velocidad = 100;

        hitBox.width = textura.getWidth() / 6f;
        hitBox.height = textura.getHeight() / 6f;
    }

    public Enemigos(Texture textura, Rectangle hitBox, float velocidad) {
        this.textura = textura;
        this.hitBox = hitBox;
        this.velocidad = velocidad;
    }

    public void mover(float delta){
        hitBox.y -= velocidad*delta;
    }

    public void dibujar(SpriteBatch batch){
        batch.draw(textura, hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }

    public void dibujarHitBox(ShapeRenderer shape){
        shape.setColor(Color.RED);
        shape.rect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
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

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }
}
