
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScaniaTest {
    private Scania scania;

    @BeforeEach
    void setup() {
        scania = new Scania();
    }

    @Test
    void raiseRampTest() {
        scania.startEngine();
        assertThrows(IllegalArgumentException.class, () -> scania.raiseRamp());
    }

    @Test
    void startEngineRampUpTest() {
        scania.raiseRamp();
        assertThrows(IllegalArgumentException.class, () -> scania.startEngine());
    }

    // Edge case flatBed
    @Test
    void negativeRampAngleTest() {
        scania.lowerRamp();
        assertEquals(0, scania.getFlatBedAngle());
    }

    @Test
    void maxRampAngleTest() {
        for (int i = 0; i < 10; i++) {
            scania.raiseRamp();
        }
        assertEquals(70, scania.getFlatBedAngle());
    }

    @Test
    void invalidRampAngleTest() {
        assertThrows(IllegalArgumentException.class, () -> scania.setRampAngle(-1));
        assertThrows(IllegalArgumentException.class, () -> scania.setRampAngle(71));
    }

    @Test
    void raiseRampAngleTest() {
        scania.raiseRamp();
        assertEquals(10, scania.getFlatBedAngle());
    }

    @Test
    void setRampAngleTest() {
        scania.setRampAngle(35);
        assertEquals(35, scania.getFlatBedAngle());
    }

    @Test
    void setInvalidRampAngleTest() {
        assertThrows(IllegalArgumentException.class, () -> scania.setRampAngle(-1));
        assertThrows(IllegalArgumentException.class, () -> scania.setRampAngle(71));
    }
}