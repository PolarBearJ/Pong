import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//This class gets all the keyinputs needed for the movement of the players

public class KeyInput extends KeyAdapter {
    Handler handler;
    private final Game game;

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        for(int i = 0; i <handler.gameObjects.size(); i++){
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()==ID.PLAYER1){
                if(key == KeyEvent.VK_UP){
                    handler.setUp1(true);
                }
                if(key == KeyEvent.VK_DOWN) handler.setDown1(true);
                if(key == KeyEvent.VK_Q) handler.setUp2(true);
                if(key == KeyEvent.VK_Z) handler.setDown2(true);
                if(key == KeyEvent.VK_ENTER){
                    if(game.isPaused()){
                        game.setPaused(false);
                    } else {
                        game.setPaused(true);
                    }
                }
            }
        }

    }
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for(int i = 0; i <handler.gameObjects.size(); i++){
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId()==ID.PLAYER1){
                if(key == KeyEvent.VK_UP) handler.setUp1(false);
                if(key == KeyEvent.VK_DOWN) handler.setDown1(false);
                if(key == KeyEvent.VK_Q) handler.setUp2(false);
                if(key == KeyEvent.VK_Z) handler.setDown2(false);
            }
        }

    }
}
