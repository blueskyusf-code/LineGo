package com.linego.browser;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.linego.browser.engine.TeraEngine;

/**
 * LineGo Browser - MainActivity
 * Estrutura Raiz: LineGo/src/com/linego/browser/
 */
public class MainActivity extends Activity {
    
    private TeraEngine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Configurações de soberania visual (Tela Cheia)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
                           WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // 2. Infla o layout (O Android vai buscar em res/layout/main.xml ou activity_main.xml)
        setContentView(R.layout.main);

        // 3. Inicializa o Motor Tera
        engine = new TeraEngine(this);

        // 4. Injeta o motor no container definido no XML
        // Como a estrutura é direta, certifique-se de que o ID no XML seja 'tera_container'
        FrameLayout container = (FrameLayout) findViewById(R.id.tera_container);
        if (container != null) {
            container.addView(engine.getView());
        }

        // 5. Boot do sistema de navegação
        engine.loadUrl("https://duckduckgo.com");
    }

    @Override
    public void onBackPressed() {
        if (engine != null && engine.canGoBack()) {
            engine.goBack();
        } else {
            finish(); // Fecha a atividade se não houver mais histórico
        }
    }
}

