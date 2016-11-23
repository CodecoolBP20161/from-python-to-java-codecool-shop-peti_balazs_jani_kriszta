package com.codecool.shop.controller;

/**
 * Created by krisztinabaranyai on 23/11/2016.
 */
public class Controller {
    private static String state;

    public static void setState(String newState) {
        state = newState;
    }

    public static String getState() {
        return state;
    }

    public static void doAct(){
        if (state.equals("DB")) {
            ProductControllerJdbc.setAttributes();
        } else if (state.equals("MEM")) {
            ProductControllerMem.setAttributes();
        }
    }
}
