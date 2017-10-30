package com.menu;

/**
 * Created by mu on 2017/7/20.
 */
public class Button {
    private String type;
    private String name;
    private Button[] sub_button;

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getType() {

        return type;
    }

    public String getName() {
        return name;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }

    public Button[] getSub_button() {

        return sub_button;
    }
}
