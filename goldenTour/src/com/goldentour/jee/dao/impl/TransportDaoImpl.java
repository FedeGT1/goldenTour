package com.goldentour.jee.dao.impl;

import org.springframework.stereotype.Repository;

import com.goldentour.jee.dao.*;
import com.goldentour.jee.entities.*;

@Repository(value = "transportDao")
public class TransportDaoImpl extends GenericDaoImpl<Transport> implements TransportDao  {

}
