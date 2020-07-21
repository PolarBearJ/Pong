import java.awt.*;

public class Ball extends GameObject {
    //Initialize all my variables that the ball uses
    private final Handler handler;
    private final Game game;
    private int centerx; //center of the balls coords
    private int centery;
    private int radius = 15; // radius of the ball
    private int startnum = 0; // used to know if reset
    private String score = ""; // score string to display
    private int p1x, p1y, p2x, p2y; //coords of the player for collision
    private int hits = 0;
    private double speedBonus = 1;
    private int ticks = 0;
    public Ball(int x, int y, ID id, Handler handler, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
    }

//Checks for collision on the ball and players
    /*
    Algorithm:
    if the ball is at the x value of a player and in the range of the paddle then velX = -velX (deflect)
    if ball hits wall then difflect
    if ball hits right or left wall then add a point to correct player
    Also if the ball hits a paddle the speed of the ball is increased by 10%
     */
    private void collision() {
        centerx = x + 15;
        centery = y + 15;
        if (centerx + radius <= 25) {
            game.setP2s(game.getP2s()+1);
        x= -6;
            velX = -velX;
            if(velX < 0){
                velX = -2;
            } else if (velX > 0){
                velX = 2;
            }
//            velX -= velX*(hits*speedMultiplier);
            hits = 0;
        } else if (centerx + radius >= 795){
            game.setP1s(game.getP1s()+1);
            x = 766;
            velX = -velX;
            if(velX < 0){
                velX = -2;
            } else if (velX > 0){
                velX = 2;
            }
//            velX -= velX*(hits*speedMultiplier);
            hits = 0;
        }
        if ((centery - radius) < 5 || (centery + radius) >= 675) {
            velY = -velY;
        }
                p1x = game.getP1x();
                p1y = game.getP1y();
                p2x = game.getP2x();
                p2y = game.getP2y();

            if (centerx + radius <= p1x + 50 && centery >= p1y && centery<= p1y + 75) {
                hits++;
                velX = -velX;
                if(velX < 0){
                    velX -= speedBonus;
                } else {
                    velX += speedBonus;
                }
            }
            if (centerx + radius >= p2x+15 && centery >= p2y && centery <= p2y + 75) {
                hits++;
                velX = -velX;
                if(velX < 0){
                    velX -= speedBonus;
                } else {
                    velX += speedBonus;
                }
            }
    }


//This is the tick method that happens 60 times per second
    public void tick() {
        if(!game.isPaused()) {
            ticks++;
            if (ticks % 30 == 0) {
                System.out.println(velX);
            }
            //For resetting the game
            if (game.isBallReset()) {
                x = 400;
                y = 600;
                startnum = 0;
                game.setBallReset(false);
            }
            if (startnum == 0) {
                velY = -2;
                velX = -2;
            }
            //Collide and move the ball
            startnum++;
            collision();
            y += velY;
            x += velX;

            score = game.getP1s() + "                " + game.getP2s();
        }
    }
//Renders the ball AS WELL AS THE SCORE
    //also draw cool line son the paddles to enphasise where the collisions happen
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x, y, radius, radius);
        Font h = new Font("TimesRoman", Font.PLAIN, 75);
        g.setFont(h);
        g.drawString(score,175,125);
        g.setColor(Color.red);
        g.fillRect(p1x+13, p1y, 3, 75);
        g.setColor(Color.blue);
        g.fillRect(p2x-1, p2y, 3, 75);
    }

}
