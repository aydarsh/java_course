package com.rostertwo;

/**
 * An MVC application
 * ViewHi.java - visual components,
 * ModelHi.java - contains data and methods to access them,
 * ControllerHi.java - contains logic that glues View and Model
 */
public class Lesson24 {
    public static void main(String[] args) {
        ViewHi view = new ViewHi();
        ModelHi model = new ModelHi();
        ControllerHi controller = new ControllerHi(model, view);
        controller.initController();
    }
}
