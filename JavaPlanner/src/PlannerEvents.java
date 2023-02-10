import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class PlannerEvents implements Serializable {
    private LocalDate date;
    private LocalTime time;

    String description;
    PlannerEvents(LocalDate date, LocalTime time, String description) {
        this.date = date;
        this.time = time;
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "----" + date + "----\n" + description + " " + time;
    }
}
