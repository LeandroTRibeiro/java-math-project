package components.template;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import services.CopyMouseAdapter;

public class PasswordGeneratorView extends JPanel {
    
    private JCheckBox _upper;
    private JCheckBox _lower;
    private JCheckBox _number;
    private JCheckBox _symbol;
    
    private JSpinner _length;
    private JButton _generatePassword;
    private JTextField _password;
    
    private SecureRandom random = new SecureRandom();

    public PasswordGeneratorView() {
        
        JPanel innerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        gbc.insets = new Insets(2, 2, 2, 2); 
        
        _upper = new JCheckBox("Maiúsculas");
        _lower = new JCheckBox("Minúsculas");
        _number = new JCheckBox("Números");
        _symbol = new JCheckBox("Símbolos");

        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, 100, 1);
        _length = new JSpinner(model);
        
        _generatePassword = new JButton("Gerar");
        _password = new JTextField();
        _password.setPreferredSize(new Dimension(_password.getPreferredSize().width, 40));
        
        _password.setMargin(new Insets(0, 10, 0, 0));

        _password.addMouseListener(new CopyMouseAdapter(_password));

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        innerPanel.add(_upper, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        innerPanel.add(_lower, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        innerPanel.add(_number, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;        
        innerPanel.add(_symbol, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        innerPanel.add(_length, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        innerPanel.add(_generatePassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; 
        innerPanel.add(_password, gbc);
        
        setLayout(new BorderLayout());
        
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Gerador de Senha"),
                new EmptyBorder(5, 10, 5, 10)
        ));
        
        add(innerPanel, BorderLayout.CENTER);

        _upper.addItemListener(new CheckBoxListener());
        _lower.addItemListener(new CheckBoxListener());
        _number.addItemListener(new CheckBoxListener());
        _symbol.addItemListener(new CheckBoxListener());
        
        _generatePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePassword();
            }
        });
        
        updateSpinnerMinimum();
    }
    
    private class CheckBoxListener implements ItemListener {
        @Override
        public void itemStateChanged(ItemEvent e) {
            updateSpinnerMinimum();
        }
    }

    private void updateSpinnerMinimum() {
        int selectedCount = 0;
        
        if (_upper.isSelected()) selectedCount++;
        if (_lower.isSelected()) selectedCount++;
        if (_number.isSelected()) selectedCount++;
        if (_symbol.isSelected()) selectedCount++;

        SpinnerNumberModel model = (SpinnerNumberModel) _length.getModel();

        if (selectedCount == 0) {
            model.setMinimum(0);
            model.setMaximum(0);
            _length.setValue(0);
        } else {
            model.setMinimum(selectedCount);
            model.setMaximum(100);
            if ((int) _length.getValue() < selectedCount) {
                _length.setValue(selectedCount);
            }
        }
    }

    private void generatePassword() {
    	
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()_-+=<>?";
        
        ArrayList<Character> passwordChars = new ArrayList<>(); 
        StringBuilder characterPool = new StringBuilder();      

        if (_upper.isSelected()) {
            passwordChars.add(upperCase.charAt(random.nextInt(upperCase.length())));
            characterPool.append(upperCase);
        }
        if (_lower.isSelected()) {
            passwordChars.add(lowerCase.charAt(random.nextInt(lowerCase.length())));
            characterPool.append(lowerCase);
        }
        if (_number.isSelected()) {
            passwordChars.add(numbers.charAt(random.nextInt(numbers.length())));
            characterPool.append(numbers);
        }
        if (_symbol.isSelected()) {
            passwordChars.add(symbols.charAt(random.nextInt(symbols.length())));
            characterPool.append(symbols);
        }

        if (characterPool.length() == 0) {
            _password.setText("");
            return;
        }

        int passwordLength = (int) _length.getValue();

        while (passwordChars.size() < passwordLength) {
            passwordChars.add(characterPool.charAt(random.nextInt(characterPool.length())));
        }

        Collections.shuffle(passwordChars);

        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        _password.setText(password.toString());
    }

}
