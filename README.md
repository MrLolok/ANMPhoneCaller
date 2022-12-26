# ANM Phone Caller

**ANM Phone Caller** è un software scritto in Java che offre la possibilità agli amministratori in ufficio, di effetturare chiamate telefoniche rapide agli autisti, tramite una rubrica.
> **NB:** Affinché il programma funzioni correttamente, è necessario configurare il file "cisco_credentials.json" inserendo le credenziali corrette per usufruire delle funzionalità del telefono desiderato

**Attenzione!** Questa è solo una versione di prova da cui è possibile effettuare chiamate solo tramite prompt dei comandi inserendo il numero manualmente. Una volta verificata la possibilità di collegare i dispositivi desiderati al software, sarà avviato lo sviluppo di un'interfaccia grafica per la rubrica e tutte le funzionalità ad essa relative

**Tecnologie utilizzate:**
 1. Cisco JTAPI - Java Telephony API (versione 14 della Cisco) per interfacciarsi agli IP Phone Cisco
 2. Jackson ObjectMapper - Deserializza i file di configurazione JSON
 3. Lombok - Annotation processor
