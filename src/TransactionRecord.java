import java.time.LocalDate;

public class TransactionRecord {
    private User user;
    private LocalDate date;
    private int amount;

    public TransactionRecord(User user, LocalDate date, int amount) {
        this.user   = user;
        this.date   = date;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }
}
