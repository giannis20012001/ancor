package org.lumi.ancor.app.dao;

import org.lumi.ancor.app.model.Port;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by John Tsantilis
 * (i [dot] tsantilis [at] yahoo [dot] com A.K.A lumi) on 28/12/2017.
 */

@Repository
@Transactional
public class PortDAO implements IPortDAO {
    @Override
    @SuppressWarnings("unchecked")
    public List<Port> getAllPorts() {
        String query = "FROM Port as prt ORDER BY prt.id";
        return (List<Port>) entityManager.createQuery(query).getResultList();

    }

    @Override
    public Port getPortById(Long id) {
        return entityManager.find(Port.class, id);

    }

    @Override
    public void addPort(Port port) {
        entityManager.persist(port);

    }

    @Override
    public void updatePort(Port port) {
        Port prt = getPortById(port.getId());
        prt.setFromPort(port.getFromPort());
        prt.setFromCountry(port.getFromCountry());
        entityManager.flush();

    }

    @Override
    public void deletePort(Long id) {
        entityManager.remove(getPortById(id));

    }

    @Override
    public boolean portExists(String fromPort, String fromCountry) {
        String query = "FROM Port as prt WHERE prt.fromPort = ? and prt.fromCountry = ?";
        int count = entityManager.createQuery(query)
                .setParameter(1, fromPort)
                .setParameter(2, fromCountry)
                .getResultList().size();

        return count > 0 ? true : false;

    }

    @PersistenceContext
    private EntityManager entityManager;

}
