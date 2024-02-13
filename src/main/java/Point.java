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


}
