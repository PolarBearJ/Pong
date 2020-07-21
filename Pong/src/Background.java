import java.awt.*;

public class Background extends GameObject {
    private final Game game;
    private boolean over = false;
    //Constructor
    public Background(int x, int y, ID id, Game game) {
        super(x, y, id);
        this.game = game;
    }
//No need to tick because will not interact
    public void tick() {

    }
//renders the background and yellow line to seperate buttons from game
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        float alpha = 1f;
        AlphaComposite alcom = AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha);
        g2d.setComposite(alcom);

        g.setColor(Color.black);
        g.fillRect(x, y, 800, 800);
        g.setColor(Color.yellow);
        g.drawLine(0, 675, 800, 675);
        Font h = new Font("TimesRoman", Font.PLAIN, 30);
        Font b = new Font("TimesRoman", Font.PLAIN, 15);
        g.setColor(Color.white);
        g.setFont(b);
        g.drawString("Press Up and Down Arrow keys to move up and down", 10, 15);
        g.drawString("Press Q and Z to move up and down", 520, 15);



        if(game.getP2s() >= 5 || game.getP1s() >= 5){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, 800, 800);
            g.setColor(Color.white);
            g.setFont(h);
            g.drawString("Game Over", 300, 350);
            g.setFont(b);
            g.drawString("(Reset to play again or Exit game)", 290, 400);
            over = true;
            game.setPaused(true);
        }


        if(game.isPaused() && !over){
            //This alpha and alcom part is just to make the pause look cooler
            //This will make it so there is a translucent background so you can
            //still se the game but nothing is moving
            alpha = .5f;
             alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(alcom);
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, 800, 800);
            alpha = 1f;
            alcom = AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, alpha);
            g2d.setComposite(alcom);
            g.setColor(Color.white);
            g.setFont(h);
            g.drawString("Game Paused", 300, 350);
            g.setFont(b);
            g.drawString("(To unpause press Enter again)", 290, 400);
        }



    }

}
