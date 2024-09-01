
package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_Mana extends entity {

    public OBJ_Mana(Jpanel pn) {
        super(pn);
        getImageMana(); 
    }

    
    public void getImageMana()
    {
        image=setup("/Object/mana_full", pn.tilesize, pn.tilesize/2);
        image2=setup("/Object/mana_half", pn.tilesize, pn.tilesize/2);
        image3=setup("/Object/mana_blank", pn.tilesize, pn.tilesize/2);
    }
}
