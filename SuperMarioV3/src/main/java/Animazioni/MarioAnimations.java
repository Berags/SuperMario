/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package Animazioni;

import Model.Mario;
import View.PanelLivello;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Beat
 */
public class MarioAnimations {

    private Toolkit toolKit;
    private PanelLivello panelLivello;
    private Mario mario;

    private BufferedImage marioFermo = null;
    private BufferedImage marioFermoDestra = null;
    private BufferedImage marioFermoSinistra = null;
    private BufferedImage marioCamminataDestraUno = null;
    private BufferedImage marioCamminataDestraDue = null;
    private BufferedImage marioCamminataSinistraUno = null;
    private BufferedImage marioCamminataSinistraDue = null;
    private BufferedImage marioSaltoDestra = null;
    private BufferedImage marioSaltoSinistra = null;

    public MarioAnimations(PanelLivello panelLivello, Mario mario, Toolkit toolkit) {
        toolKit = Toolkit.getDefaultToolkit();
        this.mario = mario;
        this.panelLivello = panelLivello;
        try {
            marioFermo = ImageIO.read(getClass().getResourceAsStream("/mario_fermo.png"));
            marioFermoDestra = ImageIO.read(getClass().getResourceAsStream("/mario_fermodestra.png"));
            marioFermoSinistra = ImageIO.read(getClass().getResourceAsStream("/mario_fermosinistra.png"));
            marioCamminataDestraUno = ImageIO.read(getClass().getResourceAsStream("/mario_movimentodestrauno.png"));
            marioCamminataDestraDue = ImageIO.read(getClass().getResourceAsStream("/mario_movimentodestradue.png"));
            marioCamminataSinistraUno = ImageIO.read(getClass().getResourceAsStream("/mario_movimentosinistrauno.png"));
            marioCamminataSinistraDue = ImageIO.read(getClass().getResourceAsStream("/mario_movimentosinistradue.png"));
            marioSaltoDestra = ImageIO.read(getClass().getResourceAsStream("/mario_saltodestra.png"));
            marioSaltoSinistra = ImageIO.read(getClass().getResourceAsStream("/mario_saltosinistra.png"));
        } catch (IOException ex) {
            Logger.getLogger(MarioAnimations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BufferedImage getMarioFermo() {
        return marioFermo;
    }

    public BufferedImage getMarioFermoDestra() {
        return marioFermoDestra;
    }

    public void setMarioFermoDestra(BufferedImage marioFermoDestra) {
        this.marioFermoDestra = marioFermoDestra;
    }

    public BufferedImage getMarioFermoSinistra() {
        return marioFermoSinistra;
    }

    public void setMarioFermoSinistra(BufferedImage marioFermoSinistra) {
        this.marioFermoSinistra = marioFermoSinistra;
    }

    public Toolkit getToolKit() {
        return toolKit;
    }

    public void setToolKit(Toolkit toolKit) {
        this.toolKit = toolKit;
    }

    public PanelLivello getPanelLivello() {
        return panelLivello;
    }

    public void setPanelLivello(PanelLivello panelLivello) {
        this.panelLivello = panelLivello;
    }

    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }

    public BufferedImage getMarioCamminataDestraUno() {
        return marioCamminataDestraUno;
    }

    public void setMarioCamminataDestraUno(BufferedImage marioCamminataDestraUno) {
        this.marioCamminataDestraUno = marioCamminataDestraUno;
    }

    public BufferedImage getMarioCamminataDestraDue() {
        return marioCamminataDestraDue;
    }

    public void setMarioCamminataDestraDue(BufferedImage marioCamminataDestraDue) {
        this.marioCamminataDestraDue = marioCamminataDestraDue;
    }

    public BufferedImage getMarioCamminataSinistraUno() {
        return marioCamminataSinistraUno;
    }

    public void setMarioCamminataSinistraUno(BufferedImage marioCamminataSinistraUno) {
        this.marioCamminataSinistraUno = marioCamminataSinistraUno;
    }

    public BufferedImage getMarioCamminataSinistraDue() {
        return marioCamminataSinistraDue;
    }

    public void setMarioCamminataSinistraDue(BufferedImage marioCamminataSinistraDue) {
        this.marioCamminataSinistraDue = marioCamminataSinistraDue;
    }

    public BufferedImage getMarioSaltoDestra() {
        return marioSaltoDestra;
    }

    public void setMarioSaltoDestra(BufferedImage marioSaltoDestra) {
        this.marioSaltoDestra = marioSaltoDestra;
    }

    public BufferedImage getMarioSaltoSinistra() {
        return marioSaltoSinistra;
    }

    public void setMarioSaltoSinistra(BufferedImage marioSaltoSinistra) {
        this.marioSaltoSinistra = marioSaltoSinistra;
    }
}
