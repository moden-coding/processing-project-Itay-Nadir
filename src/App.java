import processing.core.*;

public class App extends PApplet {
    float circle1X, circle1Y, circle2X, circle2Y;
    float circle1Size = 30;
    float circle2Size = 70;
    float circle1Speed = 5;
    float circle2Speed = 3;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);

    }

    public void setup() {
        circle1X = 50;
        circle1Y = height / 2;
        circle2X = 200;
        circle2Y = height / 2;
        
    }
        public void draw() {
        background(135,206,235);
        fill(255, 0, 0);
        ellipse(circle1X, circle1Y, circle1Size, circle1Size);
        



        if (circle2X > width) {
            circle2X = 0;
        }
    }
    public boolean collision(circle1, circle2){
        double distanceofCircles = dist(circle1X, circle1Y, circle2X, circle2Y);
        if(distanceofCircles <= (circle1 )){
            return true;
        }else{
            return false;
        }
        }
    public void mousePressed() {
        circle1X += circle1Speed;
}
}
