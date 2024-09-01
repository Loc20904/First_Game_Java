
package Object;

import Pk_Main.Jpanel;
import entity.entity;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class OBJ_Heart extends entity{

    public OBJ_Heart(Jpanel pn)
    {       
        super(pn);
        name="heart";        
        image3=setup("/Object/heart_blank", pn.tilesize,pn.tilesize);
        image2=setup("/Object/heart_half", pn.tilesize,pn.tilesize);
        image=setup("/Object/heart_full", pn.tilesize,pn.tilesize);
        
    }
}
