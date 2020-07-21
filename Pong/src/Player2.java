import java.awt.*;
//Player 2 class

public class Player2 extends GameObject {
    private final Handler handler;
    private final Game game;
    public Player2(int x, int y, ID id, Handler handler, Game game) {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
    }
    //Move the paddle depending on the handlers movement

    private void movement(){
        if(handler.isDown2() && handler.isUp2()) {
            velY = 0;
        } else if (handler.isDown2() && !handler.isUp2()){
            velY = 8;
        }else if (!handler.isDown2() && handler.isUp2()){
            velY = -8;
        }else if (!handler.isDown2() && !handler.isUp2()){
            velY = 0;
        }
    }

    //Do the following every tick
    //check the bounds and if everything is fine then call movement()
    public void tick() {
        if(!game.isPaused()) {
            game.setP2y((int) (game.getP2y() + velY));
            y = game.getP2y();
            if (y >= 0 && y <= 600) {
                movement();
            } else {
                velY = -velY;
            }
        }
    }
//Draw the paddle
    public void render(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(x, y, 15, 75);

    }


}
