package com.tss.model;

public class SofaFactory implements IFurnitureFactory {
    @Override
    public IFurniture createFurniture(IStyle style) {
        return new Sofa(style);
    }
}