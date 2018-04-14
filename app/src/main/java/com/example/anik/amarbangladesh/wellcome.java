package com.example.anik.amarbangladesh;

        import android.content.Intent;
        import android.os.Handler;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Window;
        import android.view.WindowManager;
        import android.widget.TextView;

public class wellcome extends AppCompatActivity {

//    private static int SPLASH_TIME_OUT = 4000;
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        ActionBar actionBar=getSupportActionBar();
        actionBar.hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent wellcomeIntent = new Intent(wellcome.this, MainActivity.class);
                startActivity(wellcomeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }*/

    private int SLEEP_TIMER = 3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_wellcome);
        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(1000 * SLEEP_TIMER);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(wellcome.this, MainActivity.class);
            startActivity(intent);
            wellcome.this.finish();
        }
    }


}
