package org.lumi.ancor.app.dao;

import org.lumi.ancor.app.model.Vessel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

@Repository
@Transactional
public class VesselDAO implements IVesselDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<Vessel> getAllVessels() {
        String query = "FROM Vessel as vssl ORDER BY vssl.id";
        return (List<Vessel>) entityManager.createQuery(query).getResultList();

    }

    @Override
    public Vessel getVesselById(Long id) {
        return entityManager.find(Vessel.class, id);

    }

    @Override
    public void addVessel(Vessel vessel) {
        entityManager.persist(vessel);

    }

    @Override
    public void updateVessel(Vessel vessel) {
        Vessel vssl = getVesselById(vessel.getId());
        vssl.setImo(vessel.getImo());
        vssl.setVesselName(vessel.getVesselName());
        vssl.setGrossTonnage(vessel.getGrossTonnage());
        entityManager.flush();

    }

    @Override
    public void deleteVessel(Long id) {
        entityManager.remove(getVesselById(id));

    }

    @Override
    public boolean vesselExists(String imo, String vesselName, int grossTonnage) {
        String query = "FROM Vessel as vssl WHERE vssl.imo = ? and vssl.vesselName = ? and vssl.grossTonnage = ?";
        int count = entityManager.createQuery(query)
                .setParameter(1, imo)
                .setParameter(2, vesselName)
                .setParameter(3, grossTonnage)
                .getResultList().size();

        return count > 0;

    }

    @PersistenceContext
    private EntityManager entityManager;

    //Logger
    private static final Logger LOGGER = Logger.getLogger(VesselDAO.class.getName());
}
