package net.ddns.sabr;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.motion.MotorPort;
import com.ergotech.brickpi.sensors.EV3TouchSensor;
import com.ergotech.brickpi.sensors.SensorPort;

/**
 * Created by Abdel on 04/11/2016.
 */
public class SETPOS implements Runnable {

    private BrickPi brickPi;
    private int x;
    private int y;
    private boolean root;

    public SETPOS(BrickPi brickPi) {
        this.brickPi = brickPi;
        this.root = true;
    }

    public SETPOS(BrickPi brickPi, int x, int y) {
        this.brickPi = brickPi;
        this.x = x;
        this.y = y;
        this.root = false;
    }

    @Override
    public void run() {
        Motor motor1 = new Motor();
        brickPi.setMotor(motor1, MotorPort.MB);

        Motor motor2 = new Motor();
        brickPi.setMotor(motor2, MotorPort.MC);

        EV3TouchSensor touchSensor1 = new EV3TouchSensor();
        brickPi.setSensor(touchSensor1, SensorPort.S2);

        EV3TouchSensor touchSensor2 = new EV3TouchSensor();
        brickPi.setSensor(touchSensor2, SensorPort.S3);

        motor1.setCommandedOutput(-10);
        motor2.setCommandedOutput(-10);
        motor1.setEnabled(true);
        motor2.setEnabled(true);
        while (touchSensor1.getValue() == 0 && touchSensor2.getValue() == 0) {
        } //TODO add multithreading to avoid this
        motor1.setEnabled(false);
        motor2.setEnabled(false);

        if (root) {
            motor1.setCommandedOutput(10);
            motor2.setCommandedOutput(10);
            motor1.setEnabled(true);
            motor2.setEnabled(true);
            while (motor1.getCurrentEncoderValue() == x && motor2.getCurrentEncoderValue() == y) {
            } //TODO add multithreading to avoid this
            motor1.setEnabled(false);
            motor2.setEnabled(false);
        }
    }
}
