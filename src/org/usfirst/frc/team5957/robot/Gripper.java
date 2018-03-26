package org.usfirst.frc.team5957.robot;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;

public class Gripper {
	private VictorSP leftSpinny, rightSpinny;
	private Solenoid gripper;
	private static final boolean closed = true;
	private static final boolean open = false;
	private static final double intakeSpeed = -0.75;
	private static final double stallSpeed = -0.15;
	private static final double ejectSpeed = 0.75;
	private static final double maxRotation = 1;

	public Gripper(int leftMotor, int rightMotor, int PCM, int DS1) {
		leftSpinny = new VictorSP(leftMotor);
		rightSpinny = new VictorSP(rightMotor);
		rightSpinny.setInverted(true);
		leftSpinny.setInverted(true);
		gripper = new Solenoid(PCM, DS1);
		gripper.set(closed);
	}

	public void intake() {
		setSpinny(intakeSpeed);
	}

	public void eject() {
		setSpinny(ejectSpeed);
	}

	public void rotate(double speed) {
		double actual = maxRotation * speed;
		leftSpinny.set(actual);
		rightSpinny.set(-actual);
	}

	public void stall() {
		setSpinny(stallSpeed);
	}

	public void stop() {
		setSpinny(0);
	}

	public void open() {
		gripper.set(open);
	}

	public void close() {
		gripper.set(closed);
	}

	public void switchState() {
		if (gripper.get() == open) {
			close();
		} else if (gripper.get() == closed) {
			open();
		}
	}

	private void setSpinny(double speed) {
		leftSpinny.set(speed);
		rightSpinny.set(speed);
	}
}