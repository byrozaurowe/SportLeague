import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {
    /** Panel glowny */
    private JPanel panel;
    /** Panel pomocniczy na logowanie */
    private JPanel panel1;
    /** Etykieta naglowka */
    private JLabel headerLabel;
    /** Etykieta hasła */
    private JLabel passwordLabel;
    /** Etykieta loginu */
    private JLabel loginLabel;
    /** Pole na haslo */
    private JPasswordField passwordField;
    /** Pole na login */
    private JTextField loginField;
    /** Przycisk rejestrujacy */
    private JButton signUpButton;
    /** Etykieta pesel */
    private JLabel peselLabel;
    /** Pole na pesel */
    private JTextField peselField;
    /** Etykieta nazwisko */
    private JLabel nameLabel;
    /** Pole na nazwisko */
    private JTextField nameField;
    /** Etykieta imie */
    private JLabel firstNameLabel;
    /** Pole na imie */
    private JTextField firstNameField;

    /** Konstruktor */
     SignUp() {
        super("Rejestracja");
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
            JOptionPane.showMessageDialog(this, "Rejestracja w trakcie weryfikacji", "Success", JOptionPane.INFORMATION_MESSAGE);
            DatabaseApplication.queries(new String[]{"addUser", loginField.getText(), String.valueOf(passwordField.getPassword()),
                    firstNameField.getText(), nameField.getText(), peselField.getText(), "2"});
            this.dispose();
        }
    }
}
