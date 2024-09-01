

package entity;

import Pk_Main.Jpanel;


public class NPC_Cot extends entity {

    public NPC_Cot(Jpanel pn) {
        super(pn);
        speed=0;
        diredtion="down";
        soliArea.x=0;
        soliArea.y=10;
        soliAreaDefaultX=soliArea.x;
        soliAreaDefaultY=soliArea.y;
        soliArea.width=36;
        soliArea.height=26;
        setImage();
        setMess();
    }

    public void setImage()
    {
        down2 = setup("/npc/test1", pn.tilesize,pn.tilesize);
        down1 = setup("/npc/test2", pn.tilesize,pn.tilesize);
        
    }
    
    public void setMess()
    {
        dialogMes[0]="Tao là Cọt";
        dialogMes[1]="Mọi người còn gọi tao là Cán Bộ tập gym :D";
        dialogMes[2]="Nếu muốn tăng sức mạnh thì có thể đến gặp tao";
    }
    
    @Override
    public void speek()
    {
        if(dialogMes[messIndex]==null)
            messIndex=0;
        pn.ui.dialogMess=dialogMes[messIndex];
        messIndex++;
        switch(pn.player.diredtion)
        {
            case "up":diredtion="down";break;
            case "down":diredtion="up";break;
            case "left":diredtion="right";break;
            case "right":diredtion="left";     
        }
    }
}
