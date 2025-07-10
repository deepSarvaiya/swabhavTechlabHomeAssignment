package com.tss.model;

public class TableFactory implements IFurnitureFactory {
    @Override
    public IFurniture createFurniture(IStyle style) {
        return new Table(style);
    }
}
