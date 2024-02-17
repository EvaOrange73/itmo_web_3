import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.Date;

@Named
@RequestScoped
public class Point {
    private float x;
    private float y;
    private float r = 3;
    private boolean result;
    private long requestTime = new Date().getTime();
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
        return new Date(requestTime);
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

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public void setProcessTime(long processTime) {
        this.processTime = processTime;
    }


}
