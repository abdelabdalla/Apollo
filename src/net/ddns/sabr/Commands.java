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

    public void BK(int p){

        setBrickPi();

        Motor motor1 = new Motor();
        brickPi.setMotor(motor1, MotorPort.MB);

        Motor motor2 = new Motor();
        brickPi.setMotor(motor2, MotorPort.MC);

        motor1.setCommandedOutput(-20);
        motor2.setCommandedOutput(-20);

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

    public void LT(int p){

        setBrickPi();

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

    public void RT(int p){

        setBrickPi();

        Motor motor = new Motor();
        brickPi.setMotor(motor, MotorPort.MD);

        motor.setCommandedOutput(20);

        motor.setEnabled(true);

        try {
            Thread.sleep(p*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        motor.setEnabled(false);
    }

    public void setorientation(int h){

        setBrickPi();
    }

    public void setpos(){

        setBrickPi();
        PU();

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
        while(touchSensor1.getValue() == 0 && touchSensor2.getValue() == 0){} //TODO add multithreading to avoid this
        motor1.setEnabled(false);
        motor2.setEnabled(false);

    }

    public void setpos(int x, int y){

        setBrickPi();
        setpos();

        Motor motor1 = new Motor();
        brickPi.setMotor(motor1, MotorPort.MB);

        Motor motor2 = new Motor();
        brickPi.setMotor(motor2, MotorPort.MC);

        motor1.setCommandedOutput(10);
        motor2.setCommandedOutput(10);
        motor1.setEnabled(true);
        motor2.setEnabled(true);
        while(motor1.getCurrentEncoderValue() == x && motor2.getCurrentEncoderValue() == y){} //TODO add multithreading to avoid this
        motor1.setEnabled(false);
        motor2.setEnabled(false);

    }

    private void setBrickPi(){
        if(brickPi==null) brickPi = BrickPi.getBrickPi();
    }

}
