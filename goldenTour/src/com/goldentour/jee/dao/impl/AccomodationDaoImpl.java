package com.goldentour.jee.dao.impl;

import com.goldentour.jee.dao.AccomodationDao;
import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.entities.Destination;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository(value = "accomodationDao")
public class AccomodationDaoImpl extends GenericDaoImpl<Accomodation> implements AccomodationDao {

    @PersistenceContext
    protected EntityManager em;

    public List<Accomodation> FindAccomodationsForDestinationId(Long id) {
        List<Accomodation> accomodations;
        try {
            Query q = em.createQuery("SELECT a from Accomodation a WHERE a.destination = :destination_id", Accomodation.class);
            q.setParameter("destination_id", em.getReference(Destination.class, id));
            accomodations = q.getResultList();
            if (accomodations.size() != 0) return accomodations;
            else
                return null;
        } finally {
            em.close();
        }
    }

}
