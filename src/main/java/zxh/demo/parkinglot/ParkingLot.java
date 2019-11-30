package zxh.demo.parkinglot;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * ParkingLot:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public class ParkingLot {
    private int capacity;
    private int index = 0;
    private Map<Ticket, Car> slots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        slots = new HashMap<>();
    }

    public Ticket park(Car car) {
        if (slots.size() == capacity) {
            throw new ParkingLotFullException();
        }

        Ticket t = new Ticket();
        slots.put(t, car);
        return t;
    }

    public Car pick(Ticket t) {
        Car car = Optional.ofNullable(slots.get(t)).orElseThrow(InvalidTicketException::new);
        slots.remove(t);
        return car;
    }
}
