package zxh.demo.parkinglot;

import java.util.List;

/**
 * ParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    abstract public Ticket park(Car car);

    public Car pick(Ticket t) {
        return parkingLots
                .stream()
                .filter(p -> p.contains(t))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pick(t);
    }
}
