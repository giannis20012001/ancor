package org.lumi.ancor.app.dao;

import org.lumi.ancor.app.model.Port;

import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

public interface IPortDAO {
    List<Port> getAllPorts();
    Port getPortById(Long id);
    void addPort(Port port);
    void updatePort(Port port);
    void deletePort(Long id);
    boolean portExists(String fromPort, String fromCountry);

}
