package net.ddns.sabr;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.motion.MotorPort;

/**
 * Created by Abdel on 04/11/2016.
 */
public class FD implements Runnable{

    private BrickPi brickPi;
    private int p;

    public FD(BrickPi brickPi, int p){
        this.brickPi = brickPi;
        this.p = p;
    }

    @Override
    public void run() {

        Motor motor1 = new Motor();
        brickPi.setMotor(motor1, MotorPort.MB);

        Motor motor2 = new Motor();
        brickPi.setMotor(motor2, MotorPort.MC);

        motor1.setCommandedOutput(20);
        motor2.setCommandedOutput(20);

        motor1.setEnabled(true);
        motor2.setEnabled(true);

        try {
            Thread.sleep(p*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        motor1.setEnabled(false);
        motor2.setEnabled(false);

    }
}
