package service;

import model.Person;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by claudiu on 19.05.2017.
 */
public class PersonService {

    private List<Person> persons = new ArrayList<>();
    private Connection connection = null;

    public PersonService() throws ClassNotFoundException, IOException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Properties props = new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = classloader.getResourceAsStream("database.properties");
        props.load(resourceStream);
        String dbUrl = props.getProperty("database.url");
        String user = props.getProperty("database.user");
        String pwd = props.getProperty("database.password");
        this.connection = DriverManager.getConnection(dbUrl, user, pwd);
    }

    public Person find(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("select * from persons where id = ?");
        query.setInt(1, id);
        ResultSet result = query.executeQuery();

        if (result.isBeforeFirst()) {
            result.next();
            return new Person(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4),
                    result.getString(5)
            );
        }

        return null;
    }

    public void update(Person newPerson) throws SQLException {
        if (!find(newPerson.getId()).equals(newPerson)) {
            PreparedStatement query = connection.prepareStatement(
                    "update persons set firstname=?,lastname=?,phone=?,email=? where id=?"
            );
            query.setString(1, newPerson.getFirstname());
            query.setString(2, newPerson.getLastname());
            query.setString(3, newPerson.getPhone());
            query.setString(4, newPerson.getEmail());
            query.setInt(5, newPerson.getId());
            query.executeUpdate();
        }
    }
}
