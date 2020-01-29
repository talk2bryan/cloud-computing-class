package com.wodi.backend;

import java.io.IOException;

public class Expermiments {

	public static void main(String[] args) throws IOException {		
	    
		for (int i = 1; i <= 10; i++) {
			System.out.println("\n=========================================== JOB = " + i + "============================================");
			CloudSimulation cs = new CloudSimulation(1000, 1000, i, 0);
			cs.performSimulation();
			System.out.println("\n\n=========================================== JOB = " + i + "============================================");
		}
	}

}
