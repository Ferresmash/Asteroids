package legacy;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;

import asteroidState.LargeState;
import entities.Asteroid;
import pos.Position;

public class Game extends Canvas implements Runnable {
    
    public static final int WIDTH = 160;
    public static final int HEIGHT = WIDTH / 12 * 9;
    public static final int SCALE = 3;
    public static final String NAME = "Game 2D";
    private static final long serialVersionUID = 1L;
    private Asteroid a = new Asteroid();
    
    private JFrame frame;
    public boolean running = false;
    public int tickCount = 0;
    
    public Game() { 
        setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
        
        frame = new JFrame(NAME);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public synchronized void start() {
        new Thread(this).start();
        running = true;
    }
    
    public synchronized void stop() {
        running = false;
    }
    
    
    @Override
    public void run() {
    	
        long lastTime = System.nanoTime();
        double nsPerTick = 1000000000D / 64;
        
        int ticks = 0;
        int frames = 0;
        
        long lastTimer = System.currentTimeMillis();
        double delta = 0;
        
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerTick;
            lastTime = now;
            boolean shouldRender = true;
            
            while (delta >= 1) {
                ticks++;
                tick();
                delta -= 1;
                shouldRender = true;
            }
            
            try {
                Thread.sleep(2);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            
            if (shouldRender) {
                frames++;
                render();
            }
            
            if (System.currentTimeMillis() - lastTimer >= 1000) {
                lastTimer += 1000;
                System.out.println(ticks + " ticks, " + frames + " frames");
                frames = 0;
                ticks = 0;
            }
        }
    }
    
    public void tick() {
        tickCount++;
    }
    
    public void render() {

        Graphics g = getGraphics();
        g.clearRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);
        g.setColor(Color.black);
        g.drawRect(50, 50, 50, 50);
        Position pos = a.getPosition();
        pos.setX(pos.getX()+1);
        a.setPosition(pos);
        a.draw(g);
    }
}

