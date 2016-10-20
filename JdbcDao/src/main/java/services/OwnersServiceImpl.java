package services;

import dao.OwnersDao;
import models.Owners;

import static utils.Verifier.verifyOwnerExist;

/**
 * Created by KFU-user on 13.10.2016.
 */
public class OwnersServiceImpl implements OwnersService {

    private OwnersDao ownersDao;

    public OwnersServiceImpl(OwnersDao ownersDao) {
        this.ownersDao = ownersDao;
    }

    public void updateCar(Owners owners) {
        verifyOwnerExist(owners.getOwnerId());
        this.ownersDao.update(owners);
    }
}
