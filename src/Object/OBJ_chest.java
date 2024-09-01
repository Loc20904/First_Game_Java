

package Object;

import Pk_Main.Jpanel;
import entity.entity;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class OBJ_chest extends entity {

    public OBJ_chest(Jpanel pn)
    {
        super(pn);
        name="chest";
        down1=setup("/Object/chest", pn.tilesize,pn.tilesize);
        
    }
}
