import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


enum MenuOptions {
    NOOP(0),
    BOOK(1),
    CANCEL(2),
    LIST(3),
    EXIT(4);

    private final int value;

    private MenuOptions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static MenuOptions fromValue(int value) {
        for (MenuOptions option : MenuOptions.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }

        throw new InputMismatchException("Invalid option");
    }

    public static void printMenu() {
        System.out.println("   1) Reservar entradas");
        System.out.println("   2) Anular una reserva");
        System.out.println("   3) Listar una reserva");
        System.out.println("   4) Salir");
    }
}


public class App {
    static Baptisterio baptisterio = new Baptisterio();

    public static void main(String[] args) throws Exception {
        System.out.print("¿¿¿A quién no le va a gustar un buen baptisterio???");

        var       option = MenuOptions.NOOP;
        Scanner   scan   = new Scanner(System.in);
        Exception error  = null;

        do {
            if (option != MenuOptions.LIST) {
                clearScreen();
            }

            if (error != null) {
                System.err.println("Se ha producido un error. Motivo:\t" + error.getMessage());
            }

            error  = null;
            option = MenuOptions.NOOP;

            System.out.println("\n¡Hola! ¿Qué quieres hacer?");
            MenuOptions.printMenu();

            error = null;

            try {
                System.out.print("> ");
                option = MenuOptions.fromValue(scan.nextInt());
            }
            catch (InputMismatchException e) {
                error = new InputMismatchException("Introduce un comando válido.");
                scan.nextLine();
            }

            switch (option) {
                case BOOK -> {
                    try {
                        book(scan);
                    }
                    catch (DateTimeException e) {
                        error = e;
                    }
                    catch (Exception e) {
                        error = e;
                    }
                }
                case CANCEL -> cancel(scan);
                case EXIT   -> System.out.println("¡Hasta luego!");
                case LIST   -> baptisterio.printReservations();
                case NOOP   -> {}
            }
        } while (option != MenuOptions.EXIT);

        scan.close();
    }
    private static LocalDate readDate(Scanner scan) throws DateTimeException{
        System.out.println("¿Qué día quieres comprobar? (YYYY-MM-DD)");
        System.out.print("> ");

        scan.nextLine();
        String dateString = scan.nextLine();
        LocalDate date;

        try {
            date = LocalDate.parse(dateString);
        }
        catch (DateTimeParseException e) {
            throw new DateTimeException("La fecha introducida es inválida");
        }
        return date;
    }
    private static User readUser(Scanner scan){
        System.out.println("¿Cuál es tu nombre?");
        System.out.print("> ");
        String name = scan.nextLine();

        System.out.println("¿Cuál es tu DNI?");
        System.out.print("> ");
        String dni = scan.nextLine();

        User user = new User(name, dni);
        return user;
    }
    private static void book (Scanner scan) throws Exception, DateTimeException {
        LocalDate date = null;

        try {
            date = readDate(scan);
        }
        catch (DateTimeException e) {
            throw e;
        }

        if (date.isBefore(LocalDate.now())) {
            throw new DateTimeException("No puedes reservar entradas para días pasados");
        }
        if (!baptisterio.avaliable(date)) {
            throw new Exception("La fecha introducida no está disponible");
        }

        User user=readUser(scan);

        int round     = 0;
        int remaining = baptisterio.PRICE;
        ArrayList<CoinEuro> coins = new ArrayList<CoinEuro>();


        do {
            clearScreen();
            System.out.println("Debes abonar " + baptisterio.PRICE + " céntimos.");
            System.out.println("Introduce las monedas en el sistema. Puedes separarlas por comas o introducirlas poco a poco");
            System.out.print(remaining + " céntimos restantes. \n> ");

            String coinsString = scan.nextLine();
            var coinsRound = CoinEuro.fromString(coinsString);
            coins.addAll(coinsRound);

            round      = CoinEuro.sum(coinsRound);
            remaining -= round;

        } while (round <= remaining);

        if (remaining < 0) {
            System.out.println("\nTe has pasado " + Math.abs(remaining) + " céntimos.\n(Te imaginas que te he dado el cambio porque esto es un programa cutre).");
            coins.remove(CoinEuro.fromValue(Math.abs(remaining)));
        }

        baptisterio.pay(user, date, coins);
        System.out.println("Su pago se ha procesado correctamente");

        Thread.sleep(2000);
    }

    private static void cancel (Scanner scan) throws InterruptedException {
        LocalDate date = null;

        try {
            date = readDate(scan);
        }
        catch (DateTimeException e) {
            throw e;
        }

        User user = readUser(scan);

        if (baptisterio.cancel(user, date)) {
            System.out.println("Su cita se ha cancelado con éxito!");
        }
        else {
            System.out.println("No se ha podido cancelar su reserva.");
        }

        Thread.sleep(2000);
    }


    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
