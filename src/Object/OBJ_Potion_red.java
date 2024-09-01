
package Object;

import Pk_Main.Jpanel;
import entity.entity;


public class OBJ_Potion_red extends entity {

    public OBJ_Potion_red(Jpanel pn) {
        super(pn);
        type=type_comsumable;
        life=5;
        name="Red Potion";
        description="["+name+"]"+"\nCan be use to heal 3 life";
        down1=setup("/Object/potion_red", pn.tilesize, pn.tilesize);
    }

    @Override
    public void useItem(entity entity)
    {
        pn.state=pn.gameDinalogMess;
        pn.ui.dialogMess=String.format("%s had heal 5 life!!!", entity.name);
        pn.ui.drawDinalogMess();
        entity.life+=life;
        if(entity.life>entity.maxlife)
        {
            entity.life=entity.maxlife;
        }
        
    }
}
