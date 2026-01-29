import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class MyApp{
    public static void main(String[] args) {
        JFrame appFrame = new JFrame("Painting");
        appFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //to add a canvas to the frame,Something we can draw on.We another Swing component => JPanel. 
        MyCanvas myCanvas = new MyCanvas();
        appFrame.add(myCanvas);

        // fit the frame size around the canvas(components)
        appFrame.pack();
        // don't allow the user to resize the window(frame)
        appFrame.setResizable(false);
        // open frame in the center of the screen
        appFrame.setLocationRelativeTo(null);


       //show the frame as the last step, so move that to the bottom
       appFrame.setVisible(true);
}
}

class MyCanvas extends JPanel implements Runnable {
    private boolean running = true;

    public MyCanvas() {
        setPreferredSize(new Dimension(900, 600));
        setBackground(Color.LIGHT_GRAY);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
       
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w = getWidth();
        int h = getHeight();
        Color color1 = Color.RED;
        Color color2 = Color.ORANGE;
        GradientPaint gp = new GradientPaint(0, 0, color1, w, h, color2);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);

     }

     @Override
    public void run() {
        // simple game loop ~60 FPS
        while (running) {
            repaint();
            try {
                Thread.sleep(16); // ~16 ms ≈ 60 frames per second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}