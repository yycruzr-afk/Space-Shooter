package com.yordin.rompe_bloques.entidades;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Balas {
    private Texture textura;
    private Rectangle hitBox;
    private float velocidad;

    public Balas(Texture textura, float x, float y) {
        this.textura = textura;
        hitBox = new Rectangle(x, y, textura.getWidth(), textura.getHeight());
        velocidad = 500;
    }

    public Balas(Texture textura, Rectangle hitBox, float velocidad) {
        this.textura = textura;
        this.hitBox = hitBox;
        this.velocidad = velocidad;
    }

    public void mover(float delta){
        hitBox.y += velocidad*delta;
    }

    public void dibujar(SpriteBatch batch){
        batch.draw(textura, hitBox.x, hitBox.y);
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
