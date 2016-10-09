package ru.itis;

        import ru.itis.dao.UsersDao;
        import ru.itis.dao.UsersDaoFileBasedImpl;
        import ru.itis.models.UsersSupportFactory;
        import ru.itis.service.SimpleUsersService;
        import ru.itis.service.SimpleUsersServiceImpl;

public class Main {

    public static void main(String[] args) {
        UsersDao usersDao = UsersSupportFactory.getInstance().getUsersDao();

        SimpleUsersService service = new SimpleUsersServiceImpl(usersDao);

        System.out.println(service.isRegistered("Marsel", "qwerty007"));
    }
}
