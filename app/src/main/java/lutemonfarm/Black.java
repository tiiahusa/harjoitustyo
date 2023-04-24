package lutemonfarm;

import com.example.lutemonit.R;

public class Black extends Lutemon{
    public Black(String name, String color) {
        super(name, color, 9, 0, 0, 16);
        setPicture();
    }

    public int getMaxHealth() {
        return 16;
    }

    public void setPicture() {
        this.pic = R.drawable.black;
    }
}

