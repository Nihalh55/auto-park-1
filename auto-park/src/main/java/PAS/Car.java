package PAS;

import java.time.LocalDateTime;

/**
 * The Car class holds all information that the
 * Slot class would need with respect to Cars.
 */

public class Car{

    String car_number;
    // Image?
    private LocalDateTime entry, exit;

    public void enteredNow()
    {
        entry = LocalDateTime.now();
    }

    public void leftNow()
    {
        exit = LocalDateTime.now();
    }

    public LocalDateTime[] getTimes()
    {
        LocalDateTime[] ret = new LocalDateTime[]{entry, exit};
        return ret;
    }
}