package com.curso.baptisterio.java;
import java.util.ArrayList;
import java.util.HashMap;

public class Baptisterio {
    static final int PRICE = 400;  // céntimos
    private HashMap<LocalDateWrapper, User> reservations = new HashMap<>();
    private Payment payments = new Payment();


    public boolean avaliable (LocalDateWrapper date) {
        if (reservations.containsKey(date)) {
            return false;
        }

        return true;
    }


    public boolean cancel (User user, LocalDateWrapper date) {
        if (reservations.get(date).equals(user)) {
            if (reservations.remove(date) != null) {
                return true;
            }
        }

        return false;
    }


    public boolean pay (User user, LocalDateWrapper date, ArrayList<CoinEuro> coins) {
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

    public void printReservations() {
        System.out.println("Reservas: \n");

        for (var date : reservations.keySet()) {
            System.out.println(date + " -> "
                + reservations.get(date).getUserName()
                + " ("+reservations.get(date).getDni() + ")"
            );
        }
    }
}