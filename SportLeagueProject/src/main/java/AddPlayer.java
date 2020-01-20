import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPlayer extends JFrame implements ActionListener {
    /** Panel glowny */
    private JPanel panel;
    /** Panel pomocniczy na logowanie */
    private JPanel panel1;
    /** Etykieta naglowka */
    private JLabel headerLabel;
    /** Etykieta nazwisko */
    private JLabel nameLabel;
    /** Etykieta imie */
    private JLabel firstNameLabel;
    /** Pole na nazwisko */
    private JTextField nameField;
    /** Pole na imie */
    private JTextField firstNameField;
    /** Przycisk rejestrujacy */
    private JButton addPlayerButton;
    /** Etykieta nr zawodnika */
    private JLabel playerNumberLabel;
    /** Pole na nr zawodnika */
    private JTextField playerNumberField;
    /** Etykieta rok */
    private JLabel birthYearLabel;
    /** Pole na rok */
    private JTextField birthYearField;
    /** Etykieta plec */
    private JLabel sexLabel;
    /** Pole na plec */
    private JComboBox sexList;
    /** Panel na label i guzik */
    private JPanel panel2;
    /** Id druzyny do ktorej dodajemy */
    private int teamId;

    /** Konstruktor okna dodaj gracza */
    AddPlayer(int teamId) {
        super("Dodaj zawodnika");
        Font font = new Font("Segoe UI", Font.PLAIN, 20);
        panel = new JPanel(new BorderLayout(10,10));

        this.teamId = teamId;

        String[] sexStrings = { "Mężczyzna", "Kobieta" };
        sexList = new JComboBox(sexStrings);
        sexList.setSelectedIndex(1);
        sexList.addActionListener(this);

        headerLabel = new JLabel("Dodaj zawodnika");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 23));
        headerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        firstNameLabel = new JLabel("Imię:");
        firstNameLabel.setFont(font);
        firstNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        firstNameField = new JTextField(10);
        firstNameField.setFont(font);

        nameLabel = new JLabel("Nazwisko:");
        nameLabel.setFont(font);
        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameField = new JTextField(10);
        nameField.setFont(font);

        sexLabel = new JLabel("Płeć:");
        sexLabel.setFont(font);
        sexLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sexList.setFont(font);

        birthYearLabel = new JLabel("Rok urodzenia:");
        birthYearLabel.setFont(font);
        birthYearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        birthYearField = new JTextField(10);
        birthYearField.setFont(font);

        playerNumberLabel = new JLabel("Numer zawodnika:");
        playerNumberLabel.setFont(font);
        playerNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playerNumberField = new JTextField(10);
        playerNumberField.setFont(font);

        addPlayerButton = new JButton("Zarejestruj");
        addPlayerButton.setFont(font);

        panel2 = new JPanel(new FlowLayout());
        panel2.add(addPlayerButton);

        panel1 = new JPanel(new GridLayout(5,2, 10, 10));
        panel1.add(firstNameLabel);
        panel1.add(firstNameField);
        panel1.add(nameLabel);
        panel1.add(nameField);
        panel1.add(sexLabel);
        panel1.add(sexList);
        panel1.add(birthYearLabel);
        panel1.add(birthYearField);
        panel1.add(playerNumberLabel);
        panel1.add(playerNumberField);

        panel.add(headerLabel, BorderLayout.NORTH);
        panel.add(panel1, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.SOUTH);
        add(panel);

        setVisible(true);
        setResizable(false);

        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = (centerPoint.x - windowSize.width) / 2;
        int dy = (centerPoint.y - windowSize.height) / 2;
        setLocation(dx, dy);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();

        addPlayerButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object object = actionEvent.getSource();
        if(object == addPlayerButton) {
            if (firstNameField.getText().equals("") || nameField.getText().equals("") || birthYearField.getText().equals("") || playerNumberField.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Wszystkie pola muszą być wypełnione!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    Integer.parseInt(birthYearField.getText());
                }
                catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Podano nieprawidłowy rok!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    Integer.parseInt(playerNumberField.getText());
                }
                catch(NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Podano nieprawidłowy numer zawodnika!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JOptionPane.showMessageDialog(this, "Poprawnie dodano zawodnika do drużyny", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                DatabaseApplication.queries(new String[]{"addPlayer", firstNameField.getText(), nameField.getText(),
                        sexList.getSelectedItem().toString(), birthYearField.getText(), playerNumberField.getText(), String.valueOf(teamId)});
                this.dispose();
            }
        }
    }

    public static void main(String[] args) {
        new AddPlayer(1);
    }
}
