package org.anm.phone.observer;

import com.cisco.cti.util.Condition;

import javax.telephony.AddressObserver;
import javax.telephony.ProviderObserver;
import javax.telephony.TerminalObserver;
import javax.telephony.callcontrol.CallControlCallObserver;

public interface PhoneObserver extends ProviderObserver, TerminalObserver, AddressObserver, CallControlCallObserver  {
    Condition getProviderChanged();

    Condition getTerminalChanged();

    Condition getAddressChanged();

    Condition getCallChanged();
}
