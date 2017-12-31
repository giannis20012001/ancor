package org.lumi.ancor.app.dao;

import org.lumi.ancor.app.model.VesselPositionBadRows;

import java.util.Date;
import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 29/12/2017.
 */

public interface IVesselPositionBadRowsDAO {
    List<VesselPositionBadRows> getAllVesselPositionBadRows();
    VesselPositionBadRows getVesselPositionBadRowsById(Long id);
    void addVesselPositionBadRows(VesselPositionBadRows vesselPositionBadRows);
    void updateVesselPositionBadRows(VesselPositionBadRows vesselPositionBadRows);
    void deleteVesselPositionBadRows(Long id);
    boolean vesselPositionBadRowsExists(Integer course, Integer wind, Integer temperature, String windDirection);

}
