package ru.itis;

import org.junit.Before;
import org.junit.Test;
import ru.itis.dao.UsersDaoFileBasedImpl;
import ru.itis.models.User;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by admin on 06.10.2016.
 */
public class UsersDaoFileBasedImplTest {
    @Test
    public void get() throws Exception {

        Boolean expected = "Marsel".equals(usersDao.get(1234).getName());

        assertTrue(expected);
    }

    @Test
    public void save() throws Exception {
        usersDao.save(new User("Alexey", "qwertystaff", 21, 5678));

        Boolean result = "Alexey".equals(usersDao.get(5678).getName());

        assertTrue(result);
    }

    @Test
    public void delete() throws Exception {
        usersDao.delete(1234);
        //Boolean expected = "Marsel".equals(usersDao.get(5678).getName());
        //assertFalse(expected);

    }

    private UsersDaoFileBasedImpl usersDao;

    @Before
    public void setUp() throws Exception {
        usersDao = new UsersDaoFileBasedImpl();
    }

    @Test
    public void getAll() throws Exception {
        List<User> registeredUsers = usersDao.getAll();
        int i = 0;
    }

}