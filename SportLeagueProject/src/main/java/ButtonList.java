import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ButtonList extends JFrame implements ActionListener {
    String type;
    JPanel buttonPane;
    JButton[] teamButtonTable;
    JLabel[][] teamLabelTable;
    List buttonPaneContentList;
    ArrayList<List> labelContentList = new ArrayList<List>();

    private JLabel newLabel(String labelText) {
        Font font = new Font ("Segoe UI", Font.BOLD, 16);
        JLabel label = new JLabel(labelText);
        label.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
        label.setFont(font);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        return label;
    }

    public ButtonList(String type) {
        this.type = type;

        setVisible(true);
        setTitle("Ultimate Frisbee League");
        setPreferredSize(new Dimension(800, 300));
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
        }
        else if (this.type == "Turnieje") {
            buttonPaneContentList = DatabaseApplication.queries(new String[] {"getTournaments"});
        }
        else if (this.type == "Statystyki") {
            buttonPaneContentList = DatabaseApplication.queries(new String[] {"getPlayers"});
        }

        int listSize = buttonPaneContentList.size();
        buttonPane.setLayout(new GridLayout(listSize+1, 1+labelContentList.size()));
        teamButtonTable = new JButton[listSize];
        teamLabelTable = new JLabel[listSize][labelContentList.size()];

        for (int i=0; i<listSize; i++) {
            String text = buttonPaneContentList.get(i).toString();
            teamButtonTable[i] = new JButton(text);
            buttonPane.add(teamButtonTable[i]);
            teamButtonTable[i].addActionListener(this);
            teamButtonTable[i].setBorder(BorderFactory.createEmptyBorder(0, 2, 0, 2));
            teamButtonTable[i].setFont(font);
            for (int j=0; j<labelContentList.size(); j++) {
                teamLabelTable[i][j] = new JLabel(labelContentList.get(j).get(i).toString());
                teamLabelTable[i][j].setFont(font);
                teamLabelTable[i][j].setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
                teamLabelTable[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                teamLabelTable[i][j].setVerticalAlignment(SwingConstants.CENTER);
                buttonPane.add(teamLabelTable[i][j]);
            }
        }

        add(buttonPane);
        pack();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
