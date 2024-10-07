import processing.core.*;

public class App extends PApplet {
    float ballX, ballY, circleX, circleY;
    float ballSize = 20;
    float circleSize = 70;
    boolean isJumping = false;
    float jumpHeight = -12;  
    float velocity = 0;
    boolean collided = false;
    int points = 0;
    boolean gameOver = false;
    double gravity = 0.6;  
    float ballSpeed = 2;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600);

    }

    public void setup() {
        ballX = 50;
        ballY = height / 2;
        circleX = width / 2;
        circleY = height / 2;

    }

    public void draw() {
        background(135, 206, 235);
        if (!gameOver) {
    
            velocity += gravity;
            ballY += velocity;

            if (ballY > height) {
                ballY = height;  
                gameOver = true;
            }

            if (ballY < 0) {
                gameOver = true;
            }
            

            fill(255, 0, 0);
            ellipse(ballX, ballY, ballSize, ballSize);

            fill(0, 0, 255);
            ellipse(circleX, circleY, circleSize, circleSize);
 
            ballX += ballSpeed;

             ballSpeed += 0.001;

                if (collision()) {
                    if (collision()) {
                        points++;  
                        ballX = 50;  
                        ballY = height / 2;
                        velocity = 0;
                        collided = true;
                        ballSpeed = 2;
                    } else {
                        collided = false;
                    }  
                }

            if (ballY > height || (ballX > circleX + circleSize / 2 && !collided)) {
                gameOver = true;
            }

        } else {
            fill(255, 0, 0);
            textSize(64);
            text("GAME OVER", width / 2 - 150, height / 2);
            fill(0);
            textSize(32);
            text("Points: " + points, width / 2 - 100, height / 2 + 50);
            text("Press 'R' to Restart", width / 2 - 100, height / 2 + 100);
    }
}  

    public boolean collision() {
        float distance = dist(ballX, ballY, circleX, circleY);
        return distance <= (ballSize / 2 + circleSize / 2);
    }

    public void keyPressed() {
        if (key == ' ' && !gameOver ) {
            velocity = jumpHeight;
            isJumping = true;
    } else if (key == 'r' || key == 'R') {
        resetGame();
    }
    }
public void resetGame() {
    ballX = 50;
    ballY = height / 2;
    circleX = width / 2;
    circleY = height / 2;
    points = 0;
    gameOver = false;
    isJumping= false;
    velocity = 0;
    ballSpeed = 2;
}
}
