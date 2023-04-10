package project;

public class PID {
  /**
   * The kP instance variable represents the proportional gain.
   */
  private double kP;
  /**
   * The kI instance variable represents the integral gain.
   */
  private double kI;
  /**
   * The kD instance variable represents the derivative gain.
   */
  private double kD;

  /**
   * The lastError instance variable represents the last error.
   */
  private double lastError;
  /**
   * The integral instance variable represents the integral of the error.
   */
  private double integral;

  /**
   * The constructor for the PID class.
   * @param kP the proportional gain
   * @param kI the integral gain
   * @param kD the derivative gain
   */
  public PID(double kP, double kI, double kD) {
    this.kP = kP;
    this.kI = kI;
    this.kD = kD;
    this.lastError = 0;
    this.integral = 0;
  }

  /**
   * The calculate method calculates the output of the PID controller.
   * @param setpoint the setpoint
   * @param pv the process variable
   * @param dt the time step
   * @return the output of the PID controller
   */
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
}

