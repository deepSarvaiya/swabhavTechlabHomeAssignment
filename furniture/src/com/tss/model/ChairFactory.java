package com.tss.model;

public class ChairFactory implements IFurnitureFactory {
    @Override
    public IFurniture createFurniture(IStyle style) {
        return new Chair(style);
    }
}