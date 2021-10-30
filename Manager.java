//Developed by Sideron 2021
package cells;
import java.awt.Color;
import java.awt.Graphics;
public class Manager {
    public Object[] instances = new Object[4000];
    public int count = 0;
    public int WIDTH = 1000;
    public int HEIGHT = 1000;
    public float step;
    float counter;
    float counter2;
    boolean reached;
    public Manager(){}
    public void start(){
        newObject(new Cell(WIDTH/2,HEIGHT/2,step));
        reached = false;
    }
    public void update(){
        step+=360;
        //counter+=180;
        //counter2+=360;
        for(int i = 0; i < instances.length; i++){
            if(instances[i] != null){
                if(!instances[i].destroy){
                    instances[i].update();
                }else{
                    destroyObject(i);
                }
            }
        }
        //newObject(new Cell(WIDTH/2,HEIGHT/2,step));
        //newObject(new Cell((float)Math.cos(step)*WIDTH/3+WIDTH/2,(float)Math.sin(step)*WIDTH/3+WIDTH/2,(float)Math.sin(step)*360));
        Cell cl = new Cell((float)Math.sin(step)*WIDTH/3+WIDTH/2,(float)Math.cos(step)*WIDTH/3+WIDTH/2,step);
        //cl.col = new Color(1,1,1,(float)Math.sin(step));
        newObject(cl);
    }
    public void draw(Graphics g){
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        for(int i = 0; i < instances.length; i++){
            if(instances[i] != null){
                instances[i].display(g);
            }
        }
        //g.setColor(Color.red);
        //g.drawString("Objs: " + count , 30,30);
    }
    public void newObject(Object obj){
        if(count < instances.length){
            instances[count] = obj;
            count += 1;
        }else{
            System.out.println("No more objects allowed :(");
        }
    }
    public void destroyObject(int o){
        //instances[o] = null;
        for(int i=o;i<instances.length;i++){
            if(i!=instances.length-1){
                instances[i] = instances[i+1];
                instances[i+1] = null;
            }
        }
        count-=1;
    }
}
