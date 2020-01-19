import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Klasa okna logowania */
public class Logging extends JFrame implements ActionListener {

    /** Panel glowny */
    JPanel panel;
    /** Panel pomocniczy na logowanie */
    JPanel panel1;
    /** Etykieta naglowka */
    JLabel headerLabel;
    /** Etykieta hasła */
    JLabel passwordLabel;
    /** Etykieta loginu */
    JLabel loginLabel;
    /** Pole na haslo */
    JPasswordField passwordField;
    /** Pole na login */
    JTextField loginField;
    /** Przycisk logujacy */
    JButton logInButton;
    /** Przycisk rejestracji */
    JButton signUpButton;

    JPanel buttonPanel;
    /** Konstruktor */
    public Logging() {
        setTitle("Logowanie");
        Font font = new Font("Segoe UI", Font.PLAIN, 20);
        panel = new JPanel(new BorderLayout());
        headerLabel = new JLabel("Logowanie");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel = new JLabel("Hasło:");
        passwordLabel.setFont(font);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel = new JLabel("Login:");
        loginLabel.setFont(font);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField = new JPasswordField(10);
        passwordField.setFont(font);
        loginField = new JTextField(10);
        loginField.setFont(font);
        panel1 = new JPanel(new GridLayout(2,2));
        logInButton = new JButton("Zaloguj");
        logInButton.setFont(font);
        panel1.add(loginLabel);
        panel1.add(loginField);
        panel1.add(passwordLabel);
        panel1.add(passwordField);
        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(panel1, BorderLayout.CENTER);
        panel.add(logInButton, BorderLayout.SOUTH);
        add(panel);
        signUpButton = new JButton("Zarejestruj się!");
        buttonPanel = new JPanel();
        buttonPanel.add(signUpButton);
        signUpButton.setFont(font);
        buttonPanel.add(logInButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        signUpButton.addActionListener(this);
        logInButton.addActionListener(this);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);
        pack();
    }


    public static void main(String[] args) {
        Logging frame = new Logging();
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object object = actionEvent.getSource();
        if(object == signUpButton) {
            new SignUp();
            this.dispose();
        }
        else if(object == logInButton) {

        }
    }
}
