package org.lumi.ancor.app.service;

import org.lumi.ancor.app.dao.IVesselPositionBadRowsDAO;
import org.lumi.ancor.app.model.VesselPositionBadRows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 29/12/2017.
 */

@Service
public class VesselPositionBadRowsService implements IVesselPositionBadRowsService {
    @Override
    public List<VesselPositionBadRows> getAllVesselPositionBadRows() {
        return vesselPositionBadRowsDAO.getAllVesselPositionBadRows();

    }

    @Override
    public VesselPositionBadRows getVesselPositionBadRowsById(Long id) {
        VesselPositionBadRows obj = vesselPositionBadRowsDAO.getVesselPositionBadRowsById(id);

        return obj;

    }

    @Override
    public boolean addVesselPositionBadRows(VesselPositionBadRows vesselPositionBadRows) {
        if (vesselPositionBadRowsDAO.vesselPositionBadRowsExists(vesselPositionBadRows.getCourse(),
                vesselPositionBadRows.getWind(), vesselPositionBadRows.getTemperature(),
                vesselPositionBadRows.getWindDirection())) {
            return false;

        } else {
            vesselPositionBadRowsDAO.addVesselPositionBadRows(vesselPositionBadRows);

            return true;

        }

    }

    @Override
    public void updateVesselPositionBadRows(VesselPositionBadRows vesselPositionBadRows) {
        vesselPositionBadRowsDAO.updateVesselPositionBadRows(vesselPositionBadRows);

    }

    @Override
    public void deleteVesselPositionBadRows(Long id) {
        vesselPositionBadRowsDAO.deleteVesselPositionBadRows(id);

    }

    @Autowired
    private IVesselPositionBadRowsDAO vesselPositionBadRowsDAO;

    //Logger
    private static final Logger LOGGER = Logger.getLogger(VesselPositionBadRowsService.class.getName());

}
