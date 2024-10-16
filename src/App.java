import processing.core.*;

public class App extends PApplet {
    float ballX, ballY, hoopX, hoopY; // coordinates of ball and hoop 
    float ballSize = 20; //size of ball
    float hoopSize = 70; // size of hoop
    boolean isJumping = false; //condition for if the ball is jumping
    float jumpHeight = -12; // initial upward velocity for the jump
    float velocity = 0; // vertical speed of the ball
    boolean collided = false; // condition for collision
    int points = 0; //starting points value
    int startTime; // time when game starts
    double delay = 1; //delay time
    boolean gameOver = false; //condition for game over
    boolean showIntro = true; //condition for showing intro
    double gravity = 0.5; //gravity on ball
    double ballSpeed = 1.3; //initial horizontal speed of the hoop
    // positioning for reset button:
    float resetButtonX = 300;
    float resetButtonY = 400;
    float resetButtonWidth = 200;
    float resetButtonHeight = 50;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void settings() {
        size(800, 600); //size of my game screen

    }

    public void setup() {
        ballX = 200; // starting horizontal value for ball
        ballY = height / 2; // keeps the ball centered vertically
        hoopX = width - 200; // places the hoop near the right side
        hoopY = height / 2; // keeps the hoop centered vertically
        startTime = millis(); // Sets the game start time
    }

    public void draw() {
        background(135, 206, 235); //sets background color to light blue

        if (showIntro) {
            // Intro screen content
            fill(0);
            textSize(64);
            text("Flappy Hoop", width / 2 - 180, height / 2 - 50);

            textSize(32);
            text("Press any key to start", width / 2 - 150, height / 2 + 50);

            textSize(32);
            text("Use spacebar to jump", width / 2 - 150, height / 2 + 150);

            textSize(32);
            text("Use R to reset", width / 2 - 150, height / 2 + 250);
        } else if (!gameOver) {
            int timeSinceStart = (millis() - startTime) / 1000; // converts time since start to seconds

            if (timeSinceStart > delay) {

                velocity += gravity; // updates ball's vertical speed based on gravity
                ballY += velocity;
            }

            // checks if the ball falls off the bottom of the screen
            if (ballY > height) {
                ballY = height;
                gameOver = true;
            }

            if (ballY < 0) { 
                gameOver = true; // checks if the ball goes above the top of the screen, showing a game over
            }
            // draws the ball
            fill(255, 0, 0);
            ellipse(ballX, ballY, ballSize, ballSize);
            // draws the hoop
            fill(0, 0, 255);
            ellipse(hoopX, hoopY, hoopSize, hoopSize);
         
            hoopX -= ballSpeed;
            // moves the hoop left across the screen based on the current ball speed
            ballSpeed += 0.003;
            // increases the speed of the ball over time
            fill(0);
            textSize(32);
            text("Score: " + points, 20, 40); // score and the coordinates of location from left edge of window

            if (collision()) {
                points++; // counts the points if ball collides with hoop
                ballX = 200; // resets the ball's position to the left after colliding with the hoop
                ballY = height / 2; // centering the ball
                velocity = 0; // movement of ball going up and down
                collided = true; // marks that the ball has collided with the hoop
                hoopX = width + hoopSize; // Reset circle to the right
            } else {
                collided = false; // marks that there was no collision
            }

            if (ballY > height || (ballX > hoopX + hoopSize / 2 && !collided)) {
                gameOver = true;   // checking for if game should end
            }

        } else { // end screen content
            fill(255, 0, 0);
            textSize(64);
            text("GAME OVER", width / 2 - 150, height / 2); 
            fill(0);
            textSize(32);
            text("Points: " + points, width / 2 - 100, height / 2 + 50); 
            fill(0, 255, 0);
            rect(resetButtonX, resetButtonY, resetButtonWidth, resetButtonHeight); // draws the reset button
            fill(0);
            textSize(32);
            text("RESET", resetButtonX + 50, resetButtonY + 35); 
        }
    }

    // method for checking a collision
    public boolean collision() {
        float distance = dist(ballX, ballY, hoopX, hoopY);
        return distance <= (ballSize / 2 + hoopSize / 2);
    }

    // method for reseting game (basiclluy just resetting up and redefining the variables)
    public void resetGame() {
        ballX = 200; 
        ballY = height / 2; 
        hoopX = width - 200; 
        hoopY = height / 2; 
        points = 0;
        gameOver = false; 
        isJumping = false; 
        velocity = 0; 
        ballSpeed = 1.3; 
        startTime = millis();  
    }

    public void keyPressed() { //method for key mechanisms
        if (showIntro) {
            showIntro = false; // exit intro screen on any key press
        } else if (key == ' ' && !gameOver) { // use spacebar to click
            velocity = jumpHeight;
            isJumping = true;
        } else if (key == 'r' || key == 'R') { // use r to reset game
            resetGame();
        }
    }

        // Checks if it's game over and if the mouse click is within the reset button's area
    public void mousePressed() {
        if (gameOver && mouseX > resetButtonX && mouseX < resetButtonX + resetButtonWidth && 
                mouseY > resetButtonY && mouseY < resetButtonY + resetButtonHeight) { 
            resetGame(); // restarts the game
        }
    }
}
