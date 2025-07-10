package com.tss.test;

import java.util.Scanner;

import com.tss.model.ChairFactory;
import com.tss.model.IFurniture;
import com.tss.model.IFurnitureFactory;
import com.tss.model.IStyle;
import com.tss.model.Modern;
import com.tss.model.SofaFactory;
import com.tss.model.TableFactory;
import com.tss.model.Victorian;

public class FurnitureTest {

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);

	        System.out.println("Choose furniture style (Modern / Victorian): ");
	        String styleInput = scanner.nextLine().trim().toLowerCase();

	        IStyle style = null;
	        switch (styleInput) {
	            case "modern":
	                style = new Modern();
	                break;
	            case "victorian":
	                style = new Victorian();
	                break;
	            default:
	                System.out.println("Invalid style selected.");
	                System.exit(0);
	        }

	        System.out.println("Choose furniture type (Chair / Sofa / Table): ");
	        String typeInput = scanner.nextLine().trim().toLowerCase();

	        IFurnitureFactory factory = null;

	        switch (typeInput) {
	            case "chair":
	                factory = new ChairFactory();
	                break;
	            case "sofa":
	                factory = new SofaFactory();
	                break;
	            case "table":
	                factory = new TableFactory();
	                break;
	            default:
	                System.out.println("Invalid furniture type.");
	                System.exit(0);
	        }

	        IFurniture furniture = factory.createFurniture(style);
	        furniture.describe();

	}

}
