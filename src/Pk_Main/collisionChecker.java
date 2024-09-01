

package Pk_Main;

import entity.entity;
import java.awt.Graphics;
import java.awt.Graphics2D;
import tile.Tile;


public class collisionChecker {

    Jpanel pn;
    
    public collisionChecker(Jpanel pn)
    {
        this.pn=pn;
    }
    
    public void checkTile(entity enti)
    {
        int entityLeftWorldX= enti.worldX + enti.soliArea.x;
        int entityRightWorlfX= enti.worldX + enti.soliArea.x + enti.soliArea.width;
        int entityBottonWorldY= enti.worldY + enti.soliArea.y + enti.soliArea.height;
        int entityTopWorldY= enti.worldY +enti.soliArea.y;
        
        int entityLeftCol=entityLeftWorldX / pn.tilesize;
        int entityRightCol=entityRightWorlfX/pn.tilesize;
        int entityTopRow=entityTopWorldY/pn.tilesize;
        int entityBottonRow=entityBottonWorldY/pn.tilesize;
        
        int tile1, tile2;
        
        switch(enti.diredtion)
        {
            case "up":
            {
                entityTopRow=(entityTopWorldY - enti.speed)/pn.tilesize;
                
                tile1=pn.tileMn.map[pn.currentMap][entityTopRow][entityLeftCol];
                tile2=pn.tileMn.map[pn.currentMap][entityTopRow][entityRightCol];
                                               
                if(pn.tileMn.type[tile1].colliction==true || pn.tileMn.type[tile2].colliction==true)
                {
                    enti.collisionOn=true;
                }
                break;
            }
            case "down":
            {
                entityBottonRow=(entityBottonWorldY+enti.speed)/pn.tilesize;
                tile1=pn.tileMn.map[pn.currentMap][entityBottonRow][entityLeftCol];
                tile2=pn.tileMn.map[pn.currentMap][entityBottonRow][entityRightCol];
                
                if(pn.tileMn.type[tile1].colliction==true || pn.tileMn.type[tile2].colliction==true)
                {
                    enti.collisionOn=true;
                }
                break;
            }
            case "left":
            {
                entityLeftCol=(entityLeftWorldX-enti.speed)/pn.tilesize;
                tile1=pn.tileMn.map[pn.currentMap][entityTopRow][entityLeftCol];
                tile2=pn.tileMn.map[pn.currentMap][entityBottonRow][entityLeftCol];
                
                if(pn.tileMn.type[tile1].colliction==true || pn.tileMn.type[tile2].colliction==true)
                {
                    enti.collisionOn=true;
                }
                break;
            }
            case "right": 
            {
                entityRightCol=(entityRightWorlfX + enti.speed)/pn.tilesize;
                tile1=pn.tileMn.map[pn.currentMap][entityTopRow][entityRightCol];
                tile2=pn.tileMn.map[pn.currentMap][entityBottonRow][entityRightCol];
                
                if(pn.tileMn.type[tile1].colliction==true || pn.tileMn.type[tile2].colliction==true)
                {
                    enti.collisionOn=true;
                }
                break;
            }
        }
        
    }
    
