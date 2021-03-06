/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package ca.warp7.frc2020;

import ca.warp7.frc2020.lib.control.PID;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveKinematicsConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.trajectory.constraint.TrajectoryConstraint;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Configuration

    public static final boolean kDebugCommandScheduler = false;
    public static final boolean kUseKinematicsDrive = false;
    public static final boolean kUseNotifierForMainLoop = false;

    // CAN IDs

    public static final int kDriveLeftMasterID = 31;
    public static final int kDriveLeftFollowerID = 32;

    public static final int kDriveRightMasterID = 34;
    public static final int kDriveRightFollowerID = 33;

    public static final int kFlywheelShooterMasterID = 22;
    public static final int kFlywheelShooterFollowerID = 20;

    public static final int kHopperID = 24;
    public static final int kIntakeID = 4;

    public static final int kClimberMasterID = 1;
    public static final int kClimberFollowerID = 2;

    public static final int kFeederOuterID = 1;
    public static final int kFeederInnerID = 2;

    public static final int kControlPanelManipulatorID = -1;


    // PCM IDs

    public static final int kFlywheelHoodActuatorID = 4;
    public static final int kIntakeExtensionID = 5;
    public static final int kClimberLockActuatorID = 6;
    public static final int kDriveShifterID = 7;

    // DIO IDs

    public static final int kPhotoSensorID = 0;

    // Drive Train Tuning

    public static final PID kAutonLowGearVelocityPID =
            new PID(1.0, 0.0, 5.0, 0.0);
    public static final PID kTeleopLowGearVelocityPID =
            new PID(0.0, 0.0, 0.0, 0.0);
    public static final PID kTeleopHighGearVelocityPID =
            new PID(0.0, 0.0, 0.0, 0.0);
    public static final PID kVisionAlignmentYawPID =
            new PID(0.1, 0.0, 0.2, 0.0);

    // Flywheel Tuning


    public static final double flywheelDefaultCloseRPS = 54.0;
    public static final double flywheelFarRPS = 80.0;
    public static final double kFlywheelKp = 0.0;//2.24;
    public static final double kFlywheelKs = 0.0938;
    public static final double kFlywheelKv = 0.0666;
    public static final double kFlywheelKa = 0.0455;
    public static final double kFlywheelGearRatio = 1.0 / 2.0; // 0.5

    // Intake Constants

    public static final double intakingSpeed = 0.3; // percent

    //Feeder Constants

    public static final double kFeedingSpeed = 0.6; //percent

    //Hopper constants

    public static final double kHopperSpeed = 0.6; //percent
    // Drive Train Constants

    public static final double kWheelBaseRadius = 0.35; // metres
    public static final double kDriveWheelRadius = 3.0 * 0.0254; // 0.1524 meters
    public static final double kMaxVoltage = 12.0; // volts
    public static final DifferentialDriveKinematics kKinematics =
            new DifferentialDriveKinematics(kWheelBaseRadius * 2);

    public static class LowGear {
        public static final double kGearRatio = 42.0 / 10.0 * 60.0 / 14.0; // 18.0

        public static final double kMetresPerRotation =
                (2 * Math.PI * kDriveWheelRadius) / kGearRatio; // ticks/m

        public static final SimpleMotorFeedforward kTransmission =
                new SimpleMotorFeedforward(0.0534, 4.180, 0.429);

    }

    public static class HighGear {
        public static final double kGearRatio = 42.0 / 10.0 * 50.0 / 24.0; // 8.75

        public static final double kMetresPerRotation =
                (2 * Math.PI * kDriveWheelRadius) / kGearRatio; // m/rotation

        public static final SimpleMotorFeedforward kTransmission =
                new SimpleMotorFeedforward(1.0, 12.0, 0.4);
    }

    public static final TrajectoryConstraint kKinematicsConstraint =
            new DifferentialDriveKinematicsConstraint(kKinematics, 10.0);

    public static final TrajectoryConstraint kVoltageConstraint =
            new DifferentialDriveVoltageConstraint(LowGear.kTransmission, kKinematics, kMaxVoltage);

    public static final TrajectoryConfig kTrajectoryConfig =
            new TrajectoryConfig(2.2, 1.0)
                    .addConstraint(kKinematicsConstraint)
                    .addConstraint(kVoltageConstraint);
}
