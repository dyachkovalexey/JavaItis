package ru.itis.dao;

import com.google.common.base.Splitter;
import ru.itis.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UsersDaoFileBasedImpl implements UsersDao {

    private Properties properties;

    private BufferedReader fileReader;

    private String originFilePath;
    private String supportFilePath;

    public UsersDaoFileBasedImpl() {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("C:\\Users\\Lo0ny\\Desktop\\JavaItis\\SimpleEnterpriseMaven\\src\\main\\resources\\filePaths.properties"));
            this.originFilePath = properties.getProperty("originPath");
            this.supportFilePath = properties.getProperty("supportPath");
            fileReader = new BufferedReader(new FileReader(originFilePath));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try {
            String currentLine = fileReader.readLine();
            while (currentLine != null) {
                User currentUser = parseStringAsUser(currentLine);
                result.add(currentUser);
                currentLine = fileReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("SomeError");
        }
        return result;
    }

    @Override
    public User get(int userId) {
        List<User> registeredUsers = getAll();

        for (User user : registeredUsers) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void save(User user) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(originFilePath,true));
            bufferedWriter.newLine();
            bufferedWriter.write(user.getName() + " " + user.getPassword() + " " + user.getAge() + " " + user.getId());

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int userId) {
        User userDeleted = get(userId);
        String currentLine;
        String lineToDelete = userDeleted.getName() + " " + userDeleted.getPassword() + " " +
                + userDeleted.getAge() + " " + userDeleted.getId();


        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(originFilePath));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(supportFilePath));
            while ((currentLine = bufferedReader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToDelete)) continue;
                bufferedWriter.write(currentLine + System.getProperty("line.separator"));
            }

            File f1 = new File(originFilePath);
            File f2 = new File(supportFilePath);


            f1.delete();
            f2.renameTo(f1);
            f2.createNewFile();

            bufferedWriter.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    private User parseStringAsUser(String line) {
        Splitter splitter = Splitter.on(" ");

        List<String> lines = splitter.splitToList(line);

        String name = lines.get(0);
        String password = lines.get(1);
        int age = Integer.parseInt(lines.get(2));
        int id = Integer.parseInt(lines.get(3));

        return new User(name, password, age, id);
    }
}
