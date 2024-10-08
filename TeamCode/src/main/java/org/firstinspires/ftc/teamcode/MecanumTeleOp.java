package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp (name = "RR Code")
public class MecanumTeleOp extends LinearOpMode {

    boolean pGA2Y = false;
    boolean pGA2A = false;
    boolean pGA2down = false;

    boolean pGA1Y = false;
    boolean pGA1A = false;
    boolean pGA1down = false;

    boolean pGA2up = false;

    boolean pGA1up = false;

    String status = "pickup";



    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");

        DcMotor slides = hardwareMap.dcMotor.get("slides");
        DcMotor intake = hardwareMap.dcMotor.get("intake");




        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests
        frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        slides.setTargetPosition(0);
        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int ArmTarget = 0;

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        // Wait for the game to start
        // .
        // (driver presses PLAY)


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = gamepad1.left_stick_y; // Remember, this is reversed!
            double x = -gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing
            double rx = -gamepad1.right_stick_x;

            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            /*
            if (gamepad2.left_trigger >.5 ) {
                intake.setPower(1);
            }
            if (gamepad2.right_trigger >.5 ) {
                intake.setPower(-1);
            }

            if (gamepad2.left_trigger <.5  && gamepad2.right_trigger <.5) {
                intake.setPower(0);
            }
**/


//code for manual override adjustments for lift

            boolean ga2y = gamepad2.y;
            boolean ga1y = gamepad1.y;

            boolean ga2a = gamepad2.a;
            boolean ga1a = gamepad1.a;


            boolean ga2down = gamepad2.dpad_down;
            boolean ga1down = gamepad1.dpad_down;

            boolean ga1up = gamepad1.dpad_up;
            boolean ga2up = gamepad2.dpad_up;


//up down fine tune controller 2


//up down fine tune controller 1


            //stops motor after no input
            frontLeft.setZeroPowerBehavior (DcMotor.ZeroPowerBehavior.BRAKE);
            backLeft.setZeroPowerBehavior (DcMotor.ZeroPowerBehavior.BRAKE);
            frontRight.setZeroPowerBehavior (DcMotor.ZeroPowerBehavior.BRAKE);
            backRight.setZeroPowerBehavior (DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
}