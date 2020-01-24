import TablesClasses.Match;
import TablesClasses.Tournament;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TournamentsMatches extends JFrame implements ActionListener {

    /** Tabela przyciskow akceptujacych uzytkownikow */
    private JButton[] seeMatchButton;
    /** Tabela przyciskow odrzucajacych uzytkownikow */
    private JButton[] addScoreButton;
    /** Lista loginow */
    private List requestsMatches;
    /** Id zalogowanego uzytkownika */
    private int tournamentId;

    /** Initializator etykiet
     * @param text tekst w etykiecie
     * @return etykieta
     */
    private JLabel labelInitializor(String text) {
        Font font = new Font("Segoe UI", Font.PLAIN, 20);
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    /** Konstruktor okna
     * @param tournamentId Id zalogowanego uzytkownika
     */
    TournamentsMatches(int tournamentId, boolean isOrganizer) {
        this.tournamentId = tournamentId;
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        JScrollPane scrollBar = new JScrollPane(panel,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollBar, BorderLayout.CENTER);
        setTitle("Mecze w turnieju " + DatabaseApplication.queries(new String[]{"tournamentNameById", String.valueOf(tournamentId)}).get(0));

        requestsMatches = DatabaseApplication.queries(new String[]{"requestMatchesByTournamentId", String.valueOf(tournamentId)});
        panel.setLayout(new GridLayout(requestsMatches.size(),6,10,10));

        seeMatchButton = new JButton[requestsMatches.size()];
        addScoreButton = new JButton[requestsMatches.size()];
        for(int i = 0; i < requestsMatches.size(); i++) {
            seeMatchButton[i] = new JButton("Zobacz szczegóły");
            seeMatchButton[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
            seeMatchButton[i].setHorizontalAlignment(SwingConstants.CENTER);
            seeMatchButton[i].addActionListener(this);
        }
        for(int i = 0; i < requestsMatches.size(); i++) {
            addScoreButton[i] = new JButton("Dodaj wydarzenie");
            addScoreButton[i].setFont(new Font("Segoe UI", Font.PLAIN, 20));
            addScoreButton[i].setHorizontalAlignment(SwingConstants.CENTER);
            addScoreButton[i].addActionListener(this);
        }

        JComponent[][] data = new JComponent[requestsMatches.size()+1][7];
        String[] columnNames = {"Drużyna pierwsza", "Punkty", "Drużyna druga", "Punkty", "Status", "Zobacz szczegóły", "Dodaj zdarzenie"};
        for(int i = 0; i < columnNames.length; i++) {
            data[0][i] = labelInitializor(columnNames[i]);
            data[0][i].setFont(new Font("Segoe UI", Font.BOLD, 23));
        }
        for(int i = 1; i < requestsMatches.size() + 1; i++) {
            data[i][0] = labelInitializor(String.valueOf(((Match) requestsMatches.get(i-1)).getIdDruzynyPierwszej()));
            data[i][2] = labelInitializor(String.valueOf(((Match) requestsMatches.get(i-1)).getPunktyDruzynyPierwszej()));
            data[i][1] = labelInitializor(String.valueOf(((Match) requestsMatches.get(i-1)).getIdDruzynyDrugiej()));
            data[i][3] = labelInitializor(String.valueOf(((Match) requestsMatches.get(i-1)).getPunktyDruzynyDrugiej()));
            data[i][4] = labelInitializor(String.valueOf(((Match) requestsMatches.get(i-1)).isStatus()));
            data[i][5] = seeMatchButton[i-1];
            data[i][6] = addScoreButton[i-1];
        }

        JPanel headerPanel = new JPanel(new GridLayout(1,6, 10, 10));
        for(int i = 0; i < columnNames.length; i++) {
            headerPanel.add(data[0][i]);
        }
        add(headerPanel, BorderLayout.NORTH);

        for (int i = 1; i < requestsMatches.size() + 1; i++) {
            for (int j = 0; j < columnNames.length; j++) {
                panel.add(data[i][j]);
            }
        }
        Dimension windowSize = getSize();
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        Point centerPoint = ge.getCenterPoint();
        int dx = (centerPoint.x - windowSize.width) / 2;
        int dy = (centerPoint.y - windowSize.height) / 2;
        setLocation(dx, dy);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        pack();
    }

    public void actionPerformed(ActionEvent actionEvent) {
        Object event = actionEvent.getSource();
        for(int i = 0; i < seeMatchButton.length; i++) {
            if(event == seeMatchButton[i]) {

            }
            else if(event == addScoreButton[i]) {

            }
        }
    }

    public static void main(String[] args) {
        new TournamentsMatches(1, true);
    }
}
