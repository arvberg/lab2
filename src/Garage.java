import java.util.List;
import java.util.ArrayList;

public class Garage<T extends Cars> {
    private final int maxCapacity;
    private final List<T> cars = new ArrayList<>();

    public Garage(int size) {
        this.maxCapacity = size;
    }

    public void addCar(T car) {
        if (cars.size() >= maxCapacity) {
            throw new IllegalArgumentException("Garage is full");
        }
        if (cars.contains(car)) {
            throw new IllegalArgumentException("Car already in garage");
        }
        cars.add(car);

    }

    public T getCar(int index) {
        if (cars.isEmpty()) {
            throw new IllegalStateException("Garage is empty");
        }
        if (index < 0 || index >= cars.size()) {
            throw new IllegalArgumentException("Invalid index");
        }
        return cars.remove(index);
    }

    public T getLastCar() {
        return getCar(cars.size() - 1);
    }
}