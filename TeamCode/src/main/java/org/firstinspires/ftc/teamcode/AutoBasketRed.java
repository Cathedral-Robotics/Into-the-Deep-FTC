package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@com.qualcomm.robotcore.eventloop.opmode.Autonomous (name = "Basket")
public class AutoBasketRed extends LinearOpMode {

    protected DcMotor frontLeft;
    protected DcMotor backLeft;
    protected DcMotor frontRight;
    protected DcMotor backRight;
    protected DcMotor base;

    protected DcMotor slides;
    protected Servo claw;
    double slideSpeed = 0.4;
    double baseSpeed = 2;
    int ArmTarget = 0;
    int BaseHeight = 0;
    double open = 0.5;
    double close = 0.9;
    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        slides = hardwareMap.get(DcMotor.class, "slides");
        base = hardwareMap.get(DcMotor.class,"base");
        slides.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        claw = hardwareMap.get(Servo.class, "claw");

        slides.setTargetPosition(0);
        slides.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slides.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        base.setTargetPosition(0);
        base.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        base.setMode(DcMotor.RunMode.RUN_TO_POSITION);






        waitForStart();

        moveForward(1,100);
        sleep(500);
        turnLeft(0.75, 460);
        sleep(500);
        moveLeft(1, 100);
        sleep(300);
        moveForward(0.5,1000);
        arm(1, 3000);
        turnLeft(1, 150);
        moveForward(1, 150);
        drop(1, 5000);
        moveBackward(0.5, 600);
        sleep(1000);
        armDown(1, 5000);
        moveForward(0.8, 400);
    }
    public void moveForward (double power,int time){
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        claw.setPosition(0.9);
    }

    public void moveBackward (double power, int time) {
        frontLeft.setPower(-power);
        backLeft.setPower(-power);
        frontRight.setPower(-power);
        backRight.setPower(-power);
        sleep(time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
    public void turnRight (double power, int time) {
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(-power);
        backRight.setPower(-power);
        sleep(time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
    public void turnLeft (double power, int time) {
        frontLeft.setPower(-power);
        backLeft.setPower(-power);
        frontRight.setPower(power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
        claw.setPosition(close);
    }
    public void moveLeft (double power, int time) {
        frontLeft.setPower(-power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(-power);
        sleep(time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
    public void moveRight (double power, int time) {
        frontLeft.setPower(power);
        backLeft.setPower(-power);
        frontRight.setPower(-power);
        backRight.setPower(power);
        sleep(time);
        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);
    }
    public void test (double power, int time) {
        frontLeft.setPower(power);
        sleep(time);
        frontLeft.setPower(0);

    }
   public void arm (double power, int time) {
        base.setPower(power);
        base.setTargetPosition(1400);
        slides.setPower(power);
        slides.setTargetPosition(-1850);
        sleep(time);

   }
   public void drop (double power, int time) {
        base.setPower(power);
        base.setTargetPosition(1300);
        slides.setPower(power);
        slides.setTargetPosition(-1900);
        claw.setPosition(open);
        sleep(time);
   }
   public void armDown (double power, int time) {
        base.setPower(power);
        base.setTargetPosition(0);
        slides.setPower(power);
        slides.setTargetPosition(0);
        claw.setPosition(close);
        sleep(time);
   }
}