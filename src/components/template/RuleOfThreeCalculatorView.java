package components.template;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
		
		JPanel innerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
		
		JLabel label1 = new JLabel("a: ");
		label1.setForeground(Color.RED);
		_value1 = new NumericTextField();
		
		JLabel label2 = new JLabel("= r1: ");
		label2.setForeground(Color.GREEN);
		_value2 = new NumericTextField();
		
		JLabel label3 = new JLabel("b:");
		label3.setForeground(Color.BLUE);
		_value3 = new NumericTextField();
		
		_value3.addKeyListener(new EnterKeyListener());
		
		JLabel label4 = new JLabel("= r2: ");
		_value4 = new NumericTextField();
		
		innerPanel.add(label1);
		innerPanel.add(_value1);
		innerPanel.add(label2);
		innerPanel.add(_value2);
		innerPanel.add(label3);
		innerPanel.add(_value3);
		innerPanel.add(label4);
		innerPanel.add(_value4);
		
		setLayout(new BorderLayout());
		
		setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder("Regra de Três"),
				new EmptyBorder(15, 15, 15, 15)
		));
		
		add(innerPanel, BorderLayout.CENTER);
	}
	
	private void calculationResult() {
		
		try {
			
			_value4.setForeground(Color.BLACK);
			
			double a = getValue1();
			double b = getValue2();
			double c = getValue3();
			
			double result = _calculationFunction.apply(a, b, c);
			
			_value4.setText(String.valueOf(result));
			
		} catch(NumberFormatException ex) {
			
			clearFields();
			_value4.setText("ERROR: Invalid Value");
			_value4.setForeground(Color.RED);
			
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
			// TODO Auto-generated method stub

		}


		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
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
	
}
