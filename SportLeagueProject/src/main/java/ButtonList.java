import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ButtonList extends JFrame implements ActionListener {
    String type;
    JPanel buttonPane;
    JButton[] buttonTable;
    JLabel[][] teamLabelTable;
    List buttonPaneContentList;
    ArrayList<List> labelContentList = new ArrayList<List>();

    private JLabel newLabel(String labelText) {
        Font font = new Font ("Segoe UI", Font.BOLD, 16);
        JLabel label = new JLabel(labelText);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        label.setFont(font);
        label.setSize(150, 10);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }

    public ButtonList(String type) {
        this.type = type;

        setVisible(true);
        setTitle("Ultimate Frisbee League");
        Font font = new Font ("Segoe UI", Font.PLAIN, 16);

        buttonPane = new JPanel();

        if (this.type == "Drużyny") {
            buttonPane.add(newLabel("Drużyna"));
            buttonPaneContentList = DatabaseApplication.queries(new String[] {"getTeams"});
            buttonPane.add(newLabel("Miasto"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"teamCity"}));
            buttonPane.add(newLabel("Rok założenia"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"teamFoundationYear"}));
            buttonPane.add(newLabel("Dywizja"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"teamDivision"}));
            buttonPane.add(newLabel("Wygrane"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"teamWins"}));
            buttonPane.add(newLabel("Remisy"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"teamDraws"}));
            buttonPane.add(newLabel("Porażki"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"teamLosts"}));
            buttonPane.add(newLabel("Zdobyte punkty"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"teamPoints"}));
        }
        else if (this.type == "Turnieje") {
            buttonPane.add(newLabel("Turniej"));
            buttonPaneContentList = DatabaseApplication.queries(new String[] {"getTournaments"});
            buttonPane.add(newLabel("Data"));
            labelContentList.add(DatabaseApplication.queries(new String[] {"tournamentDate"}));
            buttonPane.add(newLabel("Miasto"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"tournamentLocation"}));
            buttonPane.add(newLabel("Dywizja"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"tournamentDivision"}));
        }
        else if (this.type == "Statystyki") {
            buttonPane.add(newLabel("Imię"));
            labelContentList.add(DatabaseApplication.queries(new String[] {"playerName"}));
            buttonPane.add(newLabel("Nazwisko"));
            labelContentList.add(DatabaseApplication.queries(new String[] {"playerSurname"}));
            buttonPane.add(newLabel("Drużyna"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"playerTeam"}));
            buttonPane.add(newLabel("Numer zawodnika"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"playerNumber"}));
            buttonPane.add(newLabel("Płeć"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"playerSex"}));
            buttonPane.add(newLabel("Rok urodzenia"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"playerBirth"}));
            buttonPane.add(newLabel("Zdobyte punkty"));
            labelContentList.add(DatabaseApplication.queries(new String[]{"playerScoredPoints"}));
        }

        int listSize;
        if (this.type != "Statystyki") {
            listSize = buttonPaneContentList.size();
            buttonTable = new JButton[listSize];
        }
        else {
            listSize = (labelContentList.get(0)).size();
        }
        int wid, hei;
        wid = labelContentList.size();
        if (this.type != "Statystyki") wid++;
        hei = listSize+1;
        buttonPane.setLayout(new GridLayout(hei, wid));
        teamLabelTable = new JLabel[listSize][labelContentList.size()];

        for (int i=0; i<listSize; i++) {
            if (!type.equals("Statystyki")) {
                String text = buttonPaneContentList.get(i).toString();
                buttonTable[i] = new JButton(text);
                buttonPane.add(buttonTable[i]);
                buttonTable[i].addActionListener(this);
                buttonTable[i].setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
                buttonTable[i].setFont(font);
                buttonTable[i].setSize(150, 10);
            }
            for (int j=0; j<labelContentList.size(); j++) {
                String labelText = labelContentList.get(j).get(i).toString();
                teamLabelTable[i][j] = new JLabel(labelText);
                teamLabelTable[i][j].setFont(font);
                teamLabelTable[i][j].setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                teamLabelTable[i][j].setSize(150, 10);
                teamLabelTable[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                teamLabelTable[i][j].setVerticalAlignment(SwingConstants.CENTER);
                buttonPane.add(teamLabelTable[i][j]);
            }
        }

        add(buttonPane);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public void actionPerformed(ActionEvent e) {

    }
}
