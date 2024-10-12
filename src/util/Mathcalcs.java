package util;

public class Mathcalcs {
	
    public static double Discount(double a, double b) {
        return a - (a * (b / 100));
    }
    
    public static double Increment(double a, double b) {
        return a + (a * (b / 100));
    }
    
    public static double Sampling(double a, double b) {
    	return (a * b) / 100;
    }
    
    public static double Sampling2(double a, double b) {
    	return (b / a) * 100;
    }
    
    public static double CalculateDiscountPercentage(double a, double b) {
    	return ((a - b) / a) * 100;
    }
    
    public static double CalculatePercentageChange(double a, double b) {
    	return ((b - a) / a) * 100;
    }
    
    public static double CalculateOriginalPrice(double a, double b) {
    	return (a * 100 / (100 - b));
    }
    
    public static double CalculateRuleOfThree(double a, double b, double r1) {
    	return (r1 * b) / a;
    }
}
