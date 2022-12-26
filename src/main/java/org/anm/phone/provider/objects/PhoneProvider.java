package org.anm.phone.provider.objects;

import com.cisco.jtapi.extensions.CiscoProvider;
import org.anm.phone.observer.PhoneObserver;

public interface PhoneProvider {
    /**
     * Obtain the {@link CiscoProvider} of this
     * provider instance
     * @return Cisco provider
     */
    CiscoProvider getProvider();

    /**
     * Obtain the {@link PhoneObserver} of this
     * provider instance
     * @return phone observer
     */
    PhoneObserver getObserver();
}
