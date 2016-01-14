import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContainerPacker extends JDialog {
    private JPanel contentPane;
    private JSlider slider1;
    private JRadioButton boxesRadioButton;
    private JRadioButton pentominoSRadioButton;
    private JButton STARTButton;
    private JLabel min_fit_indicator;
    private JList list1;

    public ContainerPacker() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(STARTButton);
        slider1.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                min_fit_indicator.setText(slider1.getValue() + " %");
            }
        });
        STARTButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boxesRadioButton.isSelected()) {
                    //Start the BOXES part
                    //Create a new thread in which the algorithm will run, because it's nicer if the UI doesn't freeze while its running
                    Thread algorithmThread = new Thread(new KnapAlg(slider1.getValue()));
                    algorithmThread.start();
                } else if (pentominoSRadioButton.isSelected()) {
                    //Start the PENTOMINO
                    Thread algorithmThread = new Thread(new KnapAlgPent(slider1.getValue()));
                    algorithmThread.start();
                }
                STARTButton.setText("RUNNING...");
                STARTButton.setEnabled(false);
            }
        });
    }
    public static void main(String[] args) {
        ContainerPacker dialog = new ContainerPacker();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
