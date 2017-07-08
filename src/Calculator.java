
public class Calculator {
	
	private double windSpeed;
	private double rotationSpeed;
	
	public Calculator(double windSpeed, double rotationSpeed) {
		this.windSpeed = windSpeed;
		this.rotationSpeed = rotationSpeed;
	}
	
	public double getAngleForRadius(double r) {
		return Math.atan((2*Math.PI*r*rotationSpeed)/windSpeed);
	}

}
