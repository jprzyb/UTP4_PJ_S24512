package zad1;

import java.sql.*;

public class Database {
    private final String url;
    private final String usr;
    private final String pass;
    private final String driverName;
    private final TravelData travelData;
    private final String createQuery;
    private final String insertQuery;
    public Database(String url, TravelData travelData) {
        this.url = url;
        usr = "utp4";
        pass = "utp4";
        driverName= "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        createQuery ="CREATE TABLE IF NOT EXISTS UTP4_PJ_S24512 (offer_line TEXT);";
        insertQuery = "INSERT INTO UTP4_PJ_S24512 (offer_line) VALUES (?)";
        this.travelData = travelData;
    }

    public void create() {
        try  {
            Connection connection = DriverManager.getConnection(url);
            if (connection != null) {
                connection.getMetaData();
            }

            assert connection != null;
            Statement statement = connection.createStatement();
            statement.execute(createQuery);

            PreparedStatement query = connection.prepareStatement(insertQuery);

            for (Offer offer : travelData.getOffers()) {
                query.setString(1, offer.toString());
                query.execute();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void showGui() {
        new Window(travelData);
    }
}
