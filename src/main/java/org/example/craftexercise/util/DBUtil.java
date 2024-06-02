package org.example.craftexercise.util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

    private final String configPropertiesString = "config.properties";

    // Load properties from file on classpath
    public Connection ConnectionToDB(){


        Properties props = getApplicationProperties(configPropertiesString);
        Connection dbCon = null;

        try{
            dbCon = DriverManager.getConnection(props.getProperty("db.url") + props.getProperty("db.database"), props.getProperty("db.username"), props.getProperty("db.password"));
        }catch (SQLException ex){
            System.out.println("Connection Failed! Check Console Output!");
            ex.printStackTrace();
        }
        return dbCon;
    }


    private Properties getApplicationProperties(String configPropertiesString){

        Properties properties = new Properties();

        try (InputStream input = DBUtil.class.getClassLoader().getResourceAsStream(configPropertiesString)){
            // Load properties from file on classpath
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return properties;
    }




}
