import javax.swing.*;
import java.awt.*;

public class PanelCarta extends JPanel {
    private ImageIcon imagen;

    public PanelCarta(String rutaImagen) {
        imagen = new ImageIcon(getClass().getResource("/imagenes/" + rutaImagen));
        setPreferredSize(new Dimension(300, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
}