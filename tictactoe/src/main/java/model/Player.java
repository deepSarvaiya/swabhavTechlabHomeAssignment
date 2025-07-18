package model;

public class Player {

	private final String name;
    private final Marker marker;

    public Player(String name, Marker marker) {
        this.name = name;
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public Marker getMarker() {
        return marker;
    }
	
}
