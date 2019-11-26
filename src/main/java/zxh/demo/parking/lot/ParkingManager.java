package zxh.demo.parking.lot;

import java.util.List;
import java.util.Objects;

/**
 * ParkingManaer:
 * @author zhangxuhai
 * @date 2019/11/26
*/
public class ParkingManager implements ParkingAble {
    private List<ParkingAble> parkingAbles;

    public ParkingManager(List<ParkingAble> parkingAbles) {
        this.parkingAbles = parkingAbles;
    }

    @Override
    public Ticket park() {
        return parkingAbles
                .stream()
                .filter(p -> !p.isFull())
                .findFirst()
                .orElseThrow(ParkingLotFullException::new)
                .park();
    }

    @Override
    public Ticket pick(Ticket ticket) {
        Ticket t = null;
        for (ParkingAble p : parkingAbles) {
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
        return false;
    }
}
