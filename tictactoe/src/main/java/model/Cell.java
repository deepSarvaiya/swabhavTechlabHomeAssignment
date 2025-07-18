package model;

public class Cell {

	private Marker marker;

    public Cell() {
        this.marker = Marker.EMPTY;
    }

    public Marker getMarker() {
        return marker;
    }

    public void setMarker(Marker marker) {
        if (this.marker != Marker.EMPTY) {
            throw new IllegalStateException("Cell is already marked.");
        }
        this.marker = marker;
    }

    public boolean isEmpty() {
        return this.marker == Marker.EMPTY;
    }

    public void clear() {
        this.marker = Marker.EMPTY;
    }
	
}
