package cloud.assignments.a4;

import java.util.ArrayList;

public class AmericanPutOption {
	private int n;
	private double k, t, s, r, u, d;
	
	// Constants that need to be computed when we receive the above params
	// via the constructor.
	private double dt, p, disc, americanPutValue;
	
	private ArrayList<Double> stArrayList, cArrayList;

	public AmericanPutOption(double k, double t, double s, double r, int n, 
			double u, double d) {
		this.k = k;
		this.t = t;
		this.s = s;
		this.r = r;
		this.n = n;
		this.u = u;
		this.d = d;
		
		this.americanPutValue = 0.0;
		this.disc = 0.0;
		stArrayList = new ArrayList<Double>();
		cArrayList = new ArrayList<Double>();	
		
	}

	public double setAndReturnAmericanPutValue() {
		computeConstants();
		initAssetPrices();
		initOptionValuesAtMaturity();
		applyEarlyExerciseCondition();
		
		this.americanPutValue = cArrayList.get(0);

		return this.americanPutValue;
		
	}

	private void applyEarlyExerciseCondition() {
		for (int i = n-1; i >= 0; i--) {
			for (int j = 0; j < i; j++) {
				double val = disc * (p * cArrayList.get(j+1) + (1-p) * 
						cArrayList.get(j));
				cArrayList.set(j, val);
				double element = stArrayList.get(j) / d;
				stArrayList.set(j, element);
				cArrayList.set(j, Math.max(cArrayList.get(j), k - 
						stArrayList.get(j)));
			}
		}
		
		
	}

	private void initOptionValuesAtMaturity() {
		for (int i = 0; i < this.n; i++) {
			cArrayList.add( Math.max(0.0, this.k - stArrayList.get(i)));
		}
		
	}

	private void initAssetPrices() {
		stArrayList.add(0, this.s * Math.pow(this.d, this.n));
		for (int i = 1; i < this.n; i++) {
			stArrayList.add( stArrayList.get(i-1) * (this.u / this.d) );
		}
		
	}
 
	private void computeConstants() {
		this.dt = this.dt / this.n;
		this.p = (Math.exp(this.r * this.dt) - this.d) / (this.u - this.d);
		this.disc = Math.exp(-this.r * this.dt);		
	}
	
	
	
	

}
