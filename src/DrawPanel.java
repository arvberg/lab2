import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{


    private static class CarPosition{
        BufferedImage carImage;
        Point carPoint;

        public CarPosition(BufferedImage carImage, Point carPoint){
            this.carImage = carImage;
            this.carPoint = carPoint;
        }
    }
    private final ArrayList<CarPosition> carPositions = new ArrayList<>();
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint = new Point(300,300);

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Remember to right-click src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            //volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            volvoWorkshopImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg")));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    public void addCar(BufferedImage carImage){
        int yPosition = carPositions.size() * 100;
        carPositions.add(new CarPosition(carImage,new Point(0,yPosition)));
    }

    public void moveCar(int carIndex, int x, int y){
        if (carIndex > 0 && carIndex < carPositions.size()){
            carPositions.get(carIndex).carPoint.setLocation(x, y);
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters
        g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);

        if (volvoWorkShopImage != null){
            g.drawImage(volvoWorkshopImage, volvoWorkshopPoint.x, volvoWorkshopPoint.y, null);
        }

        for (CarPosition carPosition : carPositions){
            g.drawImage(carPosition.carImage, carPosition.carPoint.x, carPosition.carPoint.y, null);
        }
    }
}
