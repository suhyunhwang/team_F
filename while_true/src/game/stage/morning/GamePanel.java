package game.stage.morning;


import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    public static final int TILE = 32;
    public static final int W = 15;
    public static final int H = 13;
    public static final int DELAY_MS = 120;

    private final GameState state = new GameState(W, H);
    private final Timer timer;

    public GamePanel() {
        setPreferredSize(new Dimension(W * TILE, H * TILE + 120));
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(DELAY_MS, e -> {
            state.tick();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        Render.draw(g2, state, TILE, W, H);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        state.onKey(e.getKeyCode());
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
