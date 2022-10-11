import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class Baptisterio {
    static final int PRICE = 400;  // céntimos
    private HashMap<LocalDate, User> reservations = new HashMap<LocalDate, User>();
    private Payment payments = new Payment();


    public boolean avaliable (LocalDate date) {
        if (reservations.containsKey(date)) {
            return false;
        }

        return true;
    }


    public void cancel (User user,LocalDate date){
        if (reservations.get(date).equals(user)) {
            reservations.remove(date);
        }
    }


    public boolean pay (User user, LocalDate date, ArrayList<CoinEuro> coins) {
        // Comprobar que la fecha está disponible
        if (!avaliable(date)) {
            return false;
        }

        // ¿Se ha pagado abonado la cantidad correcta?
        int total = 0;
        for (CoinEuro coin: coins) {
            total += coin.getValue();
        }

        if (total != PRICE) {
            return false;
        }

        payments.register(user, date, coins);
        reservations.put(date, user);
        return true;
    }
}