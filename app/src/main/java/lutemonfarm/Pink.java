package lutemonfarm;

import com.example.lutemonit.R;

public class Pink extends Lutemon{
    public Pink(String name, String color) {
        super(name, color, 7, 2, 0, 18);
        setPicture();
    }
    public int getMaxHealth() {
        return 18;
    }

    public void setPicture() {
        this.pic = R.drawable.pink;
    }
}

