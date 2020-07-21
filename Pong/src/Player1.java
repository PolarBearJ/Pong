import java.awt.*;
//Player 1 class
public class Player1 extends GameObject {
    private final Handler handler;
    private final Game game;
    public Player1(int x, int y, ID id, Handler handler, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
    }
    //Move the paddle depending on the handlers movement
    private void movement(){
        if(handler.isDown() && handler.isUp()) {
            velY = 0;
        } else if (handler.isDown() && !handler.isUp()){
            velY = 8;
        }else if (!handler.isDown() && handler.isUp()){
            velY = -8;
        }else if (!handler.isDown() && !handler.isUp()){
            velY = 0;
        }
    }

//Do the following every tick
    //check the bounds and if everything is fine then call movement()
    public void tick() {
        if(!game.isPaused()) {
            game.setP1y((int) (game.getP1y() + velY));
            y = game.getP1y();
            if (y >= 0 && y <= 600) {
                movement();
            } else {
                velY = -velY;
            }
        }
    }
//Draw the pladdle
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 15, 75);

    }

}
