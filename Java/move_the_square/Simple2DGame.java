import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Simple2DGame extends JPanel implements KeyListener, Runnable {

    private int x = 100;      // player position
    private int y = 100;
    private int speed = 5;    // movement speed
    private boolean running = true;

    public Simple2DGame() {
        setPreferredSize(new Dimension(1400, 1400));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        new Thread(this).start(); // game loop thread
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 30, 30); // draw "player"
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

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP)    y -= speed;
        if (code == KeyEvent.VK_DOWN)  y += speed;
        if (code == KeyEvent.VK_LEFT)  x -= speed;
        if (code == KeyEvent.VK_RIGHT) x += speed;
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple 2D Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Simple2DGame());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
