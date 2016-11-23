package com.codecool.shop.controller;

/**
 * Created by krisztinabaranyai on 23/11/2016.
 */
public class Controller {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void doAct(){
        if (state.equals("DB")) {
            ProductControllerJdbc.setAttributes();
        } else if (state.equals("MEM")) {
            ProductControllerMem.setAttributes();
        }
    }
}
