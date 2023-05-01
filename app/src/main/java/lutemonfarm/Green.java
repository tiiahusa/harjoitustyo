package lutemonfarm;

import com.example.lutemonit.R;

import java.io.Serializable;

public class Green extends Lutemon implements Serializable {
    public Green(String name, String color) {
        super(name, color, 6, 3, 0, 19);
        setPicture();
    }

    public int getMaxHealth() {
        return 19;
    }

    public void setPicture() {
        this.pic = R.drawable.green;
    }
}
