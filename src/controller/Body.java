package controller;

import models.Model;

/**
 * Created by tranh on 12/17/2016.
 */
public interface Body {
    void onContact(Body other);
    Model getModel();
    boolean isAlive();
}
