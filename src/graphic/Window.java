package graphic;

import util.Mathcalcs;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import components.template.InputPanel;
import components.template.RuleOfThreeCalculatorView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Window extends JFrame {
    
    private InputPanel inputPanel1;
    private InputPanel inputPanel2;
    private InputPanel inputPanel3;
    private InputPanel inputPanel4;
    private InputPanel inputPanel5;
    private InputPanel inputPanel6;
    private InputPanel inputPanel7;
    private RuleOfThreeCalculatorView inputPanel8;
    private InputPanel inputPanel9;

    public Window() {
        setSize(800, 600);
        setExtendedState(MAXIMIZED_BOTH);
        setTitle("Trabalho Programação Orientada a Objetos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../assets/calculator.png")));
        
        setLayout(new GridLayout(3, 3, 25, 25));
        
        getRootPane().setBorder(new EmptyBorder(20, 20, 20, 20));
        
        inputPanel1 = new InputPanel(
        		"Valor Inicial R$ (a)", 
        		"% desconto (b)", "Resultado: ", 
        		"Aplica desconto % num valor", 
        		Mathcalcs::Discount
        );
                
        inputPanel2 = new InputPanel(
        		"Valor Inicial R$ (a)", 
        		"% acréscimo (b)", "Resultado: ", 
        		"Incrementar % num valor", 
        		Mathcalcs::Increment
        );
                
        inputPanel3 = new InputPanel(
        		"Total (a)", "% Porcentagem (b)", 
        		"Corresponde a: ", 
        		"Amostragem - Quanto X% representa de Y", 
        		Mathcalcs::Sampling
        );
                
        inputPanel4 = new InputPanel(
        		"Total (a)", "Parte (b)", 
        		"Corresponde a %: ", 
        		"Amostragem 2 - Quanto X representa de Y", 
        		Mathcalcs::Sampling2
        );
                
        inputPanel5 = new InputPanel(
        		"Valor original (a)", 
        		"Valor c/desconto (b)", "% desconto: ", 
        		"Valor era A e paguei B, qual foi o desconto %", 
        		Mathcalcs::CalculateDiscountPercentage
        );
                
        inputPanel6 = new InputPanel(
        		"Valor Inicial R$ (a)", 
        		"Valor Final (b)", "% Diferença: ", 
        		"Variação Delta (%) - diferença % entre valores", 
        		Mathcalcs::CalculatePercentageChange
        );
                
        inputPanel7 = new InputPanel(
        		"Valor final R$ (a)", 
        		"% desconto (b)", 
        		"Valor inicial: ", 
        		"Qual era o valor original?", 
        		Mathcalcs::CalculateOriginalPrice
        );
                
        inputPanel8 = new RuleOfThreeCalculatorView(Mathcalcs::CalculateRuleOfThree);
                
        inputPanel9 = new InputPanel("Valor Inicial R$ (a)", "% desconto (b)", "Resultado: ", "Qual era o valor original?", Mathcalcs::Discount);
       
        add(inputPanel1);
        add(inputPanel2);
        add(inputPanel3);
        add(inputPanel4);
        add(inputPanel5);
        add(inputPanel6);
        add(inputPanel7);
        add(inputPanel8);
        add(inputPanel9);
        
        setVisible(true);
    }


    public static void main(String[] args) {
        new Window();
    }


}
