package components.ui;

import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class NumericTextField extends JTextField {
	
    public NumericTextField() {
    	
    	setMargin(new Insets(0, 10, 0, 0));
    	
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE && c != '.') {
                    e.consume();
                }
            }
        });
    }
}
