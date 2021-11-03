package by.martysiuk.springBootApp.dao;

import by.martysiuk.springBootApp.models.Rout;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoutDao {
    Rout showRout(int id);

    List<Rout> loadRouts();
}
