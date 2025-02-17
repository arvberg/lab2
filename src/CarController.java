import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Cars> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());
        cc.cars.add(new Scania());

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Cars car : cars) {
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());

                if (x < 0 || x > frame.getWidth() - 100) {
                    car.stopEngine();
                    car.turnLeft();
                    car.turnLeft();
                    car.startEngine();
                }

                if (car instanceof Volvo240){
                    if (x == frame.drawPanel.volvoWorkshopPoint.x && y == frame.drawPanel.volvoWorkshopPoint.y) {
                        car.stopEngine();
                        // TODO: Load garage
                    }
                }

                frame.drawPanel.moveit(x, y);
                // repaint() calls the paintComponent method of the panel
            }
            frame.drawPanel.repaint();
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
       for (Cars car : cars
                ) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double) amount) / 100;
        for (Cars car : cars){
            car.brake(brake);
        }
    }

    void startAllCars(){
        for (Cars car : cars){
            car.startEngine();
        }
    }

    void stopAllCars(){
        for (Cars car : cars){
            car.stopEngine();
        }
    }

    void setTurboOn(){
        for (Cars car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOn();
            }
        }
    }

    void setTurboOff(){
        for (Cars car : cars){
            if (car instanceof Saab95){
                ((Saab95) car).setTurboOff();
            }
        }
    }

    void liftFlatbed(int angle){
        for (Cars car : cars){
            if (car instanceof Scania){
                ((Scania) car).raiseRamp(angle);
            }
        }
    }

    void lowerFlatbed(int angle){
        for (Cars car : cars){
            if (car instanceof Scania){
                ((Scania) car).lowerRamp(angle);
            }
        }
    }
}
