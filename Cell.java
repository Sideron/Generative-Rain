//Developed by Sideron 2021
package cells;
import java.awt.Color;
import java.awt.Graphics;
public class Cell extends Object{
    public Cell[] ratio = new Cell[40];
    private float rad = 2;
    private double angle;
    private float speed = 0;
    float sped2=0;
    float sped3 = 0.0000001f;
    public Color col = Color.white;
    public Cell(){
        super(0,0);
        this.angle = 0;
    }
    public Cell(float x, float y, float angle) {
        super(x, y);
        this.angle = angle;
    }
    public void update(){
        super.update();
        speed+=Math.pow(sped2, 0.5f);
        sped2+=sped3;
        sped3 += 0.00000001;
        if(x < -10 || x > 1000 || y < -10 || y>1000){
            destroy = true;
        }
        x+=speed*speed*Math.sin(Math.toRadians(angle));
        y+=speed*speed*Math.cos(Math.toRadians(angle));
        
    }
    public void display(Graphics g){
        super.display(g);
        g.setColor(col);
        g.fillOval((int)(x-rad), (int)(y-rad), (int)(rad*2), (int)(rad*2));
    }
    
}
