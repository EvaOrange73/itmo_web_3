import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

@Named
@RequestScoped
public class Point {
    private float x;
    private float y;
    private float r = 3;
    private boolean result;
    private Date requestTime;
    private long processTime;

    public Point(){}

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public boolean isResult() {
        return result;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public long getProcessTime() {
        return processTime;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }

    public void addPoint(){
        String url = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Properties properties = new Properties();
            FileInputStream file = new FileInputStream("/home/eva/IdeaProjects/itmo_web_3/src/main/java/db.cfg");
            properties.load(file);
            try {
                Connection connection = DriverManager.getConnection(url, properties);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "INSERT INTO points(x, y, r, result, requesttime, processtime) " +
                                "VALUES (?, ?, ?, ?, ?, ?);"
                );
                preparedStatement.setDouble(1, this.x);
                preparedStatement.setDouble(2, this.y);
                preparedStatement.setDouble(3, this.r);
                preparedStatement.setBoolean(4, this.result);
                preparedStatement.setDate(5, (java.sql.Date) this.requestTime);
                preparedStatement.setLong(6, this.processTime);

                preparedStatement.execute();

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
