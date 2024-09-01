
package Pk_Main;

import java.awt.Rectangle;


public class EventHandler {

    public Jpanel pn;
    
    public Rectangle eventRec;
    public int defaultEventX,defaultEventY;
    
    
    public EventHandler(Jpanel pn)
    {
        this.pn=pn;
        eventRec=new Rectangle();
        eventRec.x=13;
        eventRec.y=13;
        eventRec.width=5;
        eventRec.height=5;
        defaultEventX=eventRec.x;
        defaultEventY=eventRec.y;
    }
    
    public void checkEvent()
    {
//        if(hit(38,36,"right")==true && pn.player.invisible==false)
//        {
//            pn.keyh.messCheckFrom=true;
//            dealdame(pn.gameDinalogMess,"You fall in hole");
//        }
        if(pn.stage==2)
        {
            if (hit(37, 31, "any")) {
                pn.player.attackCancel = true;
                healing(pn.gameDinalogMess);
            }
            if (hit(34, 28, "any")) {
                pn.keyh.messCheckFrom = true;
                teleport(22, 29, pn.gameDinalogMess);
            }
        }
    }
    
    public boolean hit(int eventrow,int eventcol, String recDirection)
    {
        boolean hit=false;
        
        pn.player.soliArea.x=pn.player.worldX + pn.player.soliArea.x;
        pn.player.soliArea.y=pn.player.worldY + pn.player.soliArea.y;
        
        eventRec.x=eventcol*pn.tilesize + eventRec.x;
        eventRec.y=eventrow*pn.tilesize + eventRec.y;
        
        if(pn.player.soliArea.intersects(eventRec))
        {
            if(pn.player.diredtion.equalsIgnoreCase(recDirection) || recDirection.equalsIgnoreCase("any"))
            {
                hit =true;
            }
        }       
        pn.player.soliArea.x=pn.player.soliAreaDefaultX;
        pn.player.soliArea.y=pn.player.soliAreaDefaultY;
        
        eventRec.x=defaultEventX;
        eventRec.y=defaultEventY;
        
        return hit;
    }
    
    public void dealdame(int gamestate,String mess)
    {
        pn.state=gamestate;
        pn.ui.dialogMess=mess;
        pn.player.invisible=true;
        pn.player.life--;
        if(pn.player.life<=0)
        {
            pn.state=pn.gameOver;
        }
    }
    
    public void healing(int gamestate)
    {
        if(pn.keyh.keyenter==true)
        {
            pn.state = gamestate;
            pn.keyh.messCheckFrom=true;
            pn.ui.dialogMess = "You had heling +2 life";
            pn.player.life +=2;
            pn.ast.setMonster();
            pn.player.worldY+=pn.tilesize;
        }     
    }
    
    public void teleport(int y,int x,int gamestate)
    {
        pn.state=gamestate;
        pn.ui.dialogMess="You had teleport";
        pn.currentMap=1;
        pn.player.worldX=x*pn.tilesize;
        pn.player.worldY=y*pn.tilesize;
    }
    
    
}
