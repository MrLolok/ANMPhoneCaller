package org.anm.phone;

import com.cisco.jtapi.extensions.CiscoCall;
import org.anm.phone.config.Config;
import org.anm.phone.config.defaults.ConfigCUCMCredentials;
import org.anm.phone.provider.PhoneProvider;
import org.anm.phone.call.PhoneCallBuilder;
import org.anm.phone.provider.PhoneProviderBuilder;

import javax.telephony.*;

public class ANMPhoneCaller {
    public static void main(String[] args) {
        ConfigCUCMCredentials config = Config.load("cisco_config", ConfigCUCMCredentials.class).getConfig();
        PhoneProvider provider = PhoneProviderBuilder.create().credentials(config).build();
        CiscoCall call = PhoneCallBuilder.create().provider(provider).from("").target("").build();

        try {
            Thread.sleep(5000);
            call.drop();
        } catch (InterruptedException | InvalidStateException | MethodNotSupportedException |
                 PrivilegeViolationException | ResourceUnavailableException e) {
            e.printStackTrace();
        }
    }
}