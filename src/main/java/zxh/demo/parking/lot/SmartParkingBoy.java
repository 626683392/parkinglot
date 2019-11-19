package zxh.demo.parking.lot;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * SmartParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/19
*/
public class SmartParkingBoy implements ParkingAble {
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    @Override
    public Ticket park() {
        return parkingLots
                .stream()
                .max(Comparator.comparingInt(ParkingLot::emptySlotLeft))
                .map(ParkingLot::park)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Ticket pick(Ticket ticket) {
        Ticket t = null;
        for (ParkingLot p : parkingLots) {
            try {
                t = p.pick(ticket);
            } catch (InvalidTicketException e) {
                // do nothing
            }
        }

        if (Objects.isNull(t)) {
            throw new InvalidTicketException();
        }

        return t;
    }
}
