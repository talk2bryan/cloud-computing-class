package cloud.assignments.a4;

import java.util.Random;

public class MonteCarloSimulation {
	
	int k, t, s, sigma, theta, n, m;
	
	private double se, dt, nudt, sigsdt, lnS, sum_CT, sum_CT2;
	
	private static final double RATE = 0.12;

	public MonteCarloSimulation(int k, int t, int s, int sigma, int theta,
			int n, int m) {
		this.k = k;
		this.t = t;
		this.s = s;
		this.sigma = sigma;
		this.theta = theta;
		this.n = n;
		this.m = m;
		
		this.se = 0.0;
		this.dt = 0.0;
		
		
		setAndReturnSE();
	}

	private void setAndReturnSE() {
		computeConstants();
		 getOptionValueFromSimulations();
		
	}

	private double getOptionValueFromSimulations() {
		
		for (int j = 1; j < m; j++) {
			double lnSt = lnS;
			for (int i = 1; i < n; i++) {
				double epsilon = new Random().nextGaussian();
				lnSt += nudt + sigsdt * epsilon;
			}
			double ST = Math.log(lnSt);
			double CT = Math.max(0.0, ST-k);
			sum_CT += CT;
			sum_CT2 += Math.pow(CT, 2);
		}
		double optionValue = sum_CT/m * Math.log(-RATE*t);
		
		double product = ( sum_CT2 - (Math.pow(sum_CT, 2)/m) ) * ( (Math.log(
				-2*RATE*t))/(m-1) );
		double standardDeviation = Math.sqrt(product);
		double standardError = standardDeviation/Math.sqrt(m);
		
		return standardError;
	}

	private void computeConstants() {
		dt = t/n;
		nudt = ( RATE - theta - (Math.pow(sigma, 2)/2) ) * dt;
		sigsdt = sigma * Math.pow(dt, 0.5);
		lnS = Math.log(s);
		
		sum_CT = 0;
		sum_CT2 = 0;
	}
	

}
