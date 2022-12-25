package org.anm.phone.provider;

import org.anm.phone.config.defaults.ConfigCUCMCredentials;

public interface PhoneProviderBuilder {
    static PhoneProviderBuilder create() {
        return PhoneProviderBuilderImpl.create();
    }

    PhoneProviderBuilder address(String address);

    PhoneProviderBuilder username(String username);

    PhoneProviderBuilder password(String password);

    PhoneProviderBuilder credentials(ConfigCUCMCredentials config);

    PhoneProvider build();
}
