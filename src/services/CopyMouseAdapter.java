package services;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

public class CopyMouseAdapter extends MouseAdapter {
	private JTextField _textField;
	
	public CopyMouseAdapter(JTextField textField) {
		this._textField = textField;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		String copyText = " - copiado!";
		String value = _textField.getText();
		
		if(!value.isBlank() && !value.equals("ERROR: Invalid Value")) {
			if(value.contains(copyText)) {
				value = value.replace(copyText, "");
			}
			
			copyToClipboard(value);
			_textField.setText(value + copyText);
		}
	}
	
	private void copyToClipboard(String text) {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
}
