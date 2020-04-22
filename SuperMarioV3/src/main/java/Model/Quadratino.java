/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package Model;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Beat
 */
public class Quadratino {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 48;

    private Rectangle dimensioni; //base e l'altezza
    private BufferedImage texture = null;
    private int base; //X + base
    private int lato; //y - Altezza
    //Indice 0 -> quello più a sinistra, Indice 1 -> quello più a destra
    private int[] yBase = new int[2];
    private int[] xLato = new int[2];

    public Quadratino() {
    }

    public Rectangle getDimensioni() {
        return dimensioni;
    }

    public void setDimensioni(Rectangle dimensioni) {
        this.dimensioni = dimensioni;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }

    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getLato() {
        return lato;
    }

    public void setLato(int lato) {
        this.lato = lato;
    }

    public int[] getyBase() {
        return yBase;
    }

    public void setyBase(int[] yBase) {
        this.yBase = yBase;
    }

    public int[] getxLato() {
        return xLato;
    }

    public void setxLato(int[] xLato) {
        this.xLato = xLato;
    }

    public Rectangle getBordi() {
        return new Rectangle(dimensioni.x, dimensioni.y, WIDTH, HEIGHT);
    }
}
