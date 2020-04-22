/*
 * Super Mario Bros in JAVA
 * Progetto di Beragnoli Jacopo & Del Moro Jacopo
 */
package View;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Beat
 */
public class Livello extends JFrame {

    private PanelLivello panelLivello = new PanelLivello();

    public Livello() {
        super("Super Mario - Beragnoli & Del Moro");
        add(panelLivello);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1280, 720);
        setResizable(false);
        centeredFrame(this);
    }

    private void centeredFrame(javax.swing.JFrame objFrame) {
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - objFrame.getWidth()) / 2;
        int iCoordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(iCoordX, iCoordY);
    }

    public PanelLivello getPanelLivello() {
        return panelLivello;
    }

    public void setPanelLivello(PanelLivello panelLivello) {
        this.panelLivello = panelLivello;
    }
}
