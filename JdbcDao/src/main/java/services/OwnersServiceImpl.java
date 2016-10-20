package services;

import dao.OwnersDao;
import models.Owners;

import java.util.List;

import static utils.Verifier.verifyOwnerExist;

public class OwnersServiceImpl implements OwnersService {

    private OwnersDao ownersDao;

    public OwnersServiceImpl(OwnersDao ownersDao) {
        this.ownersDao = ownersDao;
    }

    public Owners findCarById(int id) {
        return ownersDao.find(id);
    }

    public void updateCar(Owners owners) {
        verifyOwnerExist(owners.getOwnerId());
        this.ownersDao.update(owners);
    }

    public List<Owners> getAll() {
        return this.ownersDao.getAll();
    }

    public void deleteCar(int id) {
        verifyOwnerExist(id);
        this.ownersDao.delete(id);
    }

    public void addCar(Owners owners) {
        this.ownersDao.add(owners);
    }
}
