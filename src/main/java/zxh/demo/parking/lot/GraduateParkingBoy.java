package zxh.demo.parking.lot;

import java.util.List;
import java.util.Objects;

/**
 * GraduateParkingBoy:
 * @author zhangxuhai
 * @date 2019/11/18
*/
public class GraduateParkingBoy {
    private List<ParkingLot> parkingLots;

    public GraduateParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park() {
        Ticket result = null;
        for (ParkingLot p : parkingLots) {
            try {
                result = p.park();
                break;
            } catch (ParkingLotFullException e) {
                // do nothing
            }
        }

        if (Objects.isNull(result)) {
            throw new ParkingLotFullException();
        }

        return result;
    }

    public Ticket pick(Ticket ticket) {
        Ticket result = null;
        for (ParkingLot p : parkingLots) {
            try {
                result = p.pick(ticket);
                break;
            } catch (InvalidTicketException e) {
                // do nothing
            }
        }

        if (Objects.isNull(result)) {
            throw new InvalidTicketException();
        }

        return result;
    }
}
