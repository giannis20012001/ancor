package org.lumi.ancor.app.dao;

import org.lumi.ancor.app.model.VesselPosition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
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
