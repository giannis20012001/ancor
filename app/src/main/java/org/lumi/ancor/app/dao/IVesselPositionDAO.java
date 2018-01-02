package org.lumi.ancor.app.dao;

import org.lumi.ancor.app.model.VesselPosition;

import java.util.Date;
import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

public interface IVesselPositionDAO {
    List<VesselPosition> getAllVesselPositions();
    VesselPosition getVesselPositionById(Long id);
    List<VesselPosition> getVesselPositionByDate(Date fromDate, Date toDate);
    List<VesselPosition> getVesselPositionByDate(Date fromDate);
    void addVesselPosition(VesselPosition vesselPosition);
    void updateVesselPosition(VesselPosition vesselPosition);
    void deleteVesselPosition(Long id);
    boolean vesselPositionExists(Double latitude, Double longitude, Date positionReceivedTimestamp, Double speed,
                                 Double draught, Integer yearBuilt, Integer directionDegrees, String destinationPort,
                                 String destinationCountry);

}
