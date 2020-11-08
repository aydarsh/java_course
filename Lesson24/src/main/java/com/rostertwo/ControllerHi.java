package com.rostertwo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * A simple Controller that listens to mouse click events
 */
public class ControllerHi implements MouseListener{
    private ModelHi model;
    private ViewHi view;

    public ControllerHi(ModelHi model, ViewHi view) {
        this.model = model;
        this.view = view;
    }

    // assigns a mouse listener to the sayHiBtn
    public void initController() {
        view.getSayHiBtn().addMouseListener(this);
    }

    // sets model name value
    // updates helloNameLabel's string with value from model
    @Override
    public void mouseClicked(MouseEvent e) {
        model.setName(view.getUserInputTextField().getText());
        view.getHelloNameLabel().setText("Hello " + model.getName());
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}
