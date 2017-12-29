package org.lumi.ancor.app.service;

import org.lumi.ancor.app.model.VesselPositionBadRows;

import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 29/12/2017.
 */

public interface IVesselPositionBadRowsService {
    List<VesselPositionBadRows> getAllVesselPositionBadRows();
    VesselPositionBadRows getVesselPositionBadRowsById(Long id);
    boolean addVesselPositionBadRows(VesselPositionBadRows vesselPositionBadRows);
    void updateVesselPositionBadRows(VesselPositionBadRows vesselPositionBadRows);
    void deleteVesselPositionBadRows(Long id);

}
