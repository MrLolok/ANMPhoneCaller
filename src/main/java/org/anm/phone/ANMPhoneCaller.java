package org.anm.phone;

import org.anm.phone.calls.objects.PhoneCall;
import org.anm.phone.config.Config;
import org.anm.phone.config.defaults.ConfigCUCMCredentials;
import org.anm.phone.log.LogLevel;
import org.anm.phone.log.Logger;
import org.anm.phone.provider.objects.PhoneProvider;
import org.anm.phone.calls.PhoneCallBuilder;
import org.anm.phone.provider.PhoneProviderBuilder;

import java.util.Scanner;

/**
 * Classe di prova per eseguire una semplice chiamata
 * telefonica utilizzando le JTAPI di Cisco
 * NB:  Per far si che le chiamate vengano avviate correttamente,
 *      di aver configurato con le giuste credenzaili il file di
 *      configurazione "cisco_credentials.json"
 */
public class ANMPhoneCaller {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Logger.log(LogLevel.INFO, "Inserisci l'indirizzo (o numero di telefono) da cui parte la chiamata: ");
        String from = scanner.nextLine();

        Logger.log(LogLevel.INFO, "Inserisci il numero di telefono da chiamare: ");
        String target = scanner.nextLine();

        Logger.log(LogLevel.INFO, "Esecuzione chiamata...");
        executeCall(from, target);
    }

    private static void executeCall(String from, String to) {
        ConfigCUCMCredentials config = Config.load("cisco_credentials", ConfigCUCMCredentials.class).getConfig();
        PhoneProvider provider = PhoneProviderBuilder.create().credentials(config).build();
        PhoneCall call = PhoneCallBuilder.create().provider(provider).from(from).target(to).build();
        call.connect(phonecall -> {
            try {
                Thread.sleep(5000);
                phonecall.drop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}