package org.anm.phone.provider;

import org.anm.phone.config.defaults.ConfigCUCMCredentials;
import org.anm.phone.provider.objects.PhoneProvider;

public interface PhoneProviderBuilder {
    /**
     * Create and return a new instance of {@link PhoneProviderBuilderImpl}
     * @return new builder instance
     */
    static PhoneProviderBuilder create() {
        return PhoneProviderBuilderImpl.create();
    }

    /**
     * Set the address of the provider of the {@link PhoneProviderBuilder}
     * @param address to set
     * @return same builder instance
     */
    PhoneProviderBuilder address(String address);

    /**
     * Set the username of the provider of the {@link PhoneProviderBuilder}
     * @param username to set
     * @return same builder instance
     */
    PhoneProviderBuilder username(String username);

    /**
     * Set the password of the provider of the {@link PhoneProviderBuilder}
     * @param password to set
     * @return same builder instance
     */
    PhoneProviderBuilder password(String password);

    /**
     * Set the address, username and password of the provider of the
     * {@link PhoneProviderBuilder} from a config {@link ConfigCUCMCredentials}
     * @param config to use to obtain credentials
     * @return same builder instance
     */
    PhoneProviderBuilder credentials(ConfigCUCMCredentials config);

    /**
     * Build a new {@link PhoneProvider} with the credentials
     * provided before
     * @return new provider
     */
    PhoneProvider build();
}
