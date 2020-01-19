import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonList extends JFrame implements ActionListener {
    String rodzaj;

    public ButtonList(String rodzaj) {
        this.rodzaj = rodzaj;

        setVisible(true);
        setTitle("Ultimate Frisbee League");
        setPreferredSize(new Dimension(400, 300));
        Font font = new Font ("Segoe UI", Font.PLAIN, 20);

        pack();
    }

    public void actionPerformed(ActionEvent e) {

    }
}
