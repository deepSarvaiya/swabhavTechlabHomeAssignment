package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Cell;
import model.Marker;

public class CellTest {

	  @Test
	    void testInitialStateIsEmpty() {
	        Cell cell = new Cell();
	        assertEquals(Marker.EMPTY, cell.getMarker());
	    }

	    @Test
	    void testSetMarker() {
	        Cell cell = new Cell();
	        cell.setMarker(Marker.X);
	        assertEquals(Marker.X, cell.getMarker());
	    }

	    @Test
	    void testSetMarkerTwiceThrows() {
	        Cell cell = new Cell();
	        cell.setMarker(Marker.O);
	        assertThrows(IllegalStateException.class, () -> cell.setMarker(Marker.X));
	    }

	    @Test
	    void testIsEmpty() {
	        Cell cell = new Cell();
	        assertTrue(cell.isEmpty());
	        cell.setMarker(Marker.X);
	        assertFalse(cell.isEmpty());
	    }

	    @Test
	    void testClearResetsToEmpty() {
	        Cell cell = new Cell();
	        cell.setMarker(Marker.O);
	        cell.clear();
	        assertEquals(Marker.EMPTY, cell.getMarker());
	    }
	
}
