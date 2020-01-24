import TablesClasses.Match;
import TablesClasses.Player;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class AddScore extends JFrame implements ActionListener {

    /** Combobox z zawodnikami */
    private JTextField playerNumberField;
    /** Przycisk wykonujący usuniecie */
    private JButton addScoreButton;
    /** Id uzytkownika ktory otwiera to okno */
    private int matchId;
    /** Combobox z druzynami */
    private JComboBox teamBox;
    /** Guzik zakoncz mecz */
    private JButton endMatchButton;
    /** Id uzytkownika */
    private int userId;

    /** Kostrukor okna
     * @param matchId Id uzytkownika ktory otwiera to okno
     */
    AddScore(int matchId, int userId) {
        this.matchId = matchId;
        this.userId = userId;
        setLayout(new FlowLayout(1, 30,15));
        setTitle("Dodaj punkt");
        Font font = new Font("Segoe UI", Font.PLAIN, 20);
        List teamsInMatch = DatabaseApplication.queries(new String [] {"getMatchById", String.valueOf(matchId)});
        ArrayList<String> teamsNames = new ArrayList<String>();
        teamsNames.add(String.valueOf(DatabaseApplication.queries(new String [] {"getTeamNameById", String.valueOf(((Match) teamsInMatch.get(0)).getIdDruzynyPierwszej())}).get(0)));
        teamsNames.add(String.valueOf(DatabaseApplication.queries(new String [] {"getTeamNameById", String.valueOf(((Match) teamsInMatch.get(0)).getIdDruzynyDrugiej())}).get(0)));
        JLabel teamNameLabel = new JLabel("Wpisz numer zawodnika, który zdobył punkt lub zakończ mecz");
        teamNameLabel.setFont(font);

        teamBox = new JComboBox(teamsNames.toArray());
        teamBox.setFont(font);

        addScoreButton = new JButton("Dodaj punkt");
        addScoreButton.setFont(font);
        addScoreButton.addActionListener(this);

        endMatchButton = new JButton("Zakończ mecz");
        endMatchButton.setFont(font);
        endMatchButton.addActionListener(this);

        playerNumberField = new JTextField(3);
        playerNumberField.setFont(font);

        add(teamNameLabel);
        add(teamBox);
        add(playerNumberField);
        add(addScoreButton);
        add(endMatchButton);

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
            try {
                Integer.parseInt(playerNumberField.getText());
            }
            catch(NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Numer zawodnika powinien być liczbą!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
            boolean isFirstTeam = false;
            if(teamBox.getSelectedItem() == teamBox.getItemAt(0)) {
                isFirstTeam = true;
            }
            List result = DatabaseApplication.queries(new String[] {"addScore", String.valueOf(matchId),
                    String.valueOf(DatabaseApplication.queries(new String[]{"getTeamId", String.valueOf(teamBox.getSelectedItem())}).get(0)),
                    playerNumberField.getText(), String.valueOf(isFirstTeam)});
            if(result.size() != 0) {
                if(result.get(0).equals("wrongNumber")) {
                    JOptionPane.showMessageDialog(null, "Błędny numer zawodnika!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                this.dispose();
            }
        }
        if(event == endMatchButton) {
            // TODO procedura koniec meczu
        }
    }
}