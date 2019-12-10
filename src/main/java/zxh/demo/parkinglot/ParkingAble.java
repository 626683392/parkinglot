package zxh.demo.parkinglot;

/**
 * ParkingAble:
 * @author zhangxuhai
 * @date 2019/12/10
*/
public interface ParkingAble {

    Ticket park(Car car);

    Car pick(Ticket ticket);

    boolean isFull();

    boolean contains(Ticket ticket);
}
