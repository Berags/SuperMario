/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package View;

import Animazioni.BlockAnimations;
import Collisioni.GestoreCollisioniBlocchi;
import Controller.ThreadMario;
import Model.LevelPane;
import Model.Mario;
import Model.Quadratino;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author Beat
 */
public class PanelLivello extends LevelPane {
    private Mario mario;
    private BufferedImage sfondo = null;
    private Toolkit toolKit = Toolkit.getDefaultToolkit();
    private GestoreCollisioniBlocchi collisioniBlocchi = new GestoreCollisioniBlocchi();
    private ThreadMario tMario;
    private boolean primaVolta = true;
    private int panelWidth;
    private int panelHeight;
    private static final int OFFSET_QUADRATINI = 685;
    public static final int YPAVIMENTOQUADRATINO = 333;
    

    private Quadratino[] quadratini = new Quadratino[3];
    private Quadratino[] puntiInt = new Quadratino[4];

    public PanelLivello() {
        setDoubleBuffered(true);
        setFocusable(true);
        mario = new Mario();
        tMario = new ThreadMario(mario, this);
        super.setMario(mario);
        super.settMario(tMario);
        this.addKeyListener(tMario.getListenerMario());

        try {
            sfondo = ImageIO.read(getClass().getResourceAsStream("/Livello1.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            quadratini[i] = new Quadratino();

            switch (i) {
                case 0:
                    quadratini[i].setDimensioni(new Rectangle(OFFSET_QUADRATINI, 405, Quadratino.WIDTH, Quadratino.HEIGHT));
                    break;
                case 1:
                    quadratini[i].setDimensioni(new Rectangle(OFFSET_QUADRATINI + (2 * Quadratino.WIDTH), 405, Quadratino.WIDTH, Quadratino.HEIGHT));
                    break;
                case 2:
                    quadratini[i].setDimensioni(new Rectangle(OFFSET_QUADRATINI + (4 * Quadratino.WIDTH), 405, Quadratino.WIDTH, Quadratino.HEIGHT));
                    break;
            }

            try {
                quadratini[i].setTexture(ImageIO.read(getClass().getResourceAsStream("/mattoncino2.png")));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        for (int i = 0; i < 4; i++) {
            puntiInt[i] = new Quadratino();

            switch (i) {
                case 0:
                    puntiInt[i].setDimensioni(new Rectangle(485, 405, Quadratino.WIDTH, Quadratino.HEIGHT));
                    break;
                case 1:
                    puntiInt[i].setDimensioni(new Rectangle(OFFSET_QUADRATINI + Quadratino.WIDTH, 405, Quadratino.WIDTH, Quadratino.HEIGHT));
                    break;
                case 2:
                    puntiInt[i].setDimensioni(new Rectangle(OFFSET_QUADRATINI + (2 * Quadratino.WIDTH), 210, Quadratino.WIDTH, Quadratino.HEIGHT));
                    break;
                case 3:
                    puntiInt[i].setDimensioni(new Rectangle(OFFSET_QUADRATINI + (3 * Quadratino.WIDTH), 405, Quadratino.WIDTH, Quadratino.HEIGHT));
                    break;
            }

            try {
                puntiInt[i].setTexture(ImageIO.read(getClass().getResourceAsStream("/mattoncino.png")));
            } catch (IOException ex) {

            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        super.setG2((Graphics2D) g);

        disegnaSfondo();

        if (primaVolta) {
            tMario.start();
            mario.setX(getWidth() / 2);
            mario.setY(YPAVIMENTO);
            primaVolta = false;
        }

        for (int i = 0; i < 3; i++) {//mattoncini
            switch (i) {
                case 0:
                    super.getG2().drawImage(quadratini[i].getTexture(), 685, 405, Quadratino.WIDTH, Quadratino.HEIGHT, this);
                    break;
                case 1:
                    super.getG2().drawImage(quadratini[i].getTexture(), 685 + (2 * Quadratino.WIDTH), 405, Quadratino.WIDTH, Quadratino.HEIGHT, this);
                    break;
                case 2:
                    super.getG2().drawImage(quadratini[i].getTexture(), 685 + (4 * Quadratino.WIDTH), 405, Quadratino.WIDTH, Quadratino.HEIGHT, this);
                    break;
            }
        }

        for (int i = 0; i < 4; i++) {//puntiInterrogativi
            switch (i) {
                case 0:
                    super.getG2().drawImage(puntiInt[i].getTexture(), 485, 405, Quadratino.WIDTH, Quadratino.HEIGHT, this);
                    break;
                case 1:
                    super.getG2().drawImage(puntiInt[i].getTexture(), 685 + Quadratino.WIDTH, 405, Quadratino.WIDTH, Quadratino.HEIGHT, this);
                    break;
                case 2:
                    super.getG2().drawImage(puntiInt[i].getTexture(), 685 + (2 * Quadratino.WIDTH), 210, Quadratino.WIDTH, Quadratino.HEIGHT, this);
                    break;
                case 3:
                    super.getG2().drawImage(puntiInt[i].getTexture(), 685 + (3 * Quadratino.WIDTH), 405, Quadratino.WIDTH, Quadratino.HEIGHT, this);
                    break;
            }
        }

        super.animazioniMario();
        debugMode();
    }

    private void debugMode() {
        super.getG2().setFont(new Font("Arial", Font.PLAIN, 13));
        super.getG2().setColor(Color.BLACK);
        super.getG2().drawString("FPS: " + tMario.getFps(), 10, 10);
        super.getG2().drawString("Mario X: " + mario.getX(), 10, 25);
        super.getG2().drawString("Mario Y: " + mario.getY(), 10, 40);
        super.getG2().drawString("Mario STATO: " + mario.getStatoString(), 10, 55);
        super.getG2().drawString("Y PAVIMENTO: " + YPAVIMENTO, 10, 70);
        super.getG2().drawString("Collisione: " + collisioniBlocchi.controllaCollisioniS(mario, quadratini, puntiInt), 10, 85);
        super.getG2().drawOval(mario.getX(), (int) mario.getY(), Mario.WIDTH, Mario.HEIGHT);
    }

    private void disegnaSfondo() {
        super.getG2().drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
    }

    @Override
    public Graphics2D getG2() {
        return super.getG2();
    }

    @Override
    public Quadratino[] getQuadratini() {
        return quadratini;
    }

    @Override
    public void setQuadratini(Quadratino[] quadratini) {
        this.quadratini = quadratini;
    }

    @Override
    public Quadratino[] getPuntiInt() {
        return puntiInt;
    }

    @Override
    public void setPuntiInt(Quadratino[] puntiInt) {
        this.puntiInt = puntiInt;
    }
}
