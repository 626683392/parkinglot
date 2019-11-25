package zxh.demo.parking.lot;

/**
 * ParkingAble:
 * @author zhangxuhai
 * @date 2019/11/20
*/
public interface ParkingAble {
    Ticket park();

    Ticket pick(Ticket ticket);

    boolean isFull();
}
