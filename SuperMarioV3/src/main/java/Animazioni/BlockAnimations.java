/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package Animazioni;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Beat
 */
public class BlockAnimations {

    private BufferedImage mattoncinoRotto = null;

    public BlockAnimations() {
        try {
            mattoncinoRotto = ImageIO.read(getClass().getResource("/Mattoncino_Rotto.png"));
        } catch (IOException ex) {
            Logger.getLogger(BlockAnimations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BufferedImage getMattoncinoRotto() {
        return mattoncinoRotto;
    }

    public void setMattoncinoRotto(BufferedImage mattoncinoRotto) {
        this.mattoncinoRotto = mattoncinoRotto;
    }
}
