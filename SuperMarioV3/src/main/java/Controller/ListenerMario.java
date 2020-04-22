/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package Controller;

import Model.Mario;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Beat
 */
public class ListenerMario implements KeyListener {

    private final Mario mario;
    private final ThreadMario tMario;

    public ListenerMario(Mario mario, ThreadMario tMario) {
        this.mario = mario;
        this.tMario = tMario;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        return;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //PULSANTE PREMUTO
        tMario.setPulsantePremuto(e.getKeyCode());
        tMario.setPremuto(true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //DEVE SETTARE MARIO A STATO FERMo
        tMario.setPulsantePremuto(0);
        tMario.setPremuto(false);
        mario.setStato(Mario.FERMO);
    }
}
