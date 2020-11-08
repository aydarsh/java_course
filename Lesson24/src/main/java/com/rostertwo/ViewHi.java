package com.rostertwo;

import javax.swing.*;
import java.awt.*;

/**
 * A simple View with components
 */
public class ViewHi {
    // components
    private JFrame frame;
    private JLabel helloNameLabel;
    private JLabel nameEnterLabel;
    private JTextField userInputTextField;
    private JButton sayHiBtn;
    private JPanel content;

    public ViewHi() {
        // lays out components
        frame = new JFrame("Simple App - MVC");
        helloNameLabel = new JLabel("Hello", JLabel.RIGHT);
        helloNameLabel.setPreferredSize(new Dimension(100, 20));
        userInputTextField = new JTextField(20);
        sayHiBtn = new JButton("Say Hi");
        nameEnterLabel = new JLabel("Enter your name");
        content = new JPanel();
        content.setLayout(new FlowLayout());
        content.add(nameEnterLabel);
        content.add(userInputTextField);
        content.add(sayHiBtn);
        content.add(helloNameLabel);
        // finalizes layout
        frame.setContentPane(content);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JButton getSayHiBtn() {
        return sayHiBtn;
    }

    public JLabel getHelloNameLabel() {
        return helloNameLabel;
    }

    public JTextField getUserInputTextField() {
        return userInputTextField;
    }
}
