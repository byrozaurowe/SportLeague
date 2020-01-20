import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/** Klasa okna logowania */
public class Logging extends JFrame implements ActionListener {

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
    /** Przycisk logujacy */
    private JButton logInButton;
    /** Przycisk rejestracji */
    private JButton signUpButton;
    /** Panel na przyciski */
    private JPanel buttonPanel;
    /** Etykieta na error */
    private JLabel errorLabel;

    /** Konstruktor */
     Logging() {
        setTitle("Logowanie");
        Font font = new Font("Segoe UI", Font.PLAIN, 20);
        panel = new JPanel(new BorderLayout(10,10));
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

        panel1 = new JPanel(new GridLayout(2,2, 10, 10));
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
        setResizable(false);

        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = (centerPoint.x - windowSize.width) / 2;
        int dy = (centerPoint.y - windowSize.height) / 2;
        setLocation(dx, dy);

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
            List result = DatabaseApplication.queries(new String[]{"loggIn", loginField.getText(), String.valueOf(passwordField.getPassword())});
            if(result.get(0).toString().equals("1")) {
                new Menu(Integer.parseInt(result.get(1).toString()));
                this.dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Błędny login lub hasło", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
