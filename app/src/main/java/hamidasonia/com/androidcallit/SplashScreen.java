package hamidasonia.com.androidcallit;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SplashScreen extends Activity {
    public void onAttachedToWindow(){
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);

    }
    Thread splashTread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        StartAnimation();
    }
    private void StartAnimation(){
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById (R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById (R.id.splash);
        iv.clearAnimation();
        iv.startAnimation(anim);


        splashTread = new Thread(){
            @Override
            public void run(){
                try {
                    int waited = 0;
//Splashscreen pause time
                    while(waited<4000){
                        sleep(100);
                        waited+=100;
                    }
                    Intent intent = new Intent(SplashScreen.this,
                            IntroActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();
                }catch(InterruptedException e){
// do nothing
                }finally{
                    SplashScreen.this.finish();
                }
            }
        };
        splashTread.start();

    }

}

