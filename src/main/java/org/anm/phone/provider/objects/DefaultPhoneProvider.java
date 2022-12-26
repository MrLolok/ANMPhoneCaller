package org.anm.phone.provider.objects;

import com.cisco.jtapi.extensions.CiscoProvider;
import lombok.Data;
import org.anm.phone.observer.PhoneObserver;

@Data
public class DefaultPhoneProvider implements PhoneProvider {
    private final CiscoProvider provider;
    private final PhoneObserver observer;
}
