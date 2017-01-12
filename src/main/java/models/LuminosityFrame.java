package models;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Arthur on 1/1/2017.
 */
public class LuminosityFrame extends JFrame {

    private final JTextField luminosityTextField;
    private final JButton okButton;
    private FilteredCanvas canvas;

    public LuminosityFrame(){
        super("Set Luminosity Filter");
//        this.canvas = canvas;
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new JLabel("Set a value between 0 and 1"));
        luminosityTextField = new JTextField();
        luminosityTextField.setColumns(20);
        luminosityTextField.setText(Float.toString(Grimoire.luminescenceFilter));
        add(luminosityTextField);
        okButton = createOkButton();
        add(okButton);
    }

    private JButton createOkButton(){
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                float luminosityValuyue = Float.parseFloat(luminosityTextField.getText());
                Grimoire.luminescenceFilter = luminosityValuyue;
            }
        });
        return okButton;
    }
}
