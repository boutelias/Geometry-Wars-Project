package model;

public class Position {
    private float x;
    private float y;
    
    public Position(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public Position(){
        this.x = 0;
        this.y = 0;
    }

    /* Getters and Setters */
    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
}
