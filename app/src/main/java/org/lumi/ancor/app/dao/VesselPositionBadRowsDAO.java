package org.lumi.ancor.app.dao;

import org.lumi.ancor.app.model.VesselPositionBadRows;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 29/12/2017.
 */

@Repository
@Transactional
public class VesselPositionBadRowsDAO implements IVesselPositionBadRowsDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<VesselPositionBadRows> getAllVesselPositionBadRows() {
        String query = "FROM VesselPositionBadRows as vsslpstnbdrws ORDER BY vsslpstnbdrws.id";
        return (List<VesselPositionBadRows>) entityManager.createQuery(query).getResultList();

    }

    @Override
    public VesselPositionBadRows getVesselPositionBadRowsById(Long id) {
        return entityManager.find(VesselPositionBadRows.class, id);

    }

    @Override
    public void addVesselPositionBadRows(VesselPositionBadRows vesselPositionBadRows) {
        entityManager.persist(vesselPositionBadRows);

    }

    @Override
    public void updateVesselPositionBadRows(VesselPositionBadRows vesselPositionBadRows) {
        VesselPositionBadRows vsslpstnbdrws = getVesselPositionBadRowsById(vesselPositionBadRows.getId());
        vsslpstnbdrws.setCourse(vesselPositionBadRows.getCourse());
        vsslpstnbdrws.setWind(vesselPositionBadRows.getWind());
        vsslpstnbdrws.setTemperature(vesselPositionBadRows.getTemperature());
        vsslpstnbdrws.setWindDirection(vesselPositionBadRows.getWindDirection());
        entityManager.flush();

    }

    @Override
    public void deleteVesselPositionBadRows(Long id) {
        entityManager.remove(getVesselPositionBadRowsById(id));

    }

    @Override
    public boolean vesselPositionBadRowsExists(int course, int wind, int temperature, String windDirection) {
        String query = "FROM VesselPositionBadRows as vsslpstnbdrws WHERE vsslpstnbdrws.course = ? " +
                "and vsslpstnbdrws.wind = ? and vsslpstnbdrws.temperature = ? and vsslpstnbdrws.windDirection = ?";
        int count = entityManager.createQuery(query)
                .setParameter(1, course)
                .setParameter(2, wind)
                .setParameter(3, temperature)
                .setParameter(4, windDirection)
                .getResultList().size();

        return count > 0;

    }

    @PersistenceContext
    private EntityManager entityManager;

    //Logger
    private static final Logger LOGGER = Logger.getLogger(VesselPositionBadRowsDAO.class.getName());

}
