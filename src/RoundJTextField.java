import javax.swing.*;
import java.awt.*;

class RoundJTextField extends JTextField {

    public RoundJTextField(int size) {
        super(size);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    public void paintComponent(Graphics g) {
         g.setColor(getBackground());
         g.fillRoundRect(5, 0, getWidth()-10, getHeight()-10, 15, 15);
         super.paintComponent(g);
    }
    public void paintBorder(Graphics g) {
         g.setColor(getForeground());
         g.drawRoundRect(5, 0, getWidth()-10, getHeight()-10, 15, 15);
    }
  }