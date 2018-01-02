package org.lumi.ancor.app.dao;

import org.lumi.ancor.app.model.VesselPosition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

@Repository
@Transactional
public class VesselPositionDAO implements IVesselPositionDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<VesselPosition> getAllVesselPositions() {
        String query = "FROM VesselPosition as vsslpstn ORDER BY vsslpstn.id";

        return (List<VesselPosition>) entityManager.createQuery(query).getResultList();

    }

    @Override
    public VesselPosition getVesselPositionById(Long id) {
        return entityManager.find(VesselPosition.class, id);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<VesselPosition> getVesselPositionByDate(Date fromDate, Date toDate) {
        //SELECT * FROM ancor.vessel_position WHERE position_received_timestamp BETWEEN '2017-04-16' AND '2017-04-20'
        // + INTERVAL 1 DAY ORDER BY position_received_timestamp;
        //INTERVAL is not supported in JPQL
        String query = "SELECT vsslpstn FROM VesselPosition vsslpstn WHERE vsslpstn.positionReceivedTimestamp " +
                "BETWEEN :fromDate AND :toDate ORDER BY vsslpstn.positionReceivedTimestamp";

        return (List<VesselPosition>) entityManager.createQuery(query)
                .setParameter("fromDate", fromDate, TemporalType.DATE)
                .setParameter("toDate", toDate, TemporalType.DATE)
                .getResultList();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<VesselPosition> getVesselPositionByDate(Date fromDate) {
        String query = "SELECT vsslpstn FROM VesselPosition vsslpstn " +
                "WHERE vsslpstn.positionReceivedTimestamp >= :fromDate ORDER BY vsslpstn.positionReceivedTimestamp";

        return (List<VesselPosition>) entityManager.createQuery(query)
                .setParameter("fromDate", fromDate, TemporalType.DATE)
                .getResultList();

    }

    @Override
    public void addVesselPosition(VesselPosition vesselPosition) {
        entityManager.persist(vesselPosition);

    }

    @Override
    public void updateVesselPosition(VesselPosition vesselPosition) {
        VesselPosition vsslpstn = getVesselPositionById(vesselPosition.getId());
        vsslpstn.setLatitude(vesselPosition.getLatitude());
        vsslpstn.setLongitude(vesselPosition.getLongitude());
        vsslpstn.setPositionReceivedTimestamp(vesselPosition.getPositionReceivedTimestamp());
        vsslpstn.setSpeed(vesselPosition.getSpeed());
        vsslpstn.setDraught(vesselPosition.getDraught());
        vsslpstn.setYearBuilt(vesselPosition.getYearBuilt());
        vsslpstn.setDirectionDegrees(vesselPosition.getDirectionDegrees());
        vsslpstn.setDestinationPort(vesselPosition.getDestinationPort());
        vsslpstn.setDestinationCountry(vesselPosition.getDestinationCountry());
        entityManager.flush();

    }

    @Override
    public void deleteVesselPosition(Long id) {
        entityManager.remove(getVesselPositionById(id));

    }

    @Override
    public boolean vesselPositionExists(Double latitude, Double longitude, Date positionReceivedTimestamp, Double speed,
                                        Double draught, Integer yearBuilt, Integer directionDegrees, String destinationPort,
                                        String destinationCountry) {
        String query = "FROM VesselPosition as vsslpstn WHERE vsslpstn.latitude = ? and vsslpstn.longitude = ? " +
                "and vsslpstn.positionReceivedTimestamp = ? and vsslpstn.speed = ? and vsslpstn.draught = ? " +
                "and vsslpstn.yearBuilt = ? and vsslpstn.directionDegrees = ? and vsslpstn.destinationPort = ? " +
                "and vsslpstn.destinationCountry = ?";
        int count = entityManager.createQuery(query)
                .setParameter(1, latitude)
                .setParameter(2, longitude)
                .setParameter(3, positionReceivedTimestamp)
                .setParameter(4, speed)
                .setParameter(5, draught)
                .setParameter(6, yearBuilt)
                .setParameter(7, directionDegrees)
                .setParameter(8, destinationPort)
                .setParameter(9, destinationCountry)
                .getResultList().size();

        return count > 0;

    }

    @PersistenceContext
    private EntityManager entityManager;

    //Logger
    private static final Logger LOGGER = Logger.getLogger(VesselPositionDAO.class.getName());

}
