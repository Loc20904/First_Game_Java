

package entity;

import Pk_Main.Jpanel;


public class projecttile extends entity {

    entity user;
    
    public projecttile(Jpanel pn) {
        super(pn);
        maxlife=5;
    }

    public void set(int worldX, int worldY,String direction, boolean alive, entity user){
    
        this.worldX=worldX;
        this.worldY=worldY;
        this.diredtion=direction;
        this.alive=alive;
        this.life=maxlife;
        this.user=user;

    }
    @Override
    public void update() {
        switch (diredtion) {
            case "up": {
                worldY -= speed;
                break;
            }
            case "down": {
                worldY += speed;
                break;
            }
            case "left": {
                worldX -= speed;
                break;
            }
            case "right": {
                worldX += speed;
                break;
            }
        }

        if(life<=0)
        {
            alive=false;
        }
        
        strikeCount++;
        if(strikeCount>12)
        {
            life--;
            if(strikeNum==1)
                strikeNum=2;
            else
                strikeNum=1;
            strikeCount=0;
        }
        
        int index=pn.cCheck.checkEntity(this,pn.monster);
        
        if(index!=999)
        {
            pn.player.dameMonster(index,attack,true);
            alive=false;
        }
            
    }
}
