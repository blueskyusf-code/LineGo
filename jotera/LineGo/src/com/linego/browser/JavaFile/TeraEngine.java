package com.linego.browser.engine;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TeraEngine {
    private WebView webView;

    public TeraEngine(Context context) {
        webView = new WebView(context);
        setupSettings();
        setupSovereignty();
    }

    private void setupSettings() {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); // O motor Tera precisa de JS para as extensões
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        
        // Identidade LineGo (User Agent customizado se quiser)
        settings.setUserAgentString("LineGo/1.0 (Tera Engine; Soberania Digital)");
    }

    private void setupSovereignty() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // Aqui o motor injeta o yusr.h transformado em JS ou as extensões do usuário
                injectUserExtensions();
                super.onPageFinished(view, url);
            }
        });
    }

    private void injectUserExtensions() {
        // Lógica para ler a pasta /jotera/extensions/user e injetar no DOM
        // Por enquanto, apenas um log de proteção
        webView.evaluateJavascript("console.log('Motor Tera Ativo: Protegendo sua navegação.');", null);
    }

    public WebView getView() { return webView; }
    public void loadUrl(String url) { webView.loadUrl(url); }
    public boolean canGoBack() { return webView.canGoBack(); }
    public void goBack() { webView.goBack(); }
}
