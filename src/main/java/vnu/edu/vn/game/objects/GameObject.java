package vnu.edu.vn.game.objects;

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

    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

}
