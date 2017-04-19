package sumeshgames.android.adapterstuff;

import static java.lang.Boolean.FALSE;

/**
 * Created by Sumesh on 25-10-2016.
 */

public class Item_obj

{
    public boolean done;
   public String name;
    public boolean bought;

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }

    public Item_obj(String name, boolean done) {
        this.done = done;
        this.name = name;
        bought=FALSE;
    }

    public Item_obj( String name) {
        this.done =FALSE;
        this.name = name;
        bought=FALSE;
    }
    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
