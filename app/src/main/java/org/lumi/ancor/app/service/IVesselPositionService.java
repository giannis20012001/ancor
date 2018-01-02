package org.lumi.ancor.app.service;

import org.lumi.ancor.app.model.VesselPosition;

import java.util.Date;
import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

public interface IVesselPositionService {
    List<VesselPosition> getAllVesselPositions();
    VesselPosition getVesselPositionById(Long id);
    List<VesselPosition> getVesselPositionByDate(Date fromDate, Date toDate);
    List<VesselPosition> getVesselPositionByDate(Date fromDate);
    boolean addVesselPosition(VesselPosition vesselPosition);
    void updateVesselPosition(VesselPosition vesselPosition);
    void deleteVesselPosition(Long id);

}
