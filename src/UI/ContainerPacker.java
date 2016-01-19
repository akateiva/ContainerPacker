package UI;

import javax.swing.*;
import java.awt.event.*;

public class ContainerPacker extends JDialog {
    private JPanel contentPane;

    public ContainerPacker() {
        setContentPane(contentPane);
        setModal(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
