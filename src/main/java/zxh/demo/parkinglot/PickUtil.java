package zxh.demo.parkinglot;

import java.util.List;

/**
 * PickUtil:
 * @author zhangxuhai
 * @date 2019/12/10
*/
public class PickUtil {
    private PickUtil() {
        // empty
    }

    public static Car pickFrom(List<? extends ParkingAble> parkingAbles, Ticket ticket) {
        return parkingAbles
                .stream()
                .filter(parkingAble -> parkingAble.contains(ticket))
                .findFirst()
                .orElseThrow(InvalidTicketException::new)
                .pick(ticket);
    }
}
