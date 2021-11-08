package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.dao.RoutDao;
import by.martysiuk.springBootApp.models.Rout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoutServiceImpl implements RoutService {

    private final RoutDao routDao;

    @Autowired
    public RoutServiceImpl(RoutDao routDao) {
        this.routDao = routDao;
    }

    @Transactional
    @Override
    public Rout showRout(int id) {
        return routDao.showRout(id);
    }

    @Transactional
    @Override
    public List<Rout> showRouts() {
        return routDao.showRouts();
    }

    @Transactional
    @Override
    public void updateRout(Rout rout) {
        routDao.updateRout(rout);
    }
}
