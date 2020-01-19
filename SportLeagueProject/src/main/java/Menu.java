import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends JFrame implements ActionListener {
    int userId = 1;

    JComboBox menuComboBox;
    JLabel menuLabel;
    JPanel menuPanel;
    JPanel buttonPanel;
    JButton menuButton;

    public Menu (int userId) {
        setVisible(true);
        setTitle("Ultimate Frisbee League");
        setPreferredSize(new Dimension(400, 300));

        /** 1- admin, 2 - kapitan drużyny, 3 - organizator turnieju, 4 - obserwator */
        this.userId = userId;

        Font font = new Font ("Segoe UI", Font.PLAIN, 20);

        ArrayList<String> menuComboBoxList = new ArrayList<String>();
        menuComboBoxList.add("Drużyny");
        menuComboBoxList.add("Turnieje");
        menuComboBoxList.add("Statystyki zawodników");
        if (userId == 1) {
            menuComboBoxList.add("Dodaj drużynę");
            menuComboBoxList.add("Usuń drużynę");
            menuComboBoxList.add("Dodaj zawodnika");
            menuComboBoxList.add("Dodaj turniej");
            menuComboBoxList.add("Dodaj mecz");
            menuComboBoxList.add("Usuń mecz");
            menuComboBoxList.add("Prośby użytkowników");
        }
        if (userId == 2) {
            menuComboBoxList.add("Dodaj drużynę");
            menuComboBoxList.add("Dodaj zawodnika");
        }
        if (userId == 3) {
            menuComboBoxList.add("Dodaj turniej");
            menuComboBoxList.add("Dodaj mecz");
            menuComboBoxList.add("Usuń mecz");
        }
        menuComboBox = new JComboBox(menuComboBoxList.toArray());

        menuLabel = new JLabel("Wybierz opcję z listy");
        menuButton = new JButton("Przejdź dalej");

        menuPanel = new JPanel(new GridLayout(3, 1));
        menuLabel.setHorizontalAlignment(SwingConstants.CENTER);
        menuLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuLabel.setFont(font);
        menuComboBox.setAlignmentY(Component.CENTER_ALIGNMENT);
        menuComboBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuComboBox.setFont(font);
        ((JLabel)menuComboBox.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        menuButton.setFont(font);
        menuButton.setHorizontalAlignment(SwingConstants.CENTER);
        menuButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        menuPanel.add(menuLabel);
        menuPanel.add(menuComboBox);
        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(menuButton);
        menuPanel.add(buttonPanel);

        add(menuPanel);

        menuComboBox.addActionListener(this);
        menuButton.addActionListener(this);

        pack();
    }

    public static void main(String[] args) {
        Menu main = new Menu(2);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == menuButton && menuComboBox.getSelectedItem() != null) {
            Object comboBoxSource = menuComboBox.getSelectedItem();
            if (comboBoxSource == "Drużyny") {
                new ButtonList("Drużyny");
            }
            if (comboBoxSource == "Turnieje") {
                new ButtonList("Turnieje");
            }
            if (comboBoxSource == "Statystyki zawodników") {
                new ButtonList("Statystyki");
            }
            if (comboBoxSource == "Dodaj drużynę") {

            }
            if (comboBoxSource == "Usuń drużynę") {

            }
            if (comboBoxSource == "Dodaj zawodnika") {

            }
            if (comboBoxSource == "Dodaj turniej") {

            }
            if (comboBoxSource == "Dodaj mecz") {

            }
            if (comboBoxSource == "Usuń mecz") {

            }
            if (comboBoxSource == "Prośby uzytkowników") {

            }
            this.dispose();
        }
    }
}
