package com.tss.model;

public class Sofa implements IFurniture {
    private IStyle style;

    public Sofa(IStyle style) {
        this.style = style;
    }

    @Override
    public void describe() {
        System.out.println(style.getStyleName() + " style Sofa");
    }
}
