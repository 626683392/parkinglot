package zxh.demo.parking.lot;

import java.util.UUID;

/**
 * Ticket:
 * @author zhangxuhai
 * @date 2019/11/18
*/
public class Ticket {
    private UUID ticketNO;

    private Ticket() {
        ticketNO = UUID.randomUUID();
    }

    public static Ticket create() {
        return new Ticket();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return ticketNO.equals(ticket.ticketNO);
    }

    @Override
    public int hashCode() {
        return ticketNO.hashCode();
    }
}
