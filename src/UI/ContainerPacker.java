package UI;
import java.awt.*;
import java.io.ByteArrayOutputStream;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
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
    private JTextArea textArea1;
    private JTextField text_value_a;
    private JTextField text_value_b;
    private JTextField text_value_c;
    private PrintStream printStream = new PrintStream(new FilteredStream(new ByteArrayOutputStream()));

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(10, 10, 10, 10), -1, -1));
        tabbedPane1 = new JTabbedPane();
        contentPane.add(tabbedPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridBagLayout());
        tabbedPane1.addTab("Genetic", panel1);
        final JLabel label1 = new JLabel();
        label1.setText("Package type");
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label1, gbc);
        boxesRadioButton = new JRadioButton();
        boxesRadioButton.setSelected(true);
        boxesRadioButton.setText("Boxes");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(boxesRadioButton, gbc);
        text_pop_size = new JTextField();
        text_pop_size.setText("40");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_pop_size, gbc);
        text_mut_rate = new JTextField();
        text_mut_rate.setText("10");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_mut_rate, gbc);
        text_thresh = new JTextField();
        text_thresh.setText("80");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_thresh, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("Population size");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label2, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("Mutation rate");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("Fullness threshold");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label4, gbc);
        pentominosRadioButton = new JRadioButton();
        pentominosRadioButton.setText("Pentominos");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(pentominosRadioButton, gbc);
        final JLabel label5 = new JLabel();
        label5.setText("( 0 - 100 )");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label5, gbc);
        final JLabel label6 = new JLabel();
        label6.setText("( 0 - 100 )");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label6, gbc);
        text_container_l = new JTextField();
        text_container_l.setText("11");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_container_l, gbc);
        final JLabel label7 = new JLabel();
        label7.setText("Container length");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label7, gbc);
        final JLabel label8 = new JLabel();
        label8.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label8, gbc);
        text_container_w = new JTextField();
        text_container_w.setText("5");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_container_w, gbc);
        final JLabel label9 = new JLabel();
        label9.setText("Container width");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label9, gbc);
        final JLabel label10 = new JLabel();
        label10.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.WEST;
        panel1.add(label10, gbc);
        text_container_h = new JTextField();
        text_container_h.setText("8");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_container_h, gbc);
        final JLabel label11 = new JLabel();
        label11.setText("Container heigth");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label11, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        panel1.add(spacer3, gbc);
        startButton = new JButton();
        startButton.setText("Start");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 13;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(startButton, gbc);
        text_value_a = new JTextField();
        text_value_a.setText("3");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_value_a, gbc);
        final JLabel label12 = new JLabel();
        label12.setText("Package A value");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label12, gbc);
        text_value_b = new JTextField();
        text_value_b.setText("4");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_value_b, gbc);
        final JLabel label13 = new JLabel();
        label13.setText("Package B value");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label13, gbc);
        text_value_c = new JTextField();
        text_value_c.setText("5");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(text_value_c, gbc);
        final JLabel label14 = new JLabel();
        label14.setText("Package C value");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.EAST;
        panel1.add(label14, gbc);
        //final JPanel panel2 = new JPanel();
      //panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        //tabbedPane1.addTab("Greedy", panel2);
        //final JLabel label15 = new JLabel();
       // label15.setFont(new Font(label15.getFont().getName(), label15.getFont().getStyle(), 26));
       // label15.setText("when I figure out how it works i'll update this panel");
        //panel2.add(label15);
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setAutoscrolls(true);
        contentPane.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        textArea1 = new JTextArea();
        textArea1.setEditable(false);
        textArea1.setLineWrap(true);
        textArea1.setRows(15);
        scrollPane1.setViewportView(textArea1);
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(boxesRadioButton);
        buttonGroup.add(pentominosRadioButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    class FilteredStream extends FilterOutputStream {
        public FilteredStream(OutputStream aStream) {
            super(aStream);
        }

        public void write(byte b[]) throws IOException {
            String aString = new String(b);
            textArea1.append(aString);
        }

        public void write(byte b[], int off, int len) throws IOException {
            String aString = new String(b, off, len);
            textArea1.append(aString);
        }
    }

    public ContainerPacker() {
        setContentPane(contentPane);
        setModal(true);

        DefaultCaret defCaret = (DefaultCaret) textArea1.getCaret();
        defCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Hashtable algParameters = new Hashtable();
                if (pentominosRadioButton.isSelected()) {
                    algParameters.put("pentomino", "yes");
                }
                algParameters.put("population_size", text_pop_size.getText());
                algParameters.put("mutation_rate", text_mut_rate.getText());
                algParameters.put("threshold", text_thresh.getText());
                algParameters.put("container_l", text_container_l.getText());
                algParameters.put("container_w", text_container_w.getText());
                algParameters.put("container_h", text_container_h.getText());
                algParameters.put("value_a", text_value_a.getText());
                algParameters.put("value_b", text_value_b.getText());
                algParameters.put("value_c", text_value_c.getText());
                Window3DView.startAlgorithm(algParameters);
                startButton.setEnabled(false);
            }
        });

        System.setOut(printStream);
        System.setErr(printStream);
    }
}
