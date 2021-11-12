package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.ftc.waterloo.h2oloobots.AttachmentControl;
import org.ftc.waterloo.h2oloobots.DriveTrain;
import org.ftc.waterloo.h2oloobots.TelemetryControl;

@Config
@TeleOp(name = "Main Drive Code", group = "!TeleOp")
public class Tele1 extends LinearOpMode {

    public DriveTrain driveTrain = new DriveTrain();
    public AttachmentControl attachmentControl = new AttachmentControl();
    public TelemetryControl telemetryControl = new TelemetryControl();

    double flpower, frpower, blpower, brpower;

    int flpos, frpos, blpos, brpos;

    int lmpos, lhpos;

    public void runOpMode() {

        driveTrain.FourMotorInit(true, hardwareMap, DcMotor.ZeroPowerBehavior.BRAKE);
        attachmentControl.attachmentInit(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {

            flpower = driveTrain.fl.getPower();
            frpower = driveTrain.fr.getPower();
            blpower = driveTrain.bl.getPower();
            brpower = driveTrain.br.getPower();

            flpos = driveTrain.fl.getCurrentPosition();
            frpos = driveTrain.fr.getCurrentPosition();
            blpos = driveTrain.bl.getCurrentPosition();
            brpos = driveTrain.br.getCurrentPosition();

            lmpos = attachmentControl.LiftMotor.getCurrentPosition();
            lhpos = attachmentControl.LiftHinge.getCurrentPosition();

            driveTrain.MecanumTeleOp(gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x, true, telemetry);
            attachmentControl.blueDuckMotorTeleop(gamepad1.dpad_left);
            attachmentControl.redDuckMotorTeleop(gamepad1.dpad_right);
            attachmentControl.liftMotorMove(gamepad1.y, gamepad1.a);
            attachmentControl.hingeMotorTeleOp(gamepad2.y, gamepad2.a);
//            attachmentControl.SetLiftMotorPos(gamepad1.a, AttachmentControl.LiftMotorPosition.BOTTOM);
//            attachmentControl.SetLiftMotorPos(gamepad1.y, AttachmentControl.LiftMotorPosition.HIGH);
//            attachmentControl.SetLiftMotorPos(gamepad1.b, AttachmentControl.LiftMotorPosition.LOW);
//            attachmentControl.SetLiftMotorPos(gamepad1.x, AttachmentControl.LiftMotorPosition.MIDDLE);
            attachmentControl.intakeMotorTeleOp(gamepad1.right_trigger > 0.9, gamepad1.right_bumper);

//            telemetry.addData("Lift Hinge", attachmentControl.LiftHinge.getCurrentPosition());
//            telemetry.update();
            telemetryControl.telemetryUpdate(telemetry, "Front Right Encoder", String.valueOf(frpos));
            telemetryControl.telemetryUpdate(telemetry, "Front Left Encoder", String.valueOf(flpos));
            telemetryControl.telemetryUpdate(telemetry, "Back Right Encoder", String.valueOf(brpos));
            telemetryControl.telemetryUpdate(telemetry, "Back Left Encoder", String.valueOf(blpos));
            telemetryControl.telemetryUpdate(telemetry, "Lift Hinge Position", String.valueOf(lhpos));
            telemetryControl.telemetryUpdate(telemetry, "Lift Motor Position", String.valueOf(lmpos));
            telemetryControl.motorTelemetryUpdate(telemetry, flpower, frpower, blpower, brpower);
            telemetryControl.update(telemetry);

        }

    }

}
