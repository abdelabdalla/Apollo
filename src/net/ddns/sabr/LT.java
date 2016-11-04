package net.ddns.sabr;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.motion.MotorPort;

/**
 * Created by Abdel on 04/11/2016.
 */
public class LT implements Runnable{

    private BrickPi brickPi;
    private int p;

    public LT(BrickPi brickPi, int p){
        this.brickPi = brickPi;
        this.p = p;
    }

    @Override
    public void run() {
        Motor motor = new Motor();
        brickPi.setMotor(motor, MotorPort.MD);

        motor.setCommandedOutput(-20);

        motor.setEnabled(true);

        try {
            Thread.sleep(p*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        motor.setEnabled(false);
    }
}
