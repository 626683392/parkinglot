package zxh.demo.parking.lot;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;

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
        return iterateParkingLotsToApply((p, t) -> p.park(), null, ParkingLotFullException.class);
    }

    public Ticket pick(Ticket ticket) {
        return iterateParkingLotsToApply(ParkingLot::pick, ticket, InvalidTicketException.class);
    }

    private Ticket iterateParkingLotsToApply(BiFunction<ParkingLot, Ticket, Ticket> function, Ticket inputTicket, Class<? extends RuntimeException> exceptionType) {

        Ticket result = null;
        for (ParkingLot p : parkingLots) {
            try {
                result = function.apply(p, inputTicket);
                break;
            } catch (Exception e) {
                // do nothing
            }
        }

        if (Objects.isNull(result)) {
            RuntimeException exception;
            try {
                exception = exceptionType.getDeclaredConstructor().newInstance();
            } catch (InstantiationException
                    | IllegalAccessException
                    | InvocationTargetException
                    | NoSuchMethodException e) {
                throw new IllegalArgumentException("Wrong Exception.");
            }

            throw exception;
        }

        return result;
    }
}
