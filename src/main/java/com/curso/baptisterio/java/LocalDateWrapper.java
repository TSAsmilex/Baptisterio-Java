package com.curso.baptisterio.java;
import java.time.LocalDate;

public class LocalDateWrapper {
    // Hash personalizado que utiliza día, mes y año exclusivamente.
    private LocalDate date = null;

    public LocalDateWrapper(LocalDate date) {
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        return
              date.getDayOfMonth()
            + date.getMonthValue() * 100
            + date.getYear() * 1000000;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        else if (obj != null) {
            LocalDateWrapper o = (LocalDateWrapper) obj;
            return     getYear() == o.getYear()
                    && getDayOfMonth() == o.getDayOfMonth()
                    && getMonthValue() == o.getMonthValue();
        }

        return false;
    }

    public int getDayOfMonth() {
        return date.getDayOfMonth();
    }

    public int getMonthValue() {
        return date.getMonthValue();
    }

    public int getYear() {
        return date.getYear();
    }

    public boolean isBefore(LocalDate date) {
        return date.isBefore(date);
    }

    public boolean isBefore(LocalDateWrapper date) {
        return date.isBefore(date.getDate());
    }

    @Override
    public String toString() {
        return date.toString();
    }
}
