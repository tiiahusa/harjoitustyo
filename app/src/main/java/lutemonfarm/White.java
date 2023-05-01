package lutemonfarm;

import com.example.lutemonit.R;

import java.io.Serializable;

public class White extends Lutemon implements Serializable {
    public White(String name, String color) {
        super(name, color, 5, 4, 0, 20);
        setPicture();
    }
    public int getMaxHealth() {
        return 20;
    }

    public void setPicture() {
        this.pic = R.drawable.white;
    }
}

