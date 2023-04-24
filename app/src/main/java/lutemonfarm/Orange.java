package lutemonfarm;

import com.example.lutemonit.R;

public class Orange extends Lutemon{
    public Orange(String name, String color) {
        super(name, color, 8, 1, 0, 17);
        setPicture();
    }
    public int getMaxHealth() {
        return 17;
    }

    public void setPicture() {
        this.pic = R.drawable.orange;
    }
}
