package smartwatchsim;

import javax.swing.SwingUtilities;
import smartwatchsim.gui.Ventana;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            Ventana v = new Ventana();
            v.setLocationRelativeTo(null);
            v.setVisible(true);
        });

    }

}
