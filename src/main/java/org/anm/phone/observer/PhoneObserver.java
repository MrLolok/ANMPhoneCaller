package org.anm.phone.observer;

import com.cisco.cti.util.Condition;

import javax.telephony.AddressObserver;
import javax.telephony.ProviderObserver;
import javax.telephony.TerminalObserver;
import javax.telephony.callcontrol.CallControlCallObserver;
import javax.telephony.events.AddrEv;
import javax.telephony.events.CallEv;
import javax.telephony.events.ProvEv;
import javax.telephony.events.TermEv;

public interface PhoneObserver extends ProviderObserver, TerminalObserver, AddressObserver, CallControlCallObserver {
    /**
     * Get the {@link Condition} set when the provider that use this observer
     * is changed {@link ProviderObserver#providerChangedEvent(ProvEv[])}
     * @return condition
     */
    Condition getProviderChanged();

    /**
     * Get the {@link Condition} set when the terminal that use this observer
     * is changed {@link TerminalObserver#terminalChangedEvent(TermEv[])}
     * @return condition
     */
    Condition getTerminalChanged();

    /**
     * Get the {@link Condition} set when the address that use this observer
     * is changed {@link AddressObserver#addressChangedEvent(AddrEv[])}
     * @return condition
     */
    Condition getAddressChanged();

    /**
     * Get the {@link Condition} set when the call that use this observer
     * is changed {@link CallControlCallObserver#callChangedEvent(CallEv[])}
     * @return condition
     */
    Condition getCallChanged();
}
