
package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_coin extends entity {

    public OBJ_coin(Jpanel pn) {
        super(pn);
        getImage();
        name=" 1 coin";
    }

    public void getImage()
    {
        down1=setup("/Object/coin", pn.tilesize,pn.tilesize);
    }
}
