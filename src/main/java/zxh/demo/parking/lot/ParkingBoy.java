package zxh.demo.parking.lot;

import java.util.List;
import java.util.Objects;

/**
 * ParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/20
*/
public abstract class ParkingBoy implements ParkingAble {

    protected List<ParkingLot> parkingLots;

    ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
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

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch(ParkingLot::isFull);
    }
}
