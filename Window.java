//Developed by Sideron 2021
package cells;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
public class Window extends JFrame implements Runnable{
    public static Manager adm = new Manager();
    public static final int WIDTH= adm.WIDTH, HEIGHT= adm.HEIGHT;
    private Canvas canvas;
    private Thread thread;
    private boolean running = false;
    
    private BufferStrategy bs;
    private Graphics g;
    
    private final int FPS = 240;
    private double TARGETTIME = 1000000000/FPS;
    private double delta = 0;
    private int AVERAGEFPS = FPS;
    
    public Window(){
        setTitle("Tunnel effect");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        canvas.setMinimumSize(new Dimension(WIDTH-100, HEIGHT-100));
        canvas.setFocusable(true);
        
        add(canvas);
    }
    public static void main(String[] args) {
        adm.start();
        new Window().start();
    }
    private void update(){
        adm.update();
    }
    private void draw(){
        bs = canvas.getBufferStrategy();
        if(bs == null){
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        adm.draw(g);
        //g.setColor(Color.red);
        //g.drawString("FPS: " + AVERAGEFPS , 30,60);
        g.dispose();
        bs.show();
    }
    @Override
    public void run(){
        long now = 0;
        long lastTime = System.nanoTime();
        int frames = 0;
        long time = 0;
        
        while(running){
            now = System.nanoTime();
            delta += (now-lastTime)/TARGETTIME;
            time += (now-lastTime);
            lastTime = now;
            
            if(delta>=1){
                update();
                draw();
                delta--;
                frames++;
            }
            if(time >= 1000000000){
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }
        }
        stop();
    }
    private void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    private void stop(){
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}