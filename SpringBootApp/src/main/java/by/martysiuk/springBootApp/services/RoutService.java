package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.models.Rout;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public interface RoutService {
    Rout showRout(int id);

    List<Rout> showRouts();

    void updateRout(Rout rout);

    String[] showDates (GregorianCalendar calendar,
                        SimpleDateFormat simpleDateFormat);
}
