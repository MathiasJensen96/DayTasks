package persistence;


import entities.Registration;

import java.sql.*;
import java.util.ArrayList;

public class RegistrationMapper {

    private Database database;

    public RegistrationMapper(Database database) {
        this.database = database;
    }

    public boolean addToTeam(int member_id, String team_id) {

        boolean result = false;
        String sql = "UPDATE registration SET team_id = ? WHERE member_id = ?;";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, team_id);
                ps.setString(2, String.valueOf(member_id));
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected == 1) {
                    result = true;
                }
            } catch (SQLException throwables) {
                // TODO: Make own throwable exception and let it bubble upwards
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            // TODO: Make own throwable exception and let it bubble upwards
            throwables.printStackTrace();
        }
        return result;
    }

    public ArrayList<Registration> getAllRegistrations() {
        ArrayList<Registration> allRegistrations = new ArrayList<>();
        String sql = "select * from extraInfo;";
        try (Connection connection = database.connect()) {
            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    int memberID = rs.getInt("member_id");
                    String teamID = rs.getString("team_id");
                    int price = rs.getInt("price");
                    String name = rs.getString("name");
                    Registration registration = new Registration(memberID, teamID, price, name);
                    allRegistrations.add(registration);
                }
                System.out.println(allRegistrations.toString());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allRegistrations;
    }
}