import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
/*
Probably most confusing class....
Handler implements the listeners for movement and this handles
players movement and sets setters and getters respectively
 */
public class Handler {
    LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();

    private boolean up1 = false, down1 = false, up2 = false, down2 = false;
    //When handler.tick() is called it all GameObjects will tick
    public void tick(){
        for (GameObject gameObject : getGameObjects()) {
            gameObject.tick();
        }
    }

    private List<GameObject> getGameObjects() {
        return new ArrayList<>(gameObjects);
    }

    public void render(Graphics g){
        for (int i = 0; i< gameObjects.size(); i++){
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(g);
        }
    }
    public void addObject (GameObject tempObject){
        gameObjects.add(tempObject);
    }
    public void removeObject(GameObject tempObject){
        gameObjects.remove(tempObject);
    }
    public boolean isUp() {
        return up1;
    }

    public boolean isDown() {
        return down1;
    }

    public boolean isUp2() {
        return up2;
    }
    public boolean isDown2(){
        return down2;
    }

    public void setUp1(boolean up) {
        this.up1 = up;
    }

    public void setDown1(boolean down) {
        this.down1 = down;
    }

    public void setUp2(boolean right) {
        this.up2 = right;
    }

    public void setDown2(boolean left) {
        this.down2 = left;
    }
}
