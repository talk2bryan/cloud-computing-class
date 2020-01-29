package com.wodi.backend;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.cloudbus.cloudsim.Log;

@ManagedBean
@RequestScoped
public class Job implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int bw;
	private int mips;
	private int numJobs;
	private int vmCount;
	
	
	public Job() {
		Log.printLine("====== Staring Cloud Simulation =====");
		System.out.println("======= Simulation about to start======");
	}
	
	
	public void submitJobRequest() {		
		CloudSimulation cs = new CloudSimulation(bw, mips, numJobs, vmCount);
		cs.performSimulation();
	}



	public String getMessage() {
		return "Simulation started!";
	}
	
	public int getBw() {
		return bw;
	}

	public void setBw(int bw) {
		this.bw = bw;
	}

	public int getMips() {
		return mips;
	}

	public void setMips(int mips) {
		this.mips = mips;
	}

	public int getNumJobs() {
		return numJobs;
	}

	public void setNumJobs(int numJobs) {
		this.numJobs = numJobs;
	}

	public int getVmCount() {
		return vmCount;
	}

	public void setVmCount(int vmCount) {
		this.vmCount = vmCount;
	}

}
