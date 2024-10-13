package components.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.util.function.BiFunction;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import components.ui.NumericTextField;

public class InputPanel extends JPanel {
	
	private JTextField _value1;
	private JTextField _value2;
	private JTextField _value3;
	
	private BiFunction<Double, Double, Double> _calculationFunction;

	public InputPanel(
			String label1Text, 
			String label2Text, 
			String response, 
			String title, 
			BiFunction<Double, Double, Double> calculationFunction
	) {
		
		this._calculationFunction = calculationFunction;

		JPanel innerPanel = new JPanel(new GridLayout(3, 2, 10, 10));
		
		JLabel label1 = new JLabel(label1Text);
		label1.setForeground(Color.RED);
		_value1 = new NumericTextField();
        Color lightRed = new Color(255, 185, 185);
		_value1.setBackground(lightRed);
				
		JLabel label2 = new JLabel(label2Text);
		label2.setForeground(Color.BLUE);
		_value2 = new NumericTextField();
        Color lightBlue = new Color(185, 211, 255);
		_value2.setBackground(lightBlue);
		
		_value2.addKeyListener(new EnterKeyListener());
		
		JLabel label3 = new JLabel(response);
		
		_value3 = new NumericTextField();
		
		_value3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String value = _value3.getText();
				copyToClipboard(value);
				
				_value3.setText(value + " - copiado!");
			}
		});
		
		innerPanel.add(label1);
		innerPanel.add(_value1);
		innerPanel.add(label2);
		innerPanel.add(_value2);
		innerPanel.add(label3);
		innerPanel.add(_value3);
		
		setLayout(new BorderLayout());
		
		setBorder(BorderFactory.createCompoundBorder(
			BorderFactory.createTitledBorder(title),  
			new EmptyBorder(15, 15, 15, 15)
		));
		
		add(innerPanel, BorderLayout.CENTER);
	}
	
	private void calculationResult() {
		
		
		try {
			_value3.setBackground(Color.WHITE);	
			_value3.setForeground(Color.BLACK);
			double a = getValue1();
			double b = getValue2();
			double result = _calculationFunction.apply(a, b);
			
			_value3.setText(String.valueOf(result));
			Color lightGreen = new Color(204, 255, 185);
			_value3.setBackground(lightGreen);			
			
		} catch(NumberFormatException ex) {
			
			clearFields();
			_value3.setText("ERROR: Invalid Value");
			_value3.setForeground(Color.RED);
	        Color lightRed = new Color(255, 185, 185);
			_value3.setBackground(lightRed);
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

	public void clearFields() {
		_value1.setText("");
		_value2.setText("");
	}

	private void copyToClipboard(String text) {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
}
