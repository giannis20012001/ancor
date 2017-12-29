package org.lumi.ancor.app.service;

import org.lumi.ancor.app.dao.IPortDAO;
import org.lumi.ancor.app.model.Port;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

@Service
public class PortService implements IPortService {
    @Override
    public List<Port> getAllPorts() {
        return portDAO.getAllPorts();

    }

    @Override
    public Port getPortById(Long id) {
        Port obj = portDAO.getPortById(id);

        return obj;

    }

    @Override
    public boolean addPort(Port port) {
        if (portDAO.portExists(port.getFromPort(), port.getFromCountry())) {
            return false;

        } else {
            portDAO.addPort(port);

            return true;

        }

    }

    @Override
    public void updatePort(Port port) {
        portDAO.updatePort(port);

    }

    @Override
    public void deletePort(Long id) {
        portDAO.deletePort(id);

    }

    @Autowired
    private IPortDAO portDAO;

    //Logger
    private static final Logger LOGGER = Logger.getLogger(PortService.class.getName());

}
