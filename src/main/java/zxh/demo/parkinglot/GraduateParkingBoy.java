package zxh.demo.parkinglot;

import java.util.List;

/**
 * GraduateParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/30
*/
public class GraduateParkingBoy {
    private List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        return parkingLots
                .stream()
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new)
                .park(car);
    }

    public Car pick(Ticket t) {
        return parkingLots
                .stream()
                .filter(p -> p.contains(t))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pick(t);
    }
}
