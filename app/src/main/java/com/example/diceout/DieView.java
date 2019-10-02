package com.example.diceout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.io.IOException;
import java.io.InputStream;

public class DieView extends android.support.v7.widget.AppCompatImageView {

    private int cardvalue,colorvalue;
    private Context context;
    private String cards[] = {"ace","two","three","four","five","six","seven","eight","nine","ten","jack","queen","king"};
    private String color[] = {"clubs","diamonds","hearts","spades"};
    public DieView(Context context) {
        super(context);
        init(context);
    }

    public DieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setImageResource(R.drawable.black_joker);
        this.context = context;
    }

    public void roll() {
        cardvalue = (int) (Math.random() * 13);
        colorvalue = (int) (Math.random() * 4);
        try {
            InputStream stream = context.getAssets().open(cards[cardvalue]+"_of_"+color[colorvalue]+".png");
            Drawable d = Drawable.createFromStream(stream,null);
            setImageDrawable(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue() {
        return cards[cardvalue];
    }
}
