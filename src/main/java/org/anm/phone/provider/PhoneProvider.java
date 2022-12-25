package org.anm.phone.provider;

import org.anm.phone.observer.PhoneObserver;

public interface PhoneProvider {
    PhoneObserver getObserver();

    com.cisco.jtapi.extensions.CiscoProvider getProvider();
}
