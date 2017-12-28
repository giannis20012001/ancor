package org.lumi.ancor.app.service;

import org.lumi.ancor.app.dao.IVesselDAO;
import org.lumi.ancor.app.model.Vessel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

@Service
public class VesselService implements IVesselService {
    @Override
    public List<Vessel> getAllVessels() {
        return vesselDAO.getAllVessels();

    }

    @Override
    public Vessel getVesselById(Long id) {
        Vessel obj = vesselDAO.getVesselById(id);

        return obj;

    }

    @Override
    public boolean addVessel(Vessel vessel) {
        if (vesselDAO.vesselExists(vessel.getImo(), vessel.getVesselName(), vessel.getGrossTonnage())) {
            return false;

        } else {
            vesselDAO.addVessel(vessel);

            return true;

        }

    }

    @Override
    public void updateVessel(Vessel vessel) {
        vesselDAO.updateVessel(vessel);

    }

    @Override
    public void deleteVessel(Long id) {
        vesselDAO.deleteVessel(id);

    }

    @Autowired
    private IVesselDAO vesselDAO;

}