    public int checkObject(entity entity, boolean player)
    {
        int index=999;
        
        for(int i=0;i<pn.obj.length;i++)
        {
            if(pn.obj[pn.currentMap][i]!=null)
            {
                entity.soliArea.x=entity.soliArea.x +entity.worldX;
                entity.soliArea.y=entity.soliArea.y +entity.worldY;
                
                pn.obj[pn.currentMap][i].soliArea.x=pn.obj[pn.currentMap][i].soliArea.x+pn.obj[pn.currentMap][i].worldX;
                pn.obj[pn.currentMap][i].soliArea.y=pn.obj[pn.currentMap][i].soliArea.y+pn.obj[pn.currentMap][i].worldY;
                
                switch(entity.diredtion)
                {
                    case "up":
                    {
                        entity.soliArea.y-=entity.speed;
                        break;
                    }
                    case "down":
                    {
                        entity.soliArea.y+=entity.speed;
                        break;
                    }
                    case "left":
                    {
                        entity.soliArea.x-=entity.speed;
                        break;
                    }
                    case "right":
                    {
                        entity.soliArea.x+=entity.speed; 
                    }
                }
                if (entity.soliArea.intersects(pn.obj[pn.currentMap][i].soliArea)) {
                    if (pn.obj[pn.currentMap][i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                entity.soliArea.x=entity.soliAreaDefaultX;
                entity.soliArea.y=entity.soliAreaDefaultY;
                pn.obj[pn.currentMap][i].soliArea.x=pn.obj[pn.currentMap][i].soliAreaDefaultX;
                pn.obj[pn.currentMap][i].soliArea.y=pn.obj[pn.currentMap][i].soliAreaDefaultY;
            }
        }
        return index;
    }
    
    public int checkEntity(entity entity ,entity[][] taget)
    {
        int index=999;
        for (int i = 0; i < taget.length; i++) {
            if (taget[pn.currentMap][i] != null) {
                entity.soliArea.x = entity.soliArea.x + entity.worldX;
                entity.soliArea.y = entity.soliArea.y + entity.worldY;

                taget[pn.currentMap][i].soliArea.x = taget[pn.currentMap][i].soliArea.x + taget[pn.currentMap][i].worldX;
                taget[pn.currentMap][i].soliArea.y = taget[pn.currentMap][i].soliArea.y + taget[pn.currentMap][i].worldY;

                switch (entity.diredtion) {
                    case "up": {
                        entity.soliArea.y -= entity.speed;  
                        break;
                    }
                    case "down": {
                        entity.soliArea.y += entity.speed;
                        break;
                    }
                    case "left": {
                        entity.soliArea.x -= entity.speed; 
                        break;
                    }
                    case "right": {
                        entity.soliArea.x += entity.speed;   
                        break;
                    }
                }
                if (entity.soliArea.intersects(taget[pn.currentMap][i].soliArea)) {
                    if(taget[pn.currentMap][i]!=entity)
                    {
                        entity.collisionOn = true;
                        index = i;
                    }   
                }
                entity.soliArea.x = entity.soliAreaDefaultX;
                entity.soliArea.y = entity.soliAreaDefaultY;
                taget[pn.currentMap][i].soliArea.x = taget[pn.currentMap][i].soliAreaDefaultX;
                taget[pn.currentMap][i].soliArea.y = taget[pn.currentMap][i].soliAreaDefaultY;
            }
        }
        return index;
    }
    
    public boolean checkPlayer(entity entity)
    {
        boolean contactPlayer=false;
        entity.soliArea.x = entity.soliArea.x + entity.worldX;
        entity.soliArea.y = entity.soliArea.y + entity.worldY;

        pn.player.soliArea.x = pn.player.soliArea.x + pn.player.worldX;
        pn.player.soliArea.y = pn.player.soliArea.y + pn.player.worldY;

        switch (entity.diredtion) {
            case "up": {
                entity.soliArea.y -= entity.speed;
                break;
            }
            case "down": {
                entity.soliArea.y += entity.speed;
                break;
            }
            case "left": {
                entity.soliArea.x -= entity.speed;
                break;
            }
            case "right": {
                entity.soliArea.x += entity.speed;
                break;
            }
        }
        if (entity.soliArea.intersects(pn.player.soliArea)) {
            entity.collisionOn = true;
            contactPlayer=true;
        }
        entity.soliArea.x = entity.soliAreaDefaultX;
        entity.soliArea.y = entity.soliAreaDefaultY;
        pn.player.soliArea.x = pn.player.soliAreaDefaultX;
        pn.player.soliArea.y = pn.player.soliAreaDefaultY;
        return contactPlayer;
    }
}
