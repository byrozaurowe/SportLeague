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
    List buttonPaneContentList;

    public ButtonList(String type) {
        this.type = type;

        setVisible(true);
        setTitle("Ultimate Frisbee League");
        setPreferredSize(new Dimension(400, 300));
        Font font = new Font ("Segoe UI", Font.PLAIN, 20);

        buttonPane = new JPanel();
        if (this.type == "Drużyny") {
            buttonPaneContentList = DatabaseApplication.queries("getTeams");
        }
        int listSize = buttonPaneContentList.size();
        buttonPane.setLayout(new GridLayout(listSize, 1));
        JButton[] teamButtonTable = new JButton[listSize];
        for (int i=0; i<listSize; i++) {
            String text = buttonPaneContentList.get(1).toString();
            teamButtonTable[i].setText(text);
            buttonPane.add(teamButtonTable[i]);
            teamButtonTable[i].addActionListener(this);
            teamButtonTable[i].setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            teamButtonTable[i].setFont(font);
        }

        add(buttonPane);
        pack();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
