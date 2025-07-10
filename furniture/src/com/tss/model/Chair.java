package com.tss.model;

public class Chair implements IFurniture {
    private IStyle style;

    public Chair(IStyle style) {
        this.style = style;
    }

    @Override
    public void describe() {
        System.out.println(style.getStyleName() + " style Chair");
    }
}