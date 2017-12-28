package org.lumi.ancor.app.service;

import org.lumi.ancor.app.model.Vessel;

import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

public interface IVesselService {
    List<Vessel> getAllVessels();
    Vessel getVesselById(Long id);
    boolean addVessel(Vessel vessel);
    void updateVessel(Vessel vessel);
    void deleteVessel(Long id);

}
