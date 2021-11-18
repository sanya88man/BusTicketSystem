package by.martysiuk.springBootApp.services;

import by.martysiuk.springBootApp.dao.RoutDao;
import by.martysiuk.springBootApp.models.Rout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

    @Override
    public String[] showDates(GregorianCalendar calendar,
                              SimpleDateFormat simpleDateFormat) {
        String[] arr = new String[3];
        simpleDateFormat.applyPattern("dd-MM-yyyy");

        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        arr[0] = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        arr[1] = simpleDateFormat.format(calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        arr[2] = simpleDateFormat.format(calendar.getTime());
        return arr;
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
