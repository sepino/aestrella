package presentacion.swingGUI;

import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {
    private JPanel pnlPrincipal;

    public Ventana(String title) {
        super(title);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.pnlPrincipal = new JPanel();
        this.setContentPane(pnlPrincipal);

        this.setLocationRelativeTo(null);
        this.setPreferredSize(new Dimension(200, 200));
        this.pack();
    }
}
