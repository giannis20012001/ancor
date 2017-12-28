package org.lumi.ancor.app.service;

import org.lumi.ancor.app.model.Port;

import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

public interface IPortService {
    List<Port> getAllPorts();
    Port getPortById(Long id);
    boolean addPort(Port port);
    void updatePort(Port port);
    void deletePort(Long id);

}
