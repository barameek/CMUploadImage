package com.codemobiles.cmuploadimage.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;

/**
 * Created by maboho_retina on 9/2/15 AD.
 */
public class AnimationUtil {

    public static void runDemo(Context context, View animatedView){
        // new animation style
        //float animDistance = context.getResources().getDimension(
        //        R.dimen.animation_distance);

        float animDistance = 300;

        ObjectAnimator oa1 = ObjectAnimator.ofFloat(animatedView, "rotation", 0, 2600);
        oa1.setDuration(2000);
        oa1.setRepeatCount(3000);


        ObjectAnimator oa2 = ObjectAnimator.ofFloat(animatedView, "x", 0, animDistance);
        oa2.setDuration(2000);
        oa2.setRepeatCount(10000);


        ObjectAnimator oa3 = ObjectAnimator.ofFloat(animatedView, "scaleX", 0, 1);
        oa3.setDuration(2000);
        oa3.setRepeatCount(10000);

        ObjectAnimator oa4 = ObjectAnimator.ofFloat(animatedView, "scaleY", 0, 1);
        oa3.setDuration(2000);
        oa3.setRepeatCount(10000);

        AnimatorSet animSetXY = new AnimatorSet();
        animSetXY.playTogether(oa1, oa2, oa3, oa4);
        //animSetXY.playSequentially(oa1, oa2);
        animSetXY.start();
    }
}
