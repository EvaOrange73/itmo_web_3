import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.Date;

@Named
@RequestScoped
public class Point {
    private float x;
    private float y;
    private float r;
    private boolean result;
    private Date requestTime;
    private long processTime;

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