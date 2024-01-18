import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@ManagedBean
@Named
@SessionScoped
public class PointsList implements Serializable {
    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        String url = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream("/home/eva/IdeaProjects/itmo_web_3/src/main/java/db.cfg");
            properties.load(file);
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement preparedStatement = connection.prepareStatement("Select * from points");
                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();

                while (resultSet.next()) {
                    Point point = new Point();
                    point.setX(resultSet.getFloat("x"));
                    point.setY(resultSet.getFloat("y"));
                    point.setR(resultSet.getFloat("r"));
                    point.setResult(resultSet.getBoolean("result"));
                    point.setRequestTime(resultSet.getTimestamp("requestTime"));
                    point.setProcessTime(resultSet.getInt("processTime"));
                    points.add(point);
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return points;
    }


}

