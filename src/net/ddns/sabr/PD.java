package net.ddns.sabr;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.motion.MotorPort;
import com.ergotech.brickpi.sensors.EV3TouchSensor;
import com.ergotech.brickpi.sensors.SensorPort;

/**
 * Created by Abdel on 04/11/2016.
 */
public class PD implements Runnable{

    private BrickPi brickPi;

    public PD(BrickPi brickPi){
        this.brickPi = brickPi;
    }


    @Override
    public void run() {

        Motor motor = new Motor();
        brickPi.setMotor(motor, MotorPort.MA);

        EV3TouchSensor touchSensor = new EV3TouchSensor();
        brickPi.setSensor(touchSensor, SensorPort.S1);

        motor.setCommandedOutput(-10);
        motor.setEnabled(true);
        while(touchSensor.getValue() != 0){} //TODO add multithreading to avoid this
        motor.setEnabled(false);

    }
}
