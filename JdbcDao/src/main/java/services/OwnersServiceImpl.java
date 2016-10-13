package services;

import dao.OwnersDao;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class OwnersServiceImpl implements OwnersService {

    private OwnersDao ownersDao;

    public OwnersServiceImpl(OwnersDao ownersDao) {
        this.ownersDao = ownersDao;
    }
}
