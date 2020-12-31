package com.example.casinoroyale;

import android.animation.Animator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Random;

public class ImageViewScrolling extends FrameLayout {

    ImageView current_image, next_image;
    IEventEnd eventEnd;

    public ImageViewScrolling(Context context) {
        super(context);
        init(context);
    }

    public ImageViewScrolling(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.image_view_scrolling, this);
        current_image = getRootView().findViewById(R.id.current_img);
        next_image = getRootView().findViewById(R.id.next_img);
        next_image.setTranslationY(getHeight());
    }

    public void setEventEnd(IEventEnd eventEnd) {
        this.eventEnd = eventEnd;
    }

    public void spinReel() {
        current_image.animate().translationY(getHeight()).setDuration(Common.ANIMATION_DUR).start();
        next_image.setTranslationY(-next_image.getHeight());
        next_image.animate().translationY(0)
                .setDuration(Common.ANIMATION_DUR)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {}
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        System.out.print(""+getCurrValue());
                        current_image.setTranslationY(0);
                        setImage("current", getNextValue());
                    }
                    @Override
                    public void onAnimationCancel(Animator animation) {}
                    @Override
                    public void onAnimationRepeat(Animator animation) {}
                });
    }

    public void setImage(String image_name, int value) {
        ImageView image_view;
        if (image_name.equals("current")) {
            image_view = current_image;
        } else {
            image_view = next_image;
        }
        if (value == Util.ACE)
            image_view.setImageResource(R.drawable.ace);
        else if (value == Util.KING)
            image_view.setImageResource(R.drawable.king);
        else if (value == Util.QUEEN)
            image_view.setImageResource(R.drawable.queen);
        else if (value == Util.JACK)
            image_view.setImageResource(R.drawable.jack);
        else if (value == Util.TEN)
            image_view.setImageResource(R.drawable.ten);
        else if (value == Util.HEART)
            image_view.setImageResource(R.drawable.heart);
        else if (value == Util.DIAMOND)
            image_view.setImageResource(R.drawable.diamond);
        else if (value == Util.CLUB)
            image_view.setImageResource(R.drawable.club);
        else if (value == Util.SPADE)
            image_view.setImageResource(R.drawable.spade);
        else if (value == Util.JOKER)
            image_view.setImageResource(R.drawable.joker);
        image_view.setTag(value);
    }

    public int getCurrValue() {
        return Integer.parseInt(current_image.getTag().toString());
    }

    public int getNextValue() {
        return Integer.parseInt(next_image.getTag().toString());
    }

}
