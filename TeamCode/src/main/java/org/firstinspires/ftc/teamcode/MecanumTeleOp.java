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
    double baseSpeed = 2;
    double slideSpeed= 0.4;


    String status = "pickup";

    public Servo claw;

    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor base = hardwareMap.dcMotor.get("base");
        DcMotor slides = hardwareMap.dcMotor.get("slides");

        claw = hardwareMap.get(Servo.class,"claw");


        // Reverse the right side motors
        // Reverse left motors if you are using NeveRests

        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        claw.setPosition(.9);
        slides.setTargetPosition(0);
        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int ArmTarget=0;

        base.setTargetPosition(0);
        base.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        base.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        int BaseHeight = 0;
        

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

        if (gamepad2.dpad_up) {
            BaseHeight= 350;
            baseSpeed=1;
            slideSpeed=0.4;
        }

        if (gamepad2.dpad_down) {
            BaseHeight = 70;
            baseSpeed=0.2;
            slideSpeed=0.4;
        }



        if (gamepad2.left_bumper  ) {
            claw.setPosition(0.5    );
        }

        if (gamepad2.right_bumper) {
            claw.setPosition(0.9);
        }



            if (gamepad2.b){
                BaseHeight=1400;
                ArmTarget=1850;
                baseSpeed = 2;
                slideSpeed=0.4;
            }


            if (gamepad2.a){
               BaseHeight=0;
                ArmTarget=0;
                baseSpeed = 2;
                slideSpeed=0.4;
            }

            if (gamepad2.y){
                BaseHeight=1500;
                ArmTarget=2870;
                baseSpeed = baseSpeed-1.3;
                slideSpeed=0.4;
            }

            if (gamepad2.x)  {
                ArmTarget=700;
                slideSpeed=slideSpeed+0.2;

            }


            if (gamepad2.dpad_right) {
                ArmTarget=1600;
                slideSpeed=0.4;
            }

            if (gamepad2.dpad_left) {
                ArmTarget=0;
                slideSpeed=0.4;
            }
            if (gamepad1.dpad_up) {
                BaseHeight=900;
                ArmTarget=2500;
                slideSpeed=0.4;
            }
            if (gamepad1.dpad_down) {
                BaseHeight=900;
                ArmTarget=0;
                slideSpeed=0.4;
            }



            //Hanging
            /*
            if (gamepad1.dpad_up) {
                BaseHeight=BaseHeight+300;
            }
            if (gamepad1.dpad_down) {
                BaseHeight=BaseHeight-300;
            }


            double righttrigger = gamepad1.right_trigger;
            double lefttrigger = gamepad1.left_trigger;

            slides.setPower(righttrigger-lefttrigger);
    */



            telemetry.addData("Lift Position", slides.getCurrentPosition());
            telemetry.addData("Base Position", base.getCurrentPosition());

            slides.setTargetPosition(-ArmTarget);
            slides.setPower(slideSpeed);
            base.setTargetPosition(BaseHeight);
            base.setPower(baseSpeed);

//code for manual override adjustments for lift


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