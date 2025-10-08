package vnu.edu.vn.game;

import javafx.scene.canvas.GraphicsContext;

///  Draw everything and update them


/// Father class
public abstract class GameObject {
    public double x;
    public double y;
    public double width;
    public double height;


    public GameObject(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    public abstract void update();

    public abstract void render(GraphicsContext gc);



    public boolean Collusion(GameObject other) {
        return (x < other.x + other.width && x + width > other.x && y < other.y + other.height && y + height > other.y);
    }

}
