package org.lumi.ancor.app.service;

import org.lumi.ancor.app.dao.IVesselPositionDAO;
import org.lumi.ancor.app.model.VesselPosition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

@Service
public class VesselPositionService implements IVesselPositionService {
    @Override
    public List<VesselPosition> getAllVesselPositions() {
        return vesselPositionDAO.getAllVesselPositions();

    }

    @Override
    public VesselPosition getVesselPositionById(Long id) {
        VesselPosition obj = vesselPositionDAO.getVesselPositionById(id);

        return obj;

    }

    @Override
    public boolean addVesselPosition(VesselPosition vesselPosition) {
        if (vesselPositionDAO.vesselPositionExists(vesselPosition.getLatitude(), vesselPosition.getLongitude(),
                vesselPosition.getPositionReceivedTimestamp(), vesselPosition.getSpeed(), vesselPosition.getDraught(),
                vesselPosition.getYearBuilt(), vesselPosition.getDirectionDegrees(),
                vesselPosition.getDestinationPort(), vesselPosition.getDestinationCountry())) {
            return false;

        } else {
            vesselPositionDAO.addVesselPosition(vesselPosition);

            return true;

        }

    }

    @Override
    public void updateVesselPosition(VesselPosition vesselPosition) {
        vesselPositionDAO.updateVesselPosition(vesselPosition);

    }

    @Override
    public void deleteVesselPosition(Long id) {
        vesselPositionDAO.deleteVesselPosition(id);

    }

    @Autowired
    private IVesselPositionDAO vesselPositionDAO;

    //Logger
    private static final Logger LOGGER = Logger.getLogger(VesselPositionService.class.getName());

}
