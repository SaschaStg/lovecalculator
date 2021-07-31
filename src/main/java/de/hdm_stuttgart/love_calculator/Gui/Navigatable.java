package de.hdm_stuttgart.love_calculator.Gui;

/**
 * Provides the Navigatable interface
 */
public interface Navigatable {

    /**
     * Fires when a scene is opened.
     *
     * @param argument the session of the game the user is playing
     */
    void onShow(Object argument);
}