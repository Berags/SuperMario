/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package Controller;

import Animazioni.BlockAnimations;
import Model.Mario;
import Animazioni.MarioAnimations;
import Collisioni.GestoreCollisioniBlocchi;
import Model.LevelPane;
import View.PanelLivello;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

/**
 *
 * @author Beat
 */
public class ThreadMario extends Thread {

    private Mario mario;
    private MarioAnimations animations;
    private final BlockAnimations blocchi;
    private final GestoreCollisioniBlocchi collisioniBlocchi = new GestoreCollisioniBlocchi();

    private int V = 250;
    private final double UPDATE_CAP = 1d / 60d;
    private static final float FORZA_SALTO = 820f;
    private static final float GRAVITA = 450f;
    private float velY = 0;
    private int pulsantePremuto;
    private static final int SALTO_MASSIMO = 280;

    private boolean pulsante = false;
    private boolean primaVolta = true;
    private boolean running = true;
    private boolean render = true;
    private boolean premuto = false;
    private boolean salto = false;
    private boolean caduta = false;

    private LevelPane panelLivello;
    private Toolkit toolkit;
    private ListenerMario listenerMario;

    private double frameTime = 0;
    private double deltaT = 0;
    private int frames = 0;
    private int fps = 0;

    public ThreadMario(Mario mario, PanelLivello panelLivello) {
        this.mario = mario;
        this.panelLivello = panelLivello;
        this.velY = this.GRAVITA;
        listenerMario = new ListenerMario(mario, this);
        animations = new MarioAnimations(panelLivello, mario, this.toolkit);
        blocchi = new BlockAnimations();
        panelLivello.repaint();
    }

    @Override
    public void run() {
        double firstTime;
        double lastTime = System.nanoTime() / 1000000000d;
        double passedTime;
        double unprocessedTime = 0;
        deltaT = 0;

        while (running) {
            render = false;
            firstTime = System.nanoTime() / 1000000000d;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;
            //Il frame time deve essere circa 0.0166667 perché è il risultato di 1/60
            if (frameTime <= 0.016667 && frameTime >= 0.01666) {
                deltaT = frameTime;
            }

            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP;
                render = true;
                //Si controllano gli FPS
                if (frameTime >= 1d) {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if (render) {
                //Render del gioco
                movimento();
                panelLivello.repaint();
                frames++;
            }
        }
    }

    /*
        m' = m + V*DeltaT
        DeltaT = t' - t
        Caduta = Gravità * DeltaT
     */
    public void movimento() {
        //DIREZIONE -> 1 a DESTRA e -1 a SINISTRA
        if(mario.getX() <= 0)
            mario.setX(0);
        else if(mario.getX() >= panelLivello.getWidth())
            mario.setX(panelLivello.getWidth() - Mario.WIDTH - 2);
        if (salto) {
            cadutaDestraSinistra();
            movimentoCaduta(pulsantePremuto);

            if (collisioniBlocchi.controllaCollisioni(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt(), blocchi)) {
                mario.setX(mario.getX());
                mario.setStato(Mario.CADUTA);
                salto = false;
                caduta = true;
                return;
            }

            if (mario.getY() <= SALTO_MASSIMO) {
                mario.setStato(Mario.CADUTA);
                salto = false;
                caduta = true;
                return;
            }

            if (collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()) != 0) {
                mario.setY(collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()));
                mario.setStato(Mario.FERMO);
                salto = false;
                return;
            }

            mario.setY((float) ((float) mario.getY() - (FORZA_SALTO * deltaT)));
            return;
        }

