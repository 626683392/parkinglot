package zxh.demo.parking.lot;

import java.util.ArrayList;

/**
 * ParkingLot:
 * @author zhangxuhai
 * @date 2019/11/18
*/
public class ParkingLot implements ParkingAble {
    private ArrayList<Ticket> carContainer;
    private int size = 0;

    public ParkingLot(int size) {
        this.size = size;
        carContainer = new ArrayList<>();
    }

    @Override
    public Ticket park() {
        if (size == carContainer.size()) {
            throw new ParkingLotFullException();
        }

        Ticket ticket = Ticket.create();
        carContainer.add(ticket);
        return ticket;
    }

    @Override
    public Ticket pick(Ticket ticket) {
        Ticket result = carContainer
                .stream()
                .filter(t -> t.equals(ticket))
                .findAny()
                .orElseThrow(InvalidTicketException::new);
        carContainer.remove(result);
        return result;
    }

    public int emptySlotLeft() {
        return size - carContainer.size();
    }

    public boolean isFull() {
        return emptySlotLeft() == 0;
    }

    public int getSize() {
        return size;
    }
}
