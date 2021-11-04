package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.Rout;

import java.util.List;

public interface RoutDao {
    Rout showRout(int id);
    List<Rout> showRouts();
    void updateRout(Rout rout);
}
