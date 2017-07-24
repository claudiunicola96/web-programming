package service;

import model.File;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by claudiu on 21.05.2017.
 */
public class UploadService {
    private Connection connection = null;

    public UploadService() throws ClassNotFoundException, IOException, SQLException {
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

    public void store(String name, String type, String path, long size) throws SQLException {
        PreparedStatement query = connection.prepareStatement(
                "insert into files(name, type, path, size) values(?,?,?,?)"
        );
        query.setString(1, name);
        query.setString(2, type);
        query.setString(3, path);
        query.setLong(4, size);

        query.executeUpdate();
    }

    public List<File> all() throws SQLException {
        PreparedStatement query = connection.prepareStatement("select id,name,type,size from files");
        ResultSet result = query.executeQuery();

        List<File> files = new ArrayList<>();
        while (result.next()) {
            files.add(new File(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getLong(4)
            ));
        }

        return files;
    }

    public File find(int id) throws SQLException {
        PreparedStatement query = connection.prepareStatement("select * from files where id=?");
        query.setInt(1, id);
        ResultSet result = query.executeQuery();

        if (result.isBeforeFirst()) {
            result.next();
            return new File(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getLong(5)
            );
        }

        return null;
    }

    public void update(int id, String name) throws SQLException {
        File file = find(id);
        PreparedStatement query = connection.prepareStatement("update files set name=? where id=?");
        query.setString(1, name);
        query.setInt(2, id);

        query.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        File file = find(id);
        PreparedStatement query = connection.prepareStatement("delete from files where id=?");
        query.setInt(1, id);

        query.executeUpdate();
    }

    public List<File> allTypes() throws SQLException {
        PreparedStatement query = connection.prepareStatement("select distinct type from files");
        ResultSet result = query.executeQuery();

        List<File> types = new ArrayList<>();
        while (result.next()) {
            types.add(new File(result.getString(1)));
        }

        return types;
    }

    public List<File> getByType(String type) throws SQLException {
        PreparedStatement query = connection.prepareStatement("select * from files where type=? ");
        query.setString(1, type);
        ResultSet result = query.executeQuery();
        List<File> files = new ArrayList<>();
        while (result.next()) {
            files.add(new File(
                    result.getInt(1),
                    result.getString(2),
                    result.getString(3),
                    result.getLong(5)
            ));
        }

        return files;
    }
}
