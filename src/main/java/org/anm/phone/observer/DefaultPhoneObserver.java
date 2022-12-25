package org.anm.phone.observer;

import com.cisco.cti.util.Condition;
import com.cisco.jtapi.extensions.CiscoAddrInServiceEv;
import com.cisco.jtapi.extensions.CiscoTermInServiceEv;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.telephony.events.*;

@NoArgsConstructor
public class DefaultPhoneObserver implements PhoneObserver {
    @Getter
    private final Condition providerChanged = new Condition(),
                            terminalChanged = new Condition(),
                            addressChanged = new Condition(),
                            callChanged = new Condition();

    @Override
    public void providerChangedEvent(ProvEv[] events) {
        for (ProvEv event : events)
            if (event.getID() == ProvInServiceEv.ID) {
                providerChanged.set();
                break;
            }
    }

    @Override
    public void terminalChangedEvent(TermEv[] events) {
        for (TermEv event : events)
            if (event.getID() == CiscoTermInServiceEv.ID) {
                terminalChanged.set();
                break;
            }
    }

    @Override
    public void addressChangedEvent(AddrEv[] events) {
        for (AddrEv event : events)
            if (event.getID() == CiscoAddrInServiceEv.ID) {
                addressChanged.set();
                break;
            }
    }

    @Override
    public void callChangedEvent(CallEv[] events) {
        for (CallEv event : events)
            if (event.getID() == CallActiveEv.ID) {
                callChanged.set();
                break;
            }
    }
}
