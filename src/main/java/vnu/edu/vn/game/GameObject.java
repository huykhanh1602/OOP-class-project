package vnu.edu.vn.game;

import javafx.scene.canvas.GraphicsContext;

public abstract class GameObject {
    public double x;
    public double y;
    public double width;
    public double height;


    public GameObject(double x, double y) {
        this.x = x;
        this.y = y;
    }


}
