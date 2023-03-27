package project;

public class PID {
  private double kP;
  private double kI;
  private double kD;

  private double lastError;
  private double integral;

  public PID(double kP, double kI, double kD) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.lastError = 0;
    this.integral = 0;
  }

  public double calculate(double setpoint, double pv, double dt) {
    double error = setpoint - pv;

    // Calculate the proportional term
    double Pout = kP * error;

    // Calculate the integral term
    integral += error * dt;
    double Iout = kI * integral;

    // Calculate the derivative term
    double derivative = (error - lastError) / dt;
    double Dout = kD * derivative;

    // Calculate the total output
    double output = Pout + Iout + Dout;

    // Store the last error for the next iteration
    lastError = error;

    return output;
  }

  public void reset() {
    this.lastError = 0;
    this.integral = 0;
  }

  public double getkP() {
    return kP;
  }

  public void setkP(double kP) {
    this.kP = kP;
  }

  public double getkI() {
    return kI;
  }

  public void setkI(double kI) {
    this.kI = kI;
  }

  public double getkD() {
    return kD;
  }

  public void setkD(double kD) {
    this.kD = kD;
  }
}

