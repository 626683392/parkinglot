package zxh.demo.parking.lot;

import java.util.List;
import java.util.Objects;

/**
 * ParkingAble:
 * @author zhangxuhai
 * @date 2019/11/20
*/
public interface ParkingAble {
    Ticket park();

    Ticket pick(Ticket ticket);

    boolean isFull();

    default Ticket pickByParkingAble(List<? extends ParkingAble> parkingAbles, Ticket ticket) {
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
}
