import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Named
@SessionScoped
public class DBManager implements Serializable {
    @Inject
    Point point;

    Connection connection;

    @PostConstruct
    public void init() {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream("/home/eva/IdeaProjects/itmo_web_3/src/main/java/db.cfg");
            properties.load(file);
            try {
                this.connection = DriverManager.getConnection(url, properties);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertPoint() {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "INSERT INTO points(x, y, r, result, requesttime, processtime) " +
                            "VALUES (?, ?, ?, ?, ?, ?);"
            );
            preparedStatement.setDouble(1, point.getX());
            preparedStatement.setDouble(2, point.getY());
            preparedStatement.setDouble(3, point.getR());
            preparedStatement.setBoolean(4, point.isResult());
            preparedStatement.setLong(5, point.getRequestTime().getTime());
            point.setProcessTime(new Date().getTime() - point.getRequestTime().getTime());
            preparedStatement.setLong(6, point.getProcessTime());

            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Point> getPoints() {
        List<Point> points = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from points");
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()) {
                Point point = new Point();
                point.setX(resultSet.getFloat("x"));
                point.setY(resultSet.getFloat("y"));
                point.setR(resultSet.getFloat("r"));
                point.setResult(resultSet.getBoolean("result"));
                point.setRequestTime(resultSet.getLong("requestTime"));
                point.setProcessTime(resultSet.getLong("processTime"));
                points.add(point);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return points;
    }
}

