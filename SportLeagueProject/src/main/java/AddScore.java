import TablesClasses.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddScore extends JFrame implements ActionListener {

    /** Combobox z zawodnikami */
    private JTextField playerNumberField;
    /** Przycisk wykonujący usuniecie */
    private JButton addScoreButton;
    /** Id uzytkownika ktory otwiera to okno */
    private int matchId;

    /** Kostrukor okna
     * @param matchId Id uzytkownika ktory otwiera to okno
     */
    AddScore(int matchId, int teamId) {
        this.matchId = matchId;

        setLayout(new FlowLayout(1, 30,15));
        setTitle("Dodaj punkt");
        Font font = new Font("Segoe UI", Font.PLAIN, 20);
        DatabaseApplication.queries(new String [] {"getTeamNameById", String.valueOf(teamId)}).get(0);
        JLabel teamNameLabel = new JLabel("Numer zawodnika drużyny " + DatabaseApplication.queries(new String [] {"getTeamNameById", String.valueOf(teamId)}).get(0));
        teamNameLabel.setFont(font);

        addScoreButton = new JButton("Dodaj punkt");
        addScoreButton.setFont(font);
        addScoreButton.addActionListener(this);

        playerNumberField = new JTextField(3);
        playerNumberField.setFont(font);

        add(teamNameLabel);
        add(playerNumberField);
        add(addScoreButton);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = (centerPoint.x - windowSize.width) / 2;
        int dy = (centerPoint.y - windowSize.height) / 2;
        setLocation(dx, dy);
        pack();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object event = actionEvent.getSource();
        if(event == addScoreButton) {
            // TODO kwerenda w Databaseaplication dodajaca punkt danej druzynie
        }
    }

    public static void main(String[] args) {
        new AddScore(1,1);
    }
}