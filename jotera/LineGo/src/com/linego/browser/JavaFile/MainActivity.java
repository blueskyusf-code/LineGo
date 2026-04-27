package com.linego.browser;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.linego.browser.engine.TeraEngine;

public class MainActivity extends AppCompatActivity {
    private TeraEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // Fullscreen para imersão total no LineGo
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                           WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inicializa o Motor Tera
        engine = new TeraEngine(this);
        setContentView(engine.getView());

        // Carrega a Home da Soberania (pode ser o seu buscador)
        engine.loadUrl("https://duckduckgo.com");
    }

    @Override
    public void onBackPressed() {
        if (engine.canGoBack()) {
            engine.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
