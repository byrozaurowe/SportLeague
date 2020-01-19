import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {
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
    /** Przycisk rejestrujacy */
    JButton signUpButton;
    /** Etykieta pesel */
    JLabel peselLabel;
    /** Pole na pesel */
    JTextField peselField;
    /** Etykieta nazwisko */
    JLabel nameLabel;
    /** Pole na nazwisko */
    JTextField nameField;
    /** Etykieta imie */
    JLabel firstNameLabel;
    /** Pole na imie */
    JTextField firstNameField;

    /** Konstruktor */
    public SignUp() {
        setTitle("Rejestracja");
        Font font = new Font("Segoe UI", Font.PLAIN, 20);
        panel = new JPanel(new BorderLayout());

        headerLabel = new JLabel("Rejestracja");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        loginLabel = new JLabel("login:");
        loginLabel.setFont(font);
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginField = new JTextField(10);
        loginField.setFont(font);

        passwordLabel = new JLabel("hasło:");
        passwordLabel.setFont(font);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordField = new JPasswordField(10);
        passwordField.setFont(font);

        firstNameLabel = new JLabel("imię:");
        firstNameLabel.setFont(font);
        firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstNameField = new JTextField(10);
        firstNameField.setFont(font);

        nameLabel = new JLabel("nazwisko:");
        nameLabel.setFont(font);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameField = new JTextField(10);
        nameField.setFont(font);

        peselLabel = new JLabel("PESEL:");
        peselLabel.setFont(font);
        peselLabel.setHorizontalAlignment(SwingConstants.CENTER);
        peselField = new JTextField(10);
        peselField.setFont(font);

        signUpButton = new JButton("Zarejestruj");
        signUpButton.setFont(font);

        panel1 = new JPanel(new GridLayout(5,2));
        panel1.add(loginLabel);
        panel1.add(loginField);
        panel1.add(passwordLabel);
        panel1.add(passwordField);
        panel1.add(firstNameLabel);
        panel1.add(firstNameField);
        panel1.add(nameLabel);
        panel1.add(nameField);
        panel1.add(peselLabel);
        panel1.add(peselField);

        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(panel1, BorderLayout.CENTER);
        panel.add(signUpButton, BorderLayout.SOUTH);
        add(panel);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        signUpButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object object = actionEvent.getSource();
        if(object == signUpButton) {
            JOptionPane.showMessageDialog(this, "Rejestracja w trakcie weryfikacji", "Succes", JOptionPane.INFORMATION_MESSAGE);
            new Logging();
            this.dispose();
        }
    }
}
