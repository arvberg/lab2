
import java.awt.*;

public class Saab95 extends Cars {
    boolean turboOn = false;

    public Saab95() {
        super(2, 125, Color.RED, "Saab95");
    }

    protected void setTurboOn() {
        turboOn = true;
    }

    protected void setTurboOff() {
        turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turbo = 1;
        if (turboOn)
            turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}