        if (caduta) {
            cadutaDestraSinistra();
            movimentoCaduta(pulsantePremuto);

            if (collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()) != 0) {
                mario.setY(collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()));
                mario.setStato(Mario.FERMO);
                caduta = false;
                return;
            }

            if (mario.getY() >= LevelPane.YPAVIMENTO) {
                mario.setY(LevelPane.YPAVIMENTO);
                mario.setStato(Mario.FERMO);
                caduta = false;
                return;
            }

            mario.setY((float) (mario.getY() + (velY * deltaT)));
            return;
        }

        if (premuto) {
            switch (mario.getStato()) {
                case Mario.FERMO:
                case Mario.ABBASSATO:
                case Mario.MOVIMENTO_DESTRA:
                    movimentoDestraSinistra(pulsantePremuto);

                    if (collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()) == 0 && mario.getY() != LevelPane.YPAVIMENTO) {
                        caduta = true;
                        mario.setStato(Mario.CADUTA);
                    }

                    switch (pulsantePremuto) {
                        case KeyEvent.VK_RIGHT:
                            mario.setStato(Mario.MOVIMENTO_DESTRA);
                            break;
                        case KeyEvent.VK_LEFT:
                            mario.setStato(Mario.MOVIMENTO_SINISTRA);
                            break;
                        case KeyEvent.VK_UP:
                            mario.setStato(Mario.SALTO);
                            salto = true;
                            break;
                        case KeyEvent.VK_DOWN:
                            //mario.setStato(Mario.ABBASSATO);
                            break;
                        default:
                            mario.setStato(Mario.FERMO);
                            break;
                    }
                    break;
                case Mario.MOVIMENTO_SINISTRA:
                    movimentoDestraSinistra(pulsantePremuto);
                    
                    if (collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()) == 0 && mario.getY() != LevelPane.YPAVIMENTO) {
                        caduta = true;
                        mario.setStato(Mario.CADUTA);
                    }
                    
                    switch (pulsantePremuto) {
                        case KeyEvent.VK_RIGHT:
                            mario.setStato(Mario.MOVIMENTO_DESTRA);
                            break;
                        case KeyEvent.VK_LEFT:
                            mario.setStato(Mario.MOVIMENTO_SINISTRA);
                            break;
                        case KeyEvent.VK_UP:
                            mario.setStato(Mario.SALTO);
                            salto = true;
                            break;
                        case KeyEvent.VK_DOWN:
                            //mario.setStato(Mario.ABBASSATO);
                            break;
                        default:
                            mario.setStato(Mario.FERMO);
                            break;
                    }
                    break;
                case Mario.SALTO:
                    break;
            }
        }

