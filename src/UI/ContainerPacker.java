package UI;
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

        DefaultCaret defCaret = (DefaultCaret)textArea1.getCaret();
        defCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
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
