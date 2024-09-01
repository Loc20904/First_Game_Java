
package Object;

import Pk_Main.Jpanel;
import entity.entity;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class OBJ_door extends entity{

    public OBJ_door(Jpanel pn)
    {
        super(pn);
        name="door";
        down1=setup("image\\Object\\door", pn.tilesize,pn.tilesize);
        collision=true;
        
        soliArea.x=0;
        soliArea.y=10;
        soliAreaDefaultX=soliArea.x;
        soliAreaDefaultY=soliArea.y;
        soliArea.width=36;
        soliArea.height=26;
    }
}
