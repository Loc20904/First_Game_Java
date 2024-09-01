

package Object;

import Pk_Main.Jpanel;
import entity.entity;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class OBJ_key extends entity {
    
    public OBJ_key(Jpanel pn)
    {       
        super(pn);
        type=type_comsumable;
        name="key";
        down1=setup("/Object/key", pn.tilesize,pn.tilesize);
        description="["+name+"]"+"\nCan use it to open the door";
    }
}
