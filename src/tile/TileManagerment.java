

package tile;

import Pk_Main.Jpanel;
import Pk_Main.utilityTool;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;


public class TileManagerment {

    Jpanel pn; 
    
    public Tile[] type; 
    
    public int map[][][];
    
    public TileManagerment(Jpanel jpn){
        pn=jpn;
        type=new Tile[50];
        loadImageTile();
        map=new int[pn.maxmap][pn.maxWorldRow][pn.maxWorldCol];
        loadMap(0,getClass().getResourceAsStream("/more/map50x50.txt"));
        loadMap(1,getClass().getResourceAsStream("/more/map.txt"));
    }
    
    public void loadMap(int mapnum ,InputStream source)
    { 
        int row=0;
        int col=0;
        try {
            BufferedReader rf=new BufferedReader(new InputStreamReader(source));
            while(row<pn.maxWorldRow && col<pn.maxWorldCol)
            {
                String temp=rf.readLine();
                String[] num=temp.split(" ");
                while(col<pn.maxWorldCol)
                {
                    
                    map[mapnum][row][col]=Integer.parseInt(num[col]);
                    col++;
                }
                if(row<pn.maxWorldRow)
                {
                    col=0;
                    row++;
                }
            }
            rf.close();
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(pn, "Map not found");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(pn, "read file fail");
            System.out.println(row+col);
        }
    }
    
    public void loadImageTile()
    {
            setup(10, "/tile/land", false,pn.tilesize,pn.tilesize);
            setup(11, "/tile/water", true,pn.tilesize,pn.tilesize);
            setup(12, "/tile/wall_horizontal1", true,pn.tilesize,pn.tilesize);
            setup(13, "/tile/wall_vertical1", true,pn.tilesize,pn.tilesize);
            setup(14, "/tile/house", true,pn.tilesize*4,pn.tilesize*2);
            setup(15, "/tile/tree", true,pn.tilesize,pn.tilesize);
            setup(16, "/tile/wall_horizontal2", true,pn.tilesize,pn.tilesize);
            setup(17, "/tile/wall_vertical2", true,pn.tilesize,pn.tilesize);
            setup(18, "/tile/grass", false,pn.tilesize,pn.tilesize);
            setup(19, "/tile/wall", true,pn.tilesize,pn.tilesize);
            setup(20, "/tile/inHouse",false,pn.tilesize,pn.tilesize);
            
//        type[0]=new Tile();
//        type[0].image=ImageIO.read(new File("land.png"));
//            
//        type[1]=new Tile();
//        type[1].colliction=true;
//        type[1].image=ImageIO.read(new File("water.png"));
//        
//        type[2]=new Tile();
//        type[2].colliction=true;
//        type[2].image=ImageIO.read(new File("wall_horizontal1.png"));
//        
//        type[3]=new Tile();
//        type[3].colliction=true;
//        type[3].image=ImageIO.read(new File("wall_vertical1.png"));
//        
//        type[4]=new Tile();
//        type[4].colliction=true;
//        type[4].image=ImageIO.read(new File("house.png"));
//        
//        type[5]=new Tile();
//        type[5].colliction=true;
//        type[5].image=ImageIO.read(new File("tree.png"));
//        
//        type[6]=new Tile();
//        type[6].colliction=true;
//        type[6].image=ImageIO.read(new File("wall_horizontal2.png"));
//        
//        type[7]=new Tile();
//        type[7].colliction=true;
//        type[7].image=ImageIO.read(new File("wall_vertical2.png"));
//                     
//        type[8]=new Tile();
//        type[8].image=ImageIO.read(new File("grass.png"));
//        
//        }
//        catch(IOException e)
//        {
//            JOptionPane.showMessageDialog(pn, "Load image tile fail");
//        }
    }
    
    public void setup(int index, String path, boolean collision,int width, int height)
    {
        utilityTool utool=new utilityTool();
        type[index]=new Tile();
        try {
            //type[index].image=ImageIO.read(new FileImageInputStream(new File(path+".png")));   
            
            type[index].image=ImageIO.read(getClass().getResourceAsStream(path+".png"));
        } catch (IOException ex) {
            System.out.println("Load image tile fail");
            ex.printStackTrace();
        }
        type[index].image=utool.scaleImage(type[index].image, width, height);
        type[index].colliction=collision;
    }
    
    public void draw(Graphics2D g2d)
    {

        int worldRow=0;
        int worldCol=0;
        
        while(worldRow<pn.maxWorldRow && worldCol<pn.maxWorldCol){  
            
            int worldX=worldCol * pn.tilesize;
            int worldY=worldRow * pn.tilesize;
            int screenX=worldX - pn.player.worldX + pn.player.screenX;
            int screenY=worldY - pn.player.worldY + pn.player.screenY;
            
            if(worldX + pn.tilesize > pn.player.worldX - pn.player.screenX && 
               worldX - pn.tilesize < pn.player.worldX + pn.player.screenX  &&
               worldY + pn.tilesize > pn.player.worldY - pn.player.screenY &&     
               worldY - pn.tilesize < pn.player.worldY + pn.player.screenY   
               )
            {
                g2d.drawImage(type[map[pn.currentMap][worldRow][worldCol]].image,screenX,screenY,pn.tilesize,pn.tilesize,null);
            }
            
            worldCol++;
           
            if(worldCol == pn.maxWorldCol)
            {
                worldCol=0;
                      
                worldRow++;
               
            }
        }     
    }
    
}
