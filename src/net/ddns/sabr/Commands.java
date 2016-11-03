package net.ddns.sabr;

import com.ergotech.brickpi.BrickPi;
import com.ergotech.brickpi.motion.Motor;
import com.ergotech.brickpi.motion.MotorPort;
import com.ergotech.brickpi.sensors.EV3TouchSensor;
import com.ergotech.brickpi.sensors.Sensor;
import com.ergotech.brickpi.sensors.SensorPort;

/**
 * Created by Abdel on 30/10/2016.
 */
public class Commands {

    private BrickPi brickPi;

    public void PU(){

        setBrickPi();

        Motor motor = new Motor();
        brickPi.setMotor(motor, MotorPort.MA);

        EV3TouchSensor touchSensor = new EV3TouchSensor();
        brickPi.setSensor(touchSensor, SensorPort.S1);

        motor.setCommandedOutput(10);
        motor.setEnabled(true);
        while(touchSensor.getValue() == 0){} //TODO add multithreading to avoid this
        motor.setEnabled(false);

    }

    public void PD(){

        setBrickPi();

        Motor motor = new Motor();
        brickPi.setMotor(motor, MotorPort.MA);

        EV3TouchSensor touchSensor = new EV3TouchSensor();
        brickPi.setSensor(touchSensor, SensorPort.S1);

        motor.setCommandedOutput(-10);
        motor.setEnabled(true);
        while(touchSensor.getValue() != 0){} //TODO add multithreading to avoid this
        motor.setEnabled(false);
    }

    public void FD(int p){

        setBrickPi();
    }

    public void BK(int p){

        setBrickPi();
    }

    public void LT(int p){

        setBrickPi();
    }

    public void RT(int p){

        setBrickPi();
    }

    public void setorientation(int h){

        setBrickPi();
    }

    public void setpos(int x, int y){

        setBrickPi();
    }

    private void setBrickPi(){
        if(brickPi==null) brickPi = BrickPi.getBrickPi();
    }

}
