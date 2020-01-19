import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ButtonList extends JFrame implements ActionListener {
    String rodzaj;
    JPanel buttonPane;

    public ButtonList(String rodzaj) {
        this.rodzaj = rodzaj;

        setVisible(true);
        setTitle("Ultimate Frisbee League");
        setPreferredSize(new Dimension(400, 300));
        Font font = new Font ("Segoe UI", Font.PLAIN, 20);

        buttonPane = new JPanel();
        List teamNameList = DatabaseApplication.queries("getTeams");
        int listSize = teamNameList.size();
        buttonPane.setLayout(new GridLayout(listSize, 1));
        JButton[] teamButtonTable = new JButton[];
        for (int i=0; i<teamNameList.size(); i++) {
            teamButtonTable[i].setText(teamNameList.get(i));

        }

        pack();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
