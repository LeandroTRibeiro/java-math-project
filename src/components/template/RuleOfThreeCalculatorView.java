package components.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.ui.NumericTextField;

public class RuleOfThreeCalculatorView extends JPanel {
    
    private JTextField _value1;
    private JTextField _value2;
    private JTextField _value3;
    private JTextField _value4;
    
    private ITriFunction<Double, Double, Double, Double> _calculationFunction;
    
    public RuleOfThreeCalculatorView(
            ITriFunction<Double, Double, Double, Double> calculationFunction
    ) {
        this._calculationFunction = calculationFunction;
        
        JPanel innerPanel = new JPanel(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new java.awt.Insets(10, 2, 10, 2); 
        gbc.fill = GridBagConstraints.BOTH; 

        JLabel label1 = new JLabel("a: ");
        label1.setForeground(Color.RED);
        label1.setHorizontalAlignment(SwingConstants.LEFT);

        _value1 = new NumericTextField();
        Color lightRed = new Color(255, 185, 185);
        _value1.setBackground(lightRed);
        _value1.setPreferredSize(new Dimension(_value1.getPreferredSize().width, 40));
        
        JLabel label2 = new JLabel("= r1: ");
        label2.setForeground(Color.GREEN);
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        
        _value2 = new NumericTextField();
        Color lightGreen = new Color(204, 255, 185);
        _value2.setBackground(lightGreen);
        _value2.setPreferredSize(new Dimension(_value2.getPreferredSize().width, 40));
        
        JLabel label3 = new JLabel("b:");
        label3.setForeground(Color.BLUE);
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        
        _value3 = new NumericTextField();
        Color lightBlue = new Color(185, 211, 255);
        _value3.setBackground(lightBlue);
        _value3.setPreferredSize(new Dimension(_value3.getPreferredSize().width, 40));
        _value3.addKeyListener(new EnterKeyListener());
        
        JLabel label4 = new JLabel("= r2: ");
        label4.setHorizontalAlignment(SwingConstants.LEFT);
        _value4 = new NumericTextField();
        
        _value4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String value = _value4.getText();
                copyToClipboard(value);
                
                _value4.setText(value + " - copiado!");
            }
        });
                
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;  
        innerPanel.add(label1, gbc); 
        
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 2.0;  
        innerPanel.add(_value1, gbc); 
        
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;  
        innerPanel.add(label2, gbc);
        
        gbc.gridx = 4;
        gbc.gridwidth = 2; 
        gbc.weightx = 2.0;  
        innerPanel.add(_value2, gbc); 
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;  
        innerPanel.add(label3, gbc); 
        
        gbc.gridx = 1;
        gbc.gridwidth = 2; 
        gbc.weightx = 2.0;  
        innerPanel.add(_value3, gbc); 
        
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0.1;  
        innerPanel.add(label4, gbc); 
        
        gbc.gridx = 4;
        gbc.gridwidth = 2; 
        gbc.weightx = 2.0;  
        innerPanel.add(_value4, gbc); 
        
        setLayout(new BorderLayout());
        
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Regra de Três"),
                new EmptyBorder(0, 5, 0, 5)
        ));
        
        add(innerPanel, BorderLayout.CENTER);
    }
    
    private void calculationResult() {
        
        try {
            _value4.setBackground(Color.WHITE);    
            _value4.setForeground(Color.BLACK);
            
            double a = getValue1();
            double b = getValue2();
            double c = getValue3();
            
            double result = _calculationFunction.apply(a, b, c);
            
            _value4.setText(String.valueOf(result));
            Color lightGreen = new Color(204, 255, 185);
            _value4.setBackground(lightGreen);    
            
        } catch(NumberFormatException ex) {
            
            clearFields();
            _value4.setText("ERROR: Invalid Value");
            _value4.setForeground(Color.RED);
            Color lightRed = new Color(255, 185, 185);
            _value4.setBackground(lightRed);
            
        }
    }
    
    private class EnterKeyListener implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            
            if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                calculationResult();
            }            
            
        }
        
        @Override
        public void keyTyped(KeyEvent e) {
        }


        @Override
        public void keyReleased(KeyEvent e) {            
        }
        
    }
    
    public Double getValue1() {
        return Double.parseDouble(_value1.getText());
    }
    
    public Double getValue2() {
        return Double.parseDouble(_value2.getText());
    }
    
    public Double getValue3() {
        return Double.parseDouble(_value3.getText());
    }
    
    public void clearFields() {
        _value1.setText("");
        _value2.setText("");
        _value3.setText("");
    }

    private void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }
}