//        if (premuto) {
//            switch (mario.getStato()) {
//                case Mario.SALTO:
//
//                    if (collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()) != 0) {
//                        mario.setY(collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()));
//                        mario.setStatoPrecedente(mario.getStato());
//                        mario.setStato(Mario.FERMO);
//                    }
//
//                    if (mario.getY() <= SALTO_MASSIMO) {
//                        mario.setStato(Mario.CADUTA);
//                        break;
//                    }
//
//                    cadutaDestraSinistra();
//                    movimentoCaduta(pulsantePremuto);
//
//                    mario.setY((float) ((float) mario.getY() - (FORZA_SALTO * deltaT)));
//                    break;
//                case Mario.CADUTA:
//
//                    cadutaDestraSinistra();
//                    movimentoCaduta(pulsantePremuto);
//                    mario.setY((float) (mario.getY() + (velY * deltaT)));
//
//                    if (mario.getY() >= PanelLivello.YPAVIMENTO) {
//                        mario.setY(PanelLivello.YPAVIMENTO);
//                        mario.setStato(Mario.FERMO);
//                        premuto = false;
//                    } else {
//                        mario.setStato(Mario.CADUTA);
//                        if (collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()) != 0) {
//                            mario.setY(collisioniBlocchi.collisioneBasso(mario, panelLivello.getQuadratini(), panelLivello.getPuntiInt()));
//                            mario.setStatoPrecedente(mario.getStato());
//                            mario.setStato(Mario.FERMO);
//                        } else {
//                            mario.setStato(Mario.CADUTA);
//                        }
//                    }
//                    break;
//                case Mario.FERMO:
//                case Mario.ABBASSATO:
//                case Mario.MOVIMENTO_DESTRA:
//                    mario.setY(mario.getY());
//
//                    if (pulsantePremuto == KeyEvent.VK_RIGHT) {
//                        movimentoDestraSinistra(pulsantePremuto);
//                    } else if (pulsantePremuto == KeyEvent.VK_LEFT && mario.getX() > 0) {
//                        movimentoDestraSinistra(pulsantePremuto);
//                    } else if (pulsantePremuto == KeyEvent.VK_UP) {
//                        //SALTO
//                        mario.setStatoPrecedente(mario.getStato());
//                        mario.setStato(Mario.SALTO);
//                    } else if (pulsantePremuto == KeyEvent.VK_DOWN) {
//                        //SI ABBASSA
//                        mario.setStatoPrecedente(mario.getStato());
//                        mario.setStato(Mario.ABBASSATO);
//                    }
//
//                    break;
//                case Mario.MOVIMENTO_SINISTRA:
//                    mario.setY(mario.getY());
//
//                    if (pulsantePremuto == KeyEvent.VK_RIGHT) {
//                        movimentoDestraSinistra(pulsantePremuto);
//                    } else if (pulsantePremuto == KeyEvent.VK_LEFT && mario.getX() > 0) {
//                        movimentoDestraSinistra(pulsantePremuto);
//                    } else if (pulsantePremuto == KeyEvent.VK_UP) {
//                        //SALTO
//                        mario.setStatoPrecedente(mario.getStato());
//                        mario.setStato(Mario.SALTO);
//                    } else if (pulsantePremuto == KeyEvent.VK_DOWN) {
//                        //SI ABBASSA
//                        mario.setStatoPrecedente(mario.getStato());
//                        mario.setStato(Mario.ABBASSATO);
//                    }
//
//                    break;
//            }
//        }
    }

    private void cadutaDestraSinistra() {
        if (mario.getStatoPrecedente() == Mario.MOVIMENTO_DESTRA) {
            mario.setX((int) (mario.getX() + (170 * Mario.MOVIMENTO_DESTRA) * deltaT));
        } else if (mario.getStatoPrecedente() == Mario.MOVIMENTO_SINISTRA) {
            mario.setX((int) (mario.getX() + (170 * Mario.MOVIMENTO_SINISTRA) * deltaT));
        }
    }

    private void movimentoCaduta(int keyPressed) {
        if (keyPressed == KeyEvent.VK_RIGHT) {
            mario.setX((int) (mario.getX() + (170 * Mario.MOVIMENTO_DESTRA) * deltaT));
        } else if (keyPressed == KeyEvent.VK_LEFT && mario.getX() > 0) {
            mario.setX((int) (mario.getX() + (170 * Mario.MOVIMENTO_SINISTRA) * deltaT));
        }
    }

    private void movimentoDestraSinistra(int keyPressed) {
        mario.setStatoPrecedente(mario.getStato());
        if (keyPressed == KeyEvent.VK_RIGHT) {
            mario.setStato(Mario.MOVIMENTO_DESTRA);
        } else {
            mario.setStato(Mario.MOVIMENTO_SINISTRA);
        }
        mario.setY(mario.getY());
        mario.setX((int) (mario.getX() + (V * mario.getStato()) * deltaT));
    }

    /*
     * *********************************
     * *        GETTER & SETTER        *
     * *********************************
     */
    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }

    public int getPulsantePremuto() {
        return pulsantePremuto;
    }

    public void setPulsantePremuto(int pulsantePremuto) {
        this.pulsantePremuto = pulsantePremuto;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public void setPanelLivello(PanelLivello panelLivello) {
        this.panelLivello = panelLivello;
    }

    public ListenerMario getListenerMario() {
        return listenerMario;
    }

    public void setListenerMario(ListenerMario listenerMario) {
        this.listenerMario = listenerMario;
    }

    public boolean isPulsante() {
        return pulsante;
    }

    public void setPulsante(boolean pulsante) {
        this.pulsante = pulsante;
    }

    public MarioAnimations getAnimations() {
        return animations;
    }

    public void setAnimations(MarioAnimations animations) {
        this.animations = animations;
    }

    public boolean isPrimaVolta() {
        return primaVolta;
    }

    public void setPrimaVolta(boolean primaVolta) {
        this.primaVolta = primaVolta;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRender() {
        return render;
    }

    public void setRender(boolean render) {
        this.render = render;
    }

    public Toolkit getToolkit() {
        return toolkit;
    }

    public void setToolkit(Toolkit toolkit) {
        this.toolkit = toolkit;
    }

    public double getFrameTime() {
        return frameTime;
    }

    public void setFrameTime(double frameTime) {
        this.frameTime = frameTime;
    }

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;
    }

    public boolean isPremuto() {
        return premuto;
    }

    public void setPremuto(boolean premuto) {
        this.premuto = premuto;
    }

    public double getDeltaT() {
        return deltaT;
    }

    public void setDeltaT(double deltaT) {
        this.deltaT = deltaT;
    }

    public int getV() {
        return V;
    }

    public void setV(int V) {
        this.V = V;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean isSalto() {
        return salto;
    }

    public void setSalto(boolean salto) {
        this.salto = salto;
    }

    public boolean isCaduta() {
        return caduta;
    }

    public void setCaduta(boolean caduta) {
        this.caduta = caduta;
    }

    public LevelPane getPanelLivello() {
        return panelLivello;
    }

    public void setPanelLivello(LevelPane panelLivello) {
        this.panelLivello = panelLivello;
    }

}
