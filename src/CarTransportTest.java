
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarTransportTest {
    private Cars volvo240;
    private Cars saab;
    private CarTransport carTransport;

    @BeforeEach
    void setup() {
        carTransport = new CarTransport();
        volvo240 = new Volvo240();
        saab = new Saab95();
    }

    @Test
    void addGetCarTest() {
        carTransport.lowerRamp();
        carTransport.addCar(volvo240);
        carTransport.addCar(saab);
        assertEquals(saab, carTransport.getLastCar());
        assertEquals(volvo240, carTransport.getLastCar());
    }

    @Test
    void addCarRampUp() {
        assertThrows(IllegalArgumentException.class, () -> carTransport.addCar(volvo240));
    }

    @Test
    void addCarTooFarAway() {
        carTransport.lowerRamp();
        volvo240.startEngine();
        volvo240.gas(1);
        for (int i = 0; i < 10; i++) {
            volvo240.move();
        }
        assertThrows(IllegalArgumentException.class, () -> carTransport.addCar(volvo240));
    }

    @Test
    void moveCarsAndCarTransport() {
        carTransport.lowerRamp();
        carTransport.addCar(volvo240);
        carTransport.raiseRamp();

        carTransport.startEngine();
        carTransport.gas(1);
        carTransport.move();

        assertEquals(carTransport.getPosition(), volvo240.getPosition());
    }

    @Test
    void addCarTransportToCarTransport() {
        CarTransport carTransport2 = new CarTransport();
        carTransport.lowerRamp();
        assertThrows(IllegalArgumentException.class, () -> carTransport.addCar(carTransport2));
    }

    @Test
    void addScaniaToCarTransport() {
        Scania scania = new Scania();
        carTransport.lowerRamp();
        assertThrows(IllegalArgumentException.class, () -> carTransport.addCar(scania));
    }

    @Test
    void raiseRampWhileMoving() {
        carTransport.startEngine();
        carTransport.gas(1);
        assertThrows(IllegalAccessError.class, () -> carTransport.raiseRamp());
    }

    @Test
    void lowerRampWhileMoving() {
        carTransport.startEngine();
        carTransport.gas(1);
        assertThrows(IllegalAccessError.class, () -> carTransport.lowerRamp());
    }

    @Test
    void addCarExceedingCapacity() {
        carTransport.lowerRamp();
        for (int i = 0; i < 6; i++) {
            carTransport.addCar(new Volvo240());
        }
        assertThrows(IllegalArgumentException.class, () -> carTransport.addCar(new Saab95()));
    }

    @Test
    void getCarFromEmptyTransport() {
        carTransport.lowerRamp();
        assertThrows(IllegalArgumentException.class, () -> carTransport.getLastCar());
    }

    @Test
    void startEngineWithRampDown() {
        carTransport.lowerRamp();
        assertThrows(IllegalArgumentException.class, () -> carTransport.startEngine());
    }

    @Test
    void getCarWithRampUp() {
        carTransport.lowerRamp();
        carTransport.addCar(volvo240);
        carTransport.raiseRamp();
        assertThrows(IllegalAccessError.class, () -> carTransport.getLastCar());
    }

    @Test
    void addSameCarTwice() {
        carTransport.lowerRamp();
        carTransport.addCar(volvo240);
        assertThrows(IllegalArgumentException.class, () -> carTransport.addCar(volvo240));
    }

    @Test
    void addLoadedCar() {
        carTransport.lowerRamp();
        carTransport.addCar(volvo240);
        CarTransport carTransport2 = new CarTransport();
        carTransport2.lowerRamp();

        assertThrows(IllegalArgumentException.class, () -> carTransport2.addCar(volvo240));
    }

}