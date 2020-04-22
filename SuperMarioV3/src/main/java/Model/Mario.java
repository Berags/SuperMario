/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package Model;

import java.awt.Rectangle;

/**
 *
 * @author Beat
 */
public class Mario {

    public static final int WIDTH = 50;
    public static final int HEIGHT = 73;
    public static final int FERMO = 0;
    public static final int MOVIMENTO_DESTRA = 1;
    public static final int MOVIMENTO_SINISTRA = -1;
    public static final int ABBASSATO = 2;
    public static final int SALTO = 3;
    public static final int CADUTA = 4;
    public static final int QUADRATINO = 5;
    public static final int SALTO_QUADRATINO = 6;
    public static final int CADUTA_QUADRATINO = 7;

    private int stato;
    private int statoPrecedente;
    private int x;
    private float y;
    private boolean vivo;

    public Mario() {
        this.stato = FERMO;
        this.vivo = true;
    }

    public Mario(int x, float y) {
        this.x = x;
        this.y = y;
        this.stato = FERMO;
        this.statoPrecedente = FERMO;
        this.vivo = true;
    }

    public String getStatoString() {
        switch (this.stato) {
            case FERMO: return "FERMO"; 
            case MOVIMENTO_DESTRA:return "MOVIMENTO_DESTRA"; 
            case MOVIMENTO_SINISTRA:return "MOVIMENTO_SINISTRA"; 
            case ABBASSATO:return "ABBASSATO"; 
            case SALTO:return "SALTO"; 
            case CADUTA:return "CADUTA"; 
            case QUADRATINO:return "QUADRATINO"; 
            case SALTO_QUADRATINO:return "SALTO_QUADRATINO"; 
            case CADUTA_QUADRATINO:return "CADUTA_QUADRATINO"; 
            default: return null;
        }
    }

    public int getStato() {
        return stato;
    }

    public void setStato(int stato) {
        this.stato = stato;
    }

    public boolean isVivo() {
        return vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getStatoPrecedente() {
        return statoPrecedente;
    }

    public void setStatoPrecedente(int statoPrecedente) {
        this.statoPrecedente = statoPrecedente;
    }

    public Rectangle getBordi() {
        return new Rectangle(x, (int) y, WIDTH, HEIGHT);
    }
}
