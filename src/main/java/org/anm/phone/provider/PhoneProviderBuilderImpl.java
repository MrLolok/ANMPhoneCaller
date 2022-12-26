package org.anm.phone.provider;

import com.cisco.jtapi.extensions.CiscoJtapiPeer;
import com.cisco.jtapi.extensions.CiscoProvider;
import org.anm.phone.config.defaults.ConfigCUCMCredentials;
import org.anm.phone.observer.PhoneObserver;
import org.anm.phone.observer.DefaultPhoneObserver;
import org.anm.phone.provider.objects.DefaultPhoneProvider;
import org.anm.phone.provider.objects.PhoneProvider;

import javax.telephony.JtapiPeerUnavailableException;
import javax.telephony.JtapiPeerFactory;
import javax.telephony.ResourceUnavailableException;
import javax.telephony.MethodNotSupportedException;

public class PhoneProviderBuilderImpl implements PhoneProviderBuilder {
    private final CiscoJtapiPeer peer;
    private String address, username = "", password = "";

    private PhoneProviderBuilderImpl() throws JtapiPeerUnavailableException {
        this.peer = (CiscoJtapiPeer) JtapiPeerFactory.getJtapiPeer(null);
    }

    protected static PhoneProviderBuilderImpl create() {
        try {
            return new PhoneProviderBuilderImpl();
        } catch (JtapiPeerUnavailableException e) {
            throw new RuntimeException("Unable to create a new PhoneProviderBuilder", e);
        }
    }

    @Override
    public PhoneProviderBuilder address(String address) {
        this.address = address;
        return this;
    }

    @Override
    public PhoneProviderBuilder username(String username) {
        this.username = username;
        return this;
    }

    @Override
    public PhoneProviderBuilder password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public PhoneProviderBuilder credentials(ConfigCUCMCredentials config) {
        this.address = config.getAddress();
        this.username = config.getUsername();
        this.password = config.getPassword();
        return this;
    }

    @Override
    public PhoneProvider build() {
        if (address == null || address.isBlank())
            throw new IllegalStateException("Provider's address could not be null");

        PhoneObserver observer = new DefaultPhoneObserver();
        CiscoProvider provider = (CiscoProvider) peer.getProvider(String.format("%s;login=%s;passwd=%s", address, username, password));
        try {
            provider.addObserver(observer);
        } catch (ResourceUnavailableException | MethodNotSupportedException e) {
            throw new RuntimeException("Unable to add phone observer to the provider", e);
        }
        observer.getProviderChanged().waitTrue();
        return new DefaultPhoneProvider(provider, observer);
    }
}
