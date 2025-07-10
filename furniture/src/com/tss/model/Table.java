package com.tss.model;

public class Table implements IFurniture {
    private IStyle style;

    public Table(IStyle style) {
        this.style = style;
    }

    @Override
    public void describe() {
        System.out.println(style.getStyleName() + " style Table");
    }
}
