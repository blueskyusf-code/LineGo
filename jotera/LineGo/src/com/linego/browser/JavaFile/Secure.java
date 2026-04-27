package com.linego.browser;

import android.webkit.WebView;
import android.net.Uri;
import android.util.Log;

/**
 * Clean Wordface - Sistema de Identidade Espelhada
 * Cria uma sandbox onde cada site vê um reflexo único e falso do hardware.
 */
public class Secure {

    private static final String TAG = "LineGo_Secure";

    /**
     * O "Espelho": Gera e injeta uma identidade falsa baseada no domínio do site.
     * * @param view A WebView do Motor Tera
     * @param url A URL do site atual para gerar o reflexo único
     */
    public static void applyCleanWordface(WebView view, String url) {
        if (url == null || url.startsWith("file://")) return;

        String domain = getDomain(url);
        // Gera um ID único para o site, para que o "espelho" seja persistente 
        // apenas naquele domínio, mas diferente nos outros.
        String mirrorIdentity = Integer.toHexString(domain.hashCode());

        // O Script que reconstrói a realidade para o rastreador
        String script = "javascript:(function() {" +
                "try {" +
                "   var identity = 'LG-" + mirrorIdentity + "';" +
                "   /* Sobrescreve propriedades de fingerprinting */ " +
                "   Object.defineProperty(navigator, 'userAgent', {get:() => 'LineGo/1.0 (Tera Engine; Clean Wordface; ' + identity + ')'});" +
                "   Object.defineProperty(navigator, 'platform', {get:() => 'SovereignOS'});" +
                "   Object.defineProperty(navigator, 'vendor', {get:() => 'Jotera'});" +
                "   Object.defineProperty(navigator, 'deviceMemory', {get:() => 4});" +
                "   Object.defineProperty(navigator, 'hardwareConcurrency', {get:() => 2});" +
                "   " +
                "   /* Bloqueia acesso a sensores que revelam o modelo */ " +
                "   if(navigator.getBattery) { navigator.getBattery = () => Promise.resolve({level: 1, charging: true}); }" +
                "   " +
                "   console.log('Clean Wordface: Identidade Espelhada Ativa para " + domain + "');" +
                "} catch(e) { console.error('Erro no Escudo Secure:', e); }" +
                "})();";

        view.evaluateJavascript(script, null);
        Log.d(TAG, "Espelho gerado para: " + domain + " [ID: " + mirrorIdentity + "]");
    }

    /**
     * Extrai o domínio para garantir que o espelho seja fixo por site.
     */
    private static String getDomain(String url) {
        try {
            Uri uri = Uri.parse(url);
            String domain = uri.getHost();
            return domain != null ? domain : url;
        } catch (Exception e) {
            return "unknown";
        }
    }

    /**
     * Limpeza agressiva de rastro ao fechar abas ou trocar de contexto.
     */
    public static void wipeSovereignData(WebView view) {
        view.clearCache(true);
        view.clearFormData();
        view.clearHistory();
        Log.i(TAG, "Dados de sessão eliminados. Soberania preservada.");
    }
}
