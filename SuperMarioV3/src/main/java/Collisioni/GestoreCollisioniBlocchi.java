/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package Collisioni;

import Animazioni.BlockAnimations;
import Model.Mario;
import Model.Quadratino;

/**
 *
 * @author jacop
 */
public class GestoreCollisioniBlocchi {

    private final int FINEBLOCCO = 406;

    public int collisioneBasso(Mario mario, Quadratino[] quadratini, Quadratino[] puntiInt) {
        for (Quadratino q : quadratini) {
            if (mario.getX() + Mario.WIDTH >= q.getBordi().x && mario.getX() <= q.getBordi().x + Quadratino.WIDTH) {
                if (mario.getY() + Mario.HEIGHT >= q.getBordi().y && mario.getY() + Mario.HEIGHT <= q.getBordi().y + Quadratino.HEIGHT) {
                    return (int) (q.getBordi().getY() - Mario.HEIGHT);
                }
            }
        }
        
        for (Quadratino q : puntiInt) {
            if (mario.getX() + Mario.WIDTH >= q.getBordi().x && mario.getX() <= q.getBordi().x + Quadratino.WIDTH) {
                if (mario.getY() + Mario.HEIGHT >= q.getBordi().y && mario.getY() + Mario.HEIGHT <= q.getBordi().y + Quadratino.HEIGHT) {
                    return (int) (q.getBordi().getY() - Mario.HEIGHT);
                }
            }
        }
        return 0;
    }

    public boolean controllaCollisioni(Mario mario, Quadratino[] quadratini, Quadratino[] puntiInt, BlockAnimations blocchi) {
        for (Quadratino quadratini1 : quadratini) {
            if (mario.getBordi().intersects(quadratini1.getBordi())) {
                if (mario.getBordi().intersects(quadratini1.getBordi()) && mario.getY() > FINEBLOCCO) {
                    quadratini1.setTexture(blocchi.getMattoncinoRotto());
                }
                return true;
            }
        }
        for (Quadratino puntiInt1 : puntiInt) {
            if (mario.getBordi().intersects(puntiInt1.getBordi())) {
                if (mario.getBordi().intersects(puntiInt1.getBordi()) && mario.getY() > FINEBLOCCO) {
                    puntiInt1.setTexture(blocchi.getMattoncinoRotto());
                }
                return true;
            }
        }
        return false;
    }

    public String controllaCollisioniS(Mario mario, Quadratino[] quadratini, Quadratino[] puntiInt) {
        for (Quadratino quadratini1 : quadratini) {
            if (mario.getBordi().intersects(quadratini1.getBordi())) {
                return "Collisione";
            }
        }
        for (Quadratino puntiInt1 : puntiInt) {
            if (mario.getBordi().intersects(puntiInt1.getBordi())) {
                return "Collisione";
            }
        }
        return "Nessuna Collisione";
    }
}
