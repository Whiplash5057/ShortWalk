package in.devdesk.shortwalk.Utilities;

import android.animation.ObjectAnimator;
import android.view.View;

import com.daasuu.ei.Ease;
import com.daasuu.ei.EasingInterpolator;

/**
 * Created by richardandrews on 01/07/17.
 */

public class AnimationClass {

    public void doBounceAnimation(View targetView, String translateDirection, int initialPosition, int finalPositoin) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(targetView, translateDirection, initialPosition, 25, finalPositoin);
        animator.setInterpolator(new EasingInterpolator(Ease.ELASTIC_IN_OUT));
        animator.setStartDelay(500);
        animator.setDuration(1500);
        animator.start();
    }
}
