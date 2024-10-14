package components.ui;

import javax.swing.*;
import java.awt.*;

public class IconButton extends JButton {

    public IconButton(String text, String iconPath) {
        super(text);  

        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));

        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(resizedImage));

        setHorizontalTextPosition(SwingConstants.RIGHT); 
        setVerticalTextPosition(SwingConstants.CENTER);   
        setCursor(new Cursor(Cursor.HAND_CURSOR));      
    }
}
