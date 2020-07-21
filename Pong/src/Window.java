import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*
This method creates the window for the game itself. As well as
implement 2 Jbuttons (Reset Game and Exit Game)


 */
//creates game window
public class Window {
    private final Handler handler;
    //method is called with these constructors in Game class
    public Window(int width, int height, String title, Game game, Handler handler){
        this.handler = handler;
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        JButton resetB = new JButton("Reset Game");
        JButton exitB = new JButton("Exit Game");
        resetB.setBounds(120, 700, 220, 50);
        exitB.setBounds(420, 700, 220, 50);
        frame.add(resetB);
        frame.add(exitB);
        frame.add(game);
        exitB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("exit");
                game.stop();
            }
        });
        resetB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("reset");
                game.setPaused(false);
                game.setP1s(0);
                game.setP2s(0);
                game.setBallReset(true);
                game.setP1y(325);
                game.setP2y(325);
            }
        });
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
