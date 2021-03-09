package com.fleamarket.service.impl;

import com.fleamarket.dao.IDaDao;
import com.fleamarket.dao.IUserDao;
import com.fleamarket.dao.impl.DaDaoImpl;
import com.fleamarket.dao.impl.UserDaoImpl;
import com.fleamarket.domain.DaTableEntity;
import com.fleamarket.service.IDaService;

import java.util.List;

public class DaServiceImpl implements IDaService {
    @Override
    public List<DaTableEntity> alladdr(String username) {
        IDaDao dDao = new DaDaoImpl();
        List<DaTableEntity> listgoods=dDao.alladdr(username);
        return listgoods;
    }

    @Override
    public int insaddr(DaTableEntity da) {
        if(da == null)
            return -1;
        IDaDao dDao = new DaDaoImpl();
        int msg = dDao.insaddr(da);
        return msg;
    }

    @Override
    public int updateaddr(DaTableEntity da) {
        if(da == null)
            return -1;
        IDaDao dDao = new DaDaoImpl();
        int msg = dDao.updateaddr(da);
        return msg;
    }

    @Override
    public int deladdr(String daid) {
        if(daid == null)
            return -1;
        IDaDao dDao = new DaDaoImpl();
        int msg = dDao.deladdr(daid);
        return msg;
    }
}
