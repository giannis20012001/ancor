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
    void addVesselPosition(VesselPosition vesselPosition);
    void updateVesselPosition(VesselPosition vesselPosition);
    void deleteVesselPosition(Long id);
    boolean vesselPositionExists(double latitude, double longitude, Date positionReceivedTimestamp, double speed,
                                 double draught, int yearBuilt, int directionDegrees, String destinationPort,
                                 String destinationCountry);

}
