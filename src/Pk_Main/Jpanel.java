

package Pk_Main;


import entity.Player;
import entity.entity;
import entity.projecttile;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.spec.ChaCha20ParameterSpec;
import javax.swing.JPanel;
import tile.TileManagerment;


public class Jpanel extends JPanel implements Runnable{

    public double playTime;
    
    int defaultsize=12;
    int scale=3;
    
    //Screen SETTING
    public int tilesize=defaultsize*scale;
    public int maxCol=18;
    public int maxRow=14;
    public int screenHeight=maxRow*tilesize;
    public int screenWith=maxCol*tilesize;
    
    //WORLD SETTING
    public int maxWorldCol=44;
    public int maxWorldRow=42;
    public int worldHeight=tilesize*maxWorldRow;
    public int worldWight=tilesize*maxWorldCol;
    
    //FPS
    int FPS=60;
    
    //GAME STATE
    public int state;
    public int gamePlay=0;
    public int gamePause=1;  
    public int gameDinalogMess=2;
    public int playerStatus=3;
    public int gameTitle=10;
    public int gameOver=4;
    public int finish=5;
    public int guide=6;
    
    public int stage=1;
    public int currentMap=0;
    public int maxmap=10;
    
    public Jpanel(){
    
        this.setPreferredSize(new Dimension(screenWith,screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);   
        this.addKeyListener(keyh);
        this.setFocusable(true);
    }
    
    //Declare ALL instance
    public EventHandler eventh=new EventHandler(this);
    public keyHandler keyh = new keyHandler(this);
    public collisionChecker cCheck = new collisionChecker(this);
    public TileManagerment tileMn = new TileManagerment(this);
    Thread gameThread;
    public AssetSetter ast = new AssetSetter(this);
    sound sound=new sound();
    sound se=new sound();
    public UI ui=new UI(this);
    public configGame gameData=new configGame(this);
    
    //PLAYER AND ENTITY
    public entity obj[][] = new entity[maxmap][10];
    public entity npc[][]=new entity[maxmap][10];
    public Player player = new Player(this, keyh);
    public ArrayList<entity> arrayEntity=new ArrayList<>();
    public entity monster[][]=new entity[maxmap][10];
    public ArrayList<projecttile> projecttileList=new ArrayList<>();
       
    
    public void setupGame()
    {
        ast.setObject();  
        ast.setNPC();
        ast.setMonster();
        state=gameTitle;
        playMusic(4);
    }
    
    public void playMusic(int i)
    {
        sound.setFile(i);
        sound.play();
        sound.soundLoop();
    }
    
    public void stopMusic()
    {
        sound.stop();
    }
    
    public void playSE(int i)
    {
        se.setFile(i);
        se.play();
    }
    
    public void restart()
    {
        playTime=0;
        player.setDefaultValue();
        player.setIventory();
        ast.setObject();  
        ast.setNPC();
        ast.setMonster();
    }
    
    public void threadGameStart()
    {
        gameThread =new Thread(this);
        gameThread.start();         
    }
    
    
    @Override
    public void run() {
        
        double drawingtime=1000000000/FPS;
        double nextDrawingtime=System.nanoTime()+drawingtime;
        
        while(true)
        {
            update();
            repaint();
                      
            try {
                double reaminingtime=nextDrawingtime-System.nanoTime();
                reaminingtime=reaminingtime/1000000;
                
                if(reaminingtime<0)
                    reaminingtime=0;
                
                Thread.sleep((long) reaminingtime);
                
                nextDrawingtime+=drawingtime;
            } catch (InterruptedException ex) {
                Logger.getLogger(Jpanel.class.getName()).log(Level.SEVERE, null, ex);
            }          
        }
    }
     
    public void update()
    {
        if(state==gamePlay)
        {
            player.update();
            for(int i=0;i<npc[currentMap].length;i++)
            {
                if(npc[currentMap][i]!=null)
                {
                    npc[currentMap][i].update();
                }
            }
            for(int i=0;i<monster[currentMap].length;i++)
            {
                if(monster[currentMap][i]!=null && monster[currentMap][i].life!=-999)
                {
                    monster[currentMap][i].update();
                }
                else
                {
                    monster[currentMap][i]=null;
                }
            }
            for(int i=0;i<projecttileList.size();i++)
            {
                if (projecttileList.get(i) != null) {
                    if (projecttileList.get(i).alive == true) {
                        projecttileList.get(i).update();
                    } else {
                        projecttileList.remove(i);
                    }
                }
            }
        }
        else if(state==gamePause)
        {
            //DO NOT THING
        }
        else if(state==playerStatus)
        {
            
        }
        else if(state==gameDinalogMess)
        {
            player.update();       
        }
        
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d=(Graphics2D)g;
        
        //Check effective
        long drawStart=System.nanoTime();
        
        if(state==gameTitle)
        {
            //DRAW TITLE GAME
            ui.draw(g2d);
        }
        else
        {
            //Draw Tiles
            tileMn.draw(g2d);
            
            //DRAW ENTITY
            
            arrayEntity.add(player);
            for(int i=0;i<obj[currentMap].length;i++)
            {
                if(obj[currentMap][i]!=null)
                {
                    arrayEntity.add(obj[currentMap][i]);
                }
            }
            for(int i=0;i<npc[currentMap].length;i++)
            {
                if(npc[currentMap][i]!=null)
                {
                    arrayEntity.add(npc[currentMap][i]);
                }
            }
            for(int i=0;i<monster[currentMap].length;i++)
            {
                if(monster[currentMap][i]!=null)
                {
                    arrayEntity.add(monster[currentMap][i]);
                }
            }
            for(int i=0;i<projecttileList.size();i++)
            {
                if(projecttileList.get(i)!=null)
                {
                    arrayEntity.add(projecttileList.get(i));
                }
            }
            Collections.sort(arrayEntity,new Comparator<entity>() {
                @Override
                public int compare(entity o1, entity o2) {
                    int result=Integer.compare(o1.worldY, o2.worldY);
                    return result;
                }
            });
            
            for(entity a:arrayEntity)
            {
                a.draw(g2d);
            }
            
            arrayEntity.clear();
            
            //DRAW UI
            ui.draw(g2d);
            
        }

        if(state==gamePlay)
        {
            playTime+=(double)1/60;
            g2d.setColor(Color.GRAY);
            g2d.fillRect(screenWith-4*tilesize-10,10, 4*tilesize,tilesize);
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial",Font.PLAIN, 20));
            g2d.drawString("Time: "+String.format("%.1f", playTime), screenWith-4*tilesize,tilesize);
        }
        //CHECK EFFECTIVE
        if(keyh.checkTime==true){
            long drawend=System.nanoTime();
            long pass=drawend-drawStart;
            g2d.setColor(Color.white);
            g2d.drawString("Draw Time: "+pass, screenWith/2-3*tilesize,2*tilesize);
            System.out.println(pass);
        }
        
        g2d.dispose();
    }
    
}
