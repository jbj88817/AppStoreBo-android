package us.bojie.appstorebo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.eftimoff.androipathview.PathView;

import butterknife.BindView;
import butterknife.ButterKnife;
import us.bojie.appstorebo.R;

public class WelcomeActivity extends AppCompatActivity {

    @BindView(R.id.pathView)
    PathView mPathView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        mPathView.getPathAnimator()
                .delay(100)
                .duration(3000)
                .listenerEnd(new PathView.AnimatorBuilder.ListenerEnd() {
                    @Override
                    public void onAnimationEnd() {
                        jump();
                    }
                })
                .interpolator(new AccelerateDecelerateInterpolator())
                .start();
    }

    private void jump() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
