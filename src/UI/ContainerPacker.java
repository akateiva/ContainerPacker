package UI;
import java.util.Hashtable;
import javax.swing.*;
import java.awt.event.*;

public class ContainerPacker extends JDialog {
    private JPanel contentPane;
    private JTabbedPane tabbedPane1;
    private JRadioButton boxesRadioButton;
    private JTextField text_pop_size;
    private JTextField text_mut_rate;
    private JTextField text_thresh;
    private JRadioButton pentominosRadioButton;
    private JButton startButton;
    private JTextField text_container_l;
    private JTextField text_container_w;
    private JTextField text_container_h;

    public ContainerPacker() {
        setContentPane(contentPane);
        setModal(true);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hashtable algParameters = new Hashtable();
                if(pentominosRadioButton.isSelected()){
                    algParameters.put("pentomino", "yes");
                }
                algParameters.put("population_size", text_pop_size.getText());
                algParameters.put("mutation_rate", text_mut_rate.getText());
                algParameters.put("threshold", text_thresh.getText());
                algParameters.put("container_l", text_container_l.getText());
                algParameters.put("container_w", text_container_w.getText());
                algParameters.put("container_h", text_container_h.getText());
                Window3DView.startAlgorithm(algParameters);
                startButton.setEnabled(false);
            }
        });
    }
}
