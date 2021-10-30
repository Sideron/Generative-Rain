//Developed by Sideron 2021
package cells;
import java.awt.Graphics;
public class Object {
    public float x;
    public float y;
    public boolean destroy = false;
    public Object(){
        this.x = 0;
        this.y = 0;
    }
    public Object(float x, float y){
        this.x = x;
        this.y = y;
    }
    public void update(){
        
    }
    public void display(Graphics g){
        
    }
    public float disB2n(Object c){
        float dis = (float)Math.sqrt(Math.pow(Math.abs(x-c.x), 2)+Math.pow(Math.abs(y-c.y), 2));
        return dis;
    }
}
