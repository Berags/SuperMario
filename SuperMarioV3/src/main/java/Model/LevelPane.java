/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package Model;

import Controller.ThreadMario;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author jacop
 */
public class LevelPane extends JPanel {

    private Graphics2D g2;
    private ThreadMario tMario;
    private Mario mario;
    private int statoPrecedenteMario;
    public static final int YPAVIMENTO = 525;
    private int frames = 0;

    private Quadratino[] quadratini = new Quadratino[3];
    private Quadratino[] puntiInt = new Quadratino[4];

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void marioFermo() {
        g2.drawImage(tMario.getAnimations().getMarioFermo(), mario.getX(), (int) mario.getY(), 50, 73, this);
    }

    public void animazioniMario() {
        switch (mario.getStato()) {
            case Mario.SALTO:
                salto();
                break;
            case Mario.FERMO:
                g2.drawImage(tMario.getAnimations().getMarioFermo(), mario.getX(), (int) mario.getY(), 50, 73, this);
                break;
            case Mario.MOVIMENTO_DESTRA:
                movimentoDestra();
                break;
            case Mario.MOVIMENTO_SINISTRA:
                movimentoSinistra();
                break;
            case Mario.ABBASSATO:
                g2.drawImage(tMario.getAnimations().getMarioFermo(), mario.getX(), (int) mario.getY(), 50, 73, this);
                break;
            case Mario.CADUTA:
                salto();
                break;
        }
    }

    private void salto() {
        if (mario.getStatoPrecedente() != Mario.CADUTA || mario.getStatoPrecedente() != Mario.ABBASSATO || mario.getStatoPrecedente() != Mario.SALTO) {
            statoPrecedenteMario = mario.getStatoPrecedente();
        }

        if (statoPrecedenteMario == Mario.MOVIMENTO_DESTRA || statoPrecedenteMario == Mario.FERMO) {
            g2.drawImage(tMario.getAnimations().getMarioSaltoDestra(), mario.getX(), (int) mario.getY(), 50, 73, this);
        } else if (statoPrecedenteMario == Mario.MOVIMENTO_SINISTRA) {
            g2.drawImage(tMario.getAnimations().getMarioSaltoSinistra(), mario.getX(), (int) mario.getY(), 50, 73, this);
        }
    }

    private void movimentoDestra() {
        if (this.frames >= 15) {
            this.frames = 0;
        }
        if (this.frames < 5) {
            g2.drawImage(tMario.getAnimations().getMarioFermoDestra(), mario.getX(), (int) mario.getY(), 50, 73, this);
        } else if (this.frames >= 5 && this.frames < 10) {
            g2.drawImage(tMario.getAnimations().getMarioCamminataDestraDue(), mario.getX(), (int) mario.getY() - 10, 50, 73, this);
        } else {
            g2.drawImage(tMario.getAnimations().getMarioCamminataDestraUno(), mario.getX(), (int) mario.getY() - 10, 50, 73, this);
        }
        frames++;
    }

    private void movimentoSinistra() {
        if (this.frames >= 15) {
            this.frames = 0;
        }
        if (this.frames < 5) {
            g2.drawImage(tMario.getAnimations().getMarioFermoSinistra(), mario.getX(), (int) mario.getY(), 50, 73, this);
        } else if (this.frames >= 5 && this.frames < 10) {
            g2.drawImage(tMario.getAnimations().getMarioCamminataSinistraDue(), mario.getX(), (int) mario.getY() - 10, 50, 73, this);
        } else {
            g2.drawImage(tMario.getAnimations().getMarioCamminataSinistraUno(), mario.getX(), (int) mario.getY() - 10, 50, 73, this);
        }
        frames++;
    }

    public Quadratino[] getQuadratini() {
        return quadratini;
    }

    public void setQuadratini(Quadratino[] quadratini) {
        this.quadratini = quadratini;
    }

    public Quadratino[] getPuntiInt() {
        return puntiInt;
    }

    public void setPuntiInt(Quadratino[] puntiInt) {
        this.puntiInt = puntiInt;
    }

    public ThreadMario gettMario() {
        return tMario;
    }

    public void settMario(ThreadMario tMario) {
        this.tMario = tMario;
    }

    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }

    public Graphics2D getG2() {
        return g2;
    }

    public void setG2(Graphics2D g2) {
        this.g2 = g2;
    }
}
