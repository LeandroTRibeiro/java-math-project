package components.template;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import components.ui.IconButton;

import javax.swing.ImageIcon;

public class ProjectCredits extends JFrame {

    private boolean isNoOnRight = true;
    private JPanel contentPanel;

    public ProjectCredits() {
        setSize(600, 400);
        setResizable(false);
        setTitle("Créditos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../../assets/info.png")));

        contentPanel = new JPanel(new GridLayout(8, 1, 10, 10));

        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(""),
            new EmptyBorder(15, 15, 15, 15)
        ));

        JLabel titleLabel = new JLabel("Projeto desenvolvido por:", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        contentPanel.add(titleLabel);

        String[] names = {"Leandro Thiago Ribeiro", "Bruno Michels Alves", "Enzo Rafael Conti de Souza", "Kediel Joás de Farias"};
        for (String name : names) {
            JLabel nameLabel = new JLabel(name, SwingConstants.CENTER);
            nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            contentPanel.add(nameLabel);
        }
        
        IconButton gitHubButton = new IconButton("Ver no GitHub", "../../assets/github.png");
        gitHubButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gitHubButton.addActionListener(e -> openGitHubLink("https://github.com/LeandroTRibeiro/java-math-project"));
        contentPanel.add(gitHubButton);
        
        JLabel askForGrade = new JLabel("Ta valendo um 10 hein professor? ");
        
        contentPanel.add(askForGrade);

        JPanel gradeButtons = new JPanel(new GridLayout(1, 2, 20, 20));

        JButton button1 = new JButton("Sim!");
        JButton button2 = new JButton("Não!");

        button1.setBackground(Color.GREEN);
        button1.setForeground(Color.BLACK);

        button2.setBackground(Color.RED);
        button2.setForeground(Color.WHITE);

        gradeButtons.add(button1);
        gradeButtons.add(button2);

        contentPanel.add(gradeButtons);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGif();
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                gradeButtons.removeAll();

                if (isNoOnRight) {
                    gradeButtons.add(button2);
                    gradeButtons.add(button1);
                } else {
                    gradeButtons.add(button1);
                    gradeButtons.add(button2);
                }

                isNoOnRight = !isNoOnRight;

                gradeButtons.revalidate();
                gradeButtons.repaint();
            }
        });

        add(contentPanel);
        setVisible(true);
    }

    private void showGif() {
        contentPanel.removeAll();

        ImageIcon gifIcon = new ImageIcon(getClass().getResource("../../assets/dance-party-bill-gates.gif"));

        JLabel gifLabel = new JLabel(gifIcon);

        JLabel thankYouLabel = new JLabel("Obrigado Professor! \uD83C\uDF89", SwingConstants.CENTER);
        Font emojiFont = new Font("Segoe UI Emoji", Font.PLAIN, 20);
        thankYouLabel.setFont(emojiFont);

        contentPanel.setLayout(new BorderLayout());

        contentPanel.add(gifLabel, BorderLayout.CENTER);

        contentPanel.add(thankYouLabel, BorderLayout.SOUTH);

        contentPanel.revalidate();
        contentPanel.repaint();
    }


    private void openGitHubLink(String url) {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(new URI(url));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Não foi possível abrir o link.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

}
