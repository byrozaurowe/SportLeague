import TablesClasses.Team;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu extends JFrame implements ActionListener {
    private int permissionLevel;
    private int userId;

    private JComboBox menuComboBox;
    private JLabel menuLabel;
    private JPanel menuPanel;
    private JPanel buttonPanel;
    private JButton menuButton;

    Menu (int permissionLevel, int userId) {
        setVisible(true);
        setTitle("Ultimate Frisbee League");
        setPreferredSize(new Dimension(400, 300));
        // Ustawia okno blizej srodka
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = (centerPoint.x - windowSize.width) / 2;
        int dy = (centerPoint.y - windowSize.height) / 2;
        setLocation(dx, dy);

        /* 1- admin, 2 - kapitan drużyny, 3 - organizator turnieju, 4 - obserwator */
        this.permissionLevel = permissionLevel;
        this.userId = userId;

        Font font = new Font ("Segoe UI", Font.PLAIN, 20);

        ArrayList<String> menuComboBoxList = new ArrayList<String>();
        menuComboBoxList.add("Drużyny");
        menuComboBoxList.add("Turnieje");
        menuComboBoxList.add("Statystyki zawodników");
        if (permissionLevel == 1) {
            menuComboBoxList.add("Dodaj drużynę");
            menuComboBoxList.add("Usuń drużynę");
            menuComboBoxList.add("Dodaj zawodnika");
            menuComboBoxList.add("Dodaj turniej");
            menuComboBoxList.add("Dodaj mecz");
            menuComboBoxList.add("Usuń mecz");
            menuComboBoxList.add("Prośby użytkowników");
        }
        if (permissionLevel == 2) {
            menuComboBoxList.add("Dodaj drużynę");
            menuComboBoxList.add("Dodaj zawodnika");
        }
        if (permissionLevel == 3) {
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
        Menu main = new Menu(1,0);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == menuButton && menuComboBox.getSelectedItem() != null) {
            Object comboBoxSource = menuComboBox.getSelectedItem();
            String[] text = new String[1];
            if (comboBoxSource == "Drużyny") {
                text[0] = "Drużyny";
                new ButtonList(text);
            }
            if (comboBoxSource == "Turnieje") {
                text[0] = "Turnieje";
                new ButtonList(text);
            }
            if (comboBoxSource == "Statystyki zawodników") {
                text[0] = "Statystyki";
                new ButtonList(text);
            }
            if (comboBoxSource == "Dodaj drużynę") {
                new AddTeam(userId);
            }
            if (comboBoxSource == "Usuń drużynę") {

            }
            if (comboBoxSource == "Dodaj zawodnika") {
                if(Integer.parseInt(String.valueOf(DatabaseApplication.queries(new String[]{"countTeams", String.valueOf(userId)}).get(0))) > 0) {
                    new AddPlayer(userId);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nie posiadasz żadnych drużyn, żeby dodać zawodnika", "Brak", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            if (comboBoxSource == "Dodaj turniej") {
                new AddTournament(userId);
            }
            if (comboBoxSource == "Dodaj mecz") {

            }
            if (comboBoxSource == "Usuń mecz") {

            }
            if(comboBoxSource == "Prośby użytkowników") {
                if(Integer.parseInt(String.valueOf(DatabaseApplication.queries(new String[]{"userCounter"}).get(0))) > 0) {
                    new Requests();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Nie ma żadnych próśb o autoryzacje", "Brak", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }
    }
}
