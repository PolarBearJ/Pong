
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.IOException;
/*
**************IMPORTANT**************
Program by Joshua Stevens, DATE May 08, 2020
This game uses a somewhat game engine that I have been
working on for my culminating. This program implements the following classes
* Background - created as background design
* Ball - ball that bounces between paddles
* Game - CREATES ALL OBJECTS AND IMPLEMENTS ALL CLASSES
* GameObject - An abstract class that my game objects a all extend
* Handler - Handles player movement and inputs
* ID - Keeps track of all my objects and is used to find certain information (explained in detail later)
* KeyInput - Takes inputs from the keyboard
* MouseInput - takes inputs from the mouse(for buttons)
* Player1 - Player1 object
* Player2 - Player2 object (Basically the exact same with different buttons for movement)
* Window - Creates the actual window that is being played in
MORE DETAIL ON METHODS IS GIVEN
*
*
*           EDIT  May 11/20
*   Added Pause feature located in Background
*   Also added Game over feature in Background which implements pausing
*
 */
//Game
public class Game extends Canvas implements Runnable {
    //Create variables
    private boolean isRunning = false;
    private Thread thread;
    private Handler handler;
    public final int WIDTH = 800;
    public final int HEIGHT = 800;
    private int p1s = 0;
    private int p2s = 0;
    private boolean ballReset = false;
    private int p1y = 325;
    private int p2y = 325;
    private int p1x = 15;
    private int p2x = 750;
    private boolean paused = false;
    //Constructor for the class
    //Creates the window and adds all my gameobjects to my handler.
    //As well add my listeners
    public Game() throws IOException {
        new Window(WIDTH, HEIGHT, "Pong", this, handler);
        start();
        handler = new Handler();
        handler.addObject(new Background(0, 0, ID.BACKGROUND, this));
        handler.addObject(new Player2(750, 400-75, ID.PLAYER2, handler, this));
        handler.addObject(new Player1(15, 400-75, ID.PLAYER1, handler, this));
        handler.addObject(new Ball(400, 600, ID.BALL, handler, this));
        this.addKeyListener(new KeyInput(handler, this));
    }
    //Starts the game
    private void start() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
    //Closes the window upon exit button click
    public void stop() {
        System.exit(0);
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /*
    Explanation for the following method:
    Using same type of engine as Minecraft,
    if the computer is lagging behind (aka the time now minus the last time devided by tickspersecond is greater than one then do remaining ticks needed)
    there will be 60 ticks per second
    nanoseconds per tick is described below
     */
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double ticksPerSecond = 60;
        double nsPerTick = 1000000000 / ticksPerSecond;
        double delta = 0;
        long timer = System.currentTimeMillis();

        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            render();
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
            }

        }
        stop();
    }
//This tick method makes all GAMEOBJECTS tick
    public void tick() {
        handler.tick();

    }
//Render draws all my gameobjects
    public void render() {
        BufferStrategy bs = this.getBufferStrategy(); //created null
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////////////////////////// between these lines are what is being shown
        handler.render(g);
        /////////////////////////
        g.dispose();
        bs.show();
    }
/*
The following methods are a bunch of setters and getters for various uses.
P1s and P2s are the respective scores of the players
BallReset is used to reset the ball when reset button is pressed
P1y and P2y are used to get and set the players y value when resetting game
P1x and P2x are used to get and set the players x value when doing collisions and resetting
 */
    public int getP1s() {
        return p1s;
    }

    public void setP1s(int p1s) {
        this.p1s = p1s;
    }

    public int getP2s() {
        return p2s;
    }

    public void setP2s(int p2s) {
        this.p2s = p2s;
    }

    public boolean isBallReset() {
        return ballReset;
    }

    public void setBallReset(boolean ballReset) {
        this.ballReset = ballReset;
    }

    public int getP1y() {
        return p1y;
    }

    public void setP1y(int p1y) {
        this.p1y = p1y;
    }

    public int getP2y() {
        return p2y;
    }

    public void setP2y(int p2y) {
        this.p2y = p2y;
    }
    public int getP1x() {
        return p1x;
    }

    public void setP1x(int p1x) {
        this.p1x = p1x;
    }

    public int getP2x() {
        return p2x;
    }

    public void setP2x(int p2x) {
        this.p2x = p2x;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


//Main method creates a new game
    public static void main(String args[]) throws IOException {
        new Game();
    }




}
