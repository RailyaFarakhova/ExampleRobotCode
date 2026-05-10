package frc.robot.Subsystems;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SampleConstants;

public class Subsystem extends SubsystemBase {
    private TalonFX m_motor;

    private DutyCycleOut m_dutyCycleOut;
    private PositionVoltage m_positionVoltage;

    private TalonFXConfiguration m_motorConfig;

    public Subsystem() {
        m_motor = new TalonFX(SampleConstants.motorID);
        m_dutyCycleOut = new DutyCycleOut(SampleConstants.maxRange);
        m_positionVoltage = new PositionVoltage(0);
        m_motorConfig = new TalonFXConfiguration(); 
        m_motorConfig.Slot0.kP = SampleConstants.kP;

        m_motor.getConfigurator().apply(m_motorConfig);

    }

    // Sets the motor's initial rotation value to 0.
    public void setStartPosition() {
        m_motor.setPosition(0);
    }

    /**
     * Controls motor power from range of -1 to 1.
     * @param speedPercentage from -1 to 1.
     */
    public void motorOn(double speedPercentage) {
        m_motor.setControl(m_dutyCycleOut.withOutput(speedPercentage)); 
    }

    /**
     * Sets the position of the motor using rotations.
     * @param motorPosition 
     */
    public void setPosition(double motorPosition) {
        m_motor.setControl(m_positionVoltage.withPosition(motorPosition));
    }

    // Powers off motor.
    public void motorOff() {
        m_motor.setControl(m_dutyCycleOut.withOutput(0));
    }

    // Returns the motor's rotation.
    public double getMotorRotation() {
        return m_motor.getPosition().getValueAsDouble();
    }

    @Override
    public void periodic(){
        // Example of displaying data in Elastic. This will be visible in the Network Tables.
        SmartDashboard.putNumber("Magic Number", 100);
    }
}
