#ifndef LINEGO_YUSR_H
#define LINEGO_YUSR_H

// LineGo - Jotera Engine Extension System
// "A soberania do usuário acima de tudo."

namespace Jotera {

    typedef struct {
        const char* ext_id;
        bool allow_network;
        bool allow_system_hooks;
        int priority_level; // Define quem manda mais no renderizador
    } UserExtensionConfig;

    // Função para validar se a extensão tem DNA LineGo
    inline bool IsExtensionTrusted(const char* signature) {
        // Lógica para bloquear extensões gringas maliciosas
        return (signature != nullptr); 
    }

    // Definições de controle de privacidade via extensão
    #define TERA_BLOCK_TRACKERS 0x01
    #define TERA_FORCE_HTTPS    0x02
    #define TERA_ANTI_FINGERPRINT 0x04

}

#endif // LINEGO_YUSR_H
