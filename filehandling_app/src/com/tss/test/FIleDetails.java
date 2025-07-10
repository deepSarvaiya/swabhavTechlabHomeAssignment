package com.tss.test;

import java.io.File;
import java.io.IOException;

public class FIleDetails {
	   public static void main(String[] args) throws IOException {
	        File file = new File("");
	        
	        if(file.isDirectory()) {
	            File filelist[] = file.listFiles();
	            printFileName(filelist);
	            return;
	        }
	        System.out.println(file.length());
	        
	    }

	    private static void printFileName(File[] filelist) {
	        for(File filename : filelist) {
	            if(filename.isDirectory()) {
	                File filelist1[] = filename.listFiles();
	                printFileName(filelist1);
	            }
	            System.out.println(filename.getName());
	        }
	    }

}
