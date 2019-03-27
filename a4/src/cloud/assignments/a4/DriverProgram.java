package cloud.assignments.a4;

public class DriverProgram {
	public static void main(String[] args) {
		question1();
	}

	private static void question1() {
		System.out.println("cloud.assignments.a4 - Running Q1");
		double k = 30; // strike price
		double s = 70; // asset
		double t = 1; // 1 year, 12 months == 12 steps (N)
		double r = 0.12; // rate == 12%
		int n = 12; // number of steps == N
		double u = 1.67; // up value
		double d = 1.0/u; // down value
		
		
		for (int i = 1; i <= n; i++) {
			AmericanPutOption option = new AmericanPutOption(k, t, s, r, i, u, d);
			double americanOptionValue = option.setAndReturnAmericanPutValue();
			System.out.println(
					/* "American Option Value = " + */ americanOptionValue);
		}
			
		
		
		
		
	}

}
