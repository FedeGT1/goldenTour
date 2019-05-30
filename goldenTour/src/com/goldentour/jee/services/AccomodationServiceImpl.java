package com.goldentour.jee.services;

import com.goldentour.jee.dao.AccomodationDao;
import com.goldentour.jee.entities.Accomodation;
import com.goldentour.jee.exception.AccomodationException;
import com.goldentour.jee.viewBeans.AccomodationViewBeen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "accomodationService")
@Transactional(propagation = Propagation.REQUIRED)
public class AccomodationServiceImpl implements AccomodationService {

    @Autowired
    @Qualifier("accomodationDao")
    AccomodationDao accomodationDao;

    @Override
    public List<AccomodationViewBeen> findAccomodationByDestination(Long id) throws AccomodationException {
        List<Accomodation> accomodationsList;
        List<AccomodationViewBeen> accomodationsUVBList = new ArrayList<>();

        accomodationsList = accomodationDao.FindAccomodationsForDestinationId(id);

        if (accomodationsList != null) {
            for (Accomodation tmp : accomodationsList) {
                AccomodationViewBeen tmpAVB = new AccomodationViewBeen();
                tmpAVB.setAccomodationType(tmp.getAccomodationType().getName());
                tmpAVB.setAddress(tmp.getAddress());
                tmpAVB.setDestination(tmp.getDestination().getName());
                tmpAVB.setId(tmp.getId());
                tmpAVB.setName(tmp.getName());
                tmpAVB.setPrice(tmp.getPrice());
                tmpAVB.setTelephone(tmp.getTelephone());
                accomodationsUVBList.add(tmpAVB);
            }
            return accomodationsUVBList;
        } else {
            throw new AccomodationException("Accomodation not found");
        }
    }
}