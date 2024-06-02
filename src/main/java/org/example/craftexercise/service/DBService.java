package org.example.craftexercise.service;

import org.example.craftexercise.model.DBUserInformationModel;
import org.example.craftexercise.util.DBUtil;
import org.example.craftexercise.model.FreshdeskModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBService {

    private DBUtil dbUtil = new DBUtil();;
    private final Connection dbConnection = dbUtil.ConnectionToDB();

    public List<DBUserInformationModel> getUserInformationFromDB(){

        List<DBUserInformationModel> userInfo = new ArrayList<DBUserInformationModel>();

        String query = "SELECT * FROM user_information";

        try {
            Statement statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            userInfo = transformSQLResultsToDBInformationModel(rs);



        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return userInfo;
    }


    public int addNewUserInformationInDB(FreshdeskModel model){

        int rows = 0;

        String query = String.format(
                "INSERT INTO user_information (name, email, twitter_id, unique_external_id, creation_time)" +
                "VALUES (\"%s\", \"%s\", \"%s\", \"%s\", CURRENT_TIMESTAMP)",
                model.getName(), model.getEmail(), model.getTwitter_id(), model.getUnique_external_id()
        );

        try {
            Statement statement = dbConnection.createStatement();
            rows = statement.executeUpdate(query);


        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return rows;

    }

    public static List<DBUserInformationModel> transformSQLResultsToDBInformationModel(ResultSet rs) {

        List<DBUserInformationModel> usersInformationList = new ArrayList<DBUserInformationModel>();

        try {

            while (rs.next()){
                DBUserInformationModel newUserInf = new DBUserInformationModel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("twitter_id"),
                        rs.getString("unique_external_id"),
                        rs.getTimestamp("creation_time")
                );

                usersInformationList.add(newUserInf);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return usersInformationList;
    }

}
