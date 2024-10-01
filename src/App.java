import processing.core.*;

public class App extends PApplet {
    float circle1X, circle1Y, circle2X, circle2Y;
    float circle1Size = 20;
    float circle2Size = 70; 
    float jumpHeight = 150;
    float circle1Velocity = 0;
    boolean isJumping = false;
    boolean collided = false;
    float gravity = 3;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);

    }

    public void setup() {
        circle1X = 50;
        circle1Y = height / 2;
        circle2X = 800;
        circle2Y = height / 2;

    }

    public void draw() {
        background(135, 206, 235);
        fill(255, 0, 0);

        if (circle2X < 0) {
            circle2X = width;
        }
        if (isJumping) {
            circle1Y -= -5;
            if (circle1Y <= height / 2 - jumpHeight) {
                isJumping = false;
            }

        } else {
            if (circle1Y < height / 2) {
                circle1Y += gravity;
            }
        }

        ellipse(circle1X, circle1Y, circle1Size, circle1Size);

        if (collision()) {
            circle2X = width / 2;
            collided = true;
        } else {
            circle2X -= 2;
            collided = false;
        }
        if (!collided) {
            ellipse(circle2X, circle2Y, circle2Size, circle2Size);
        }
    }

    public boolean collision() {
        double distanceOfCircles = dist(circle1X, circle1Y, circle2X, circle2Y);
        return distanceOfCircles <= (circle1Size / 2 + circle2Size / 2);
    }

    public void keyPressed() {
        if (key == ' ' && !isJumping) {
            isJumping = true;
        }
    }
}
