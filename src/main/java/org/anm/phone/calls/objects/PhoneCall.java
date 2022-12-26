package org.anm.phone.calls.objects;

import com.cisco.jtapi.extensions.CiscoCall;
import org.anm.phone.observer.PhoneObserver;

import javax.telephony.Address;
import javax.telephony.Terminal;
import java.util.function.Consumer;

public interface PhoneCall {
    /**
     * Obtain the {@link CiscoCall} of this call instance
     * @return phone observer
     */
    CiscoCall getCall();

    /**
     * Obtain the {@link PhoneObserver} of this call instance
     * @return phone observer
     */
    PhoneObserver getObserver();

    /**
     * Obtain the {@link Terminal} used for this call
     * @return phone observer
     */
    Terminal getTerminal();

    /**
     * Obtain the {@link Address} where the call come from
     * @return phone observer
     */
    Address getFrom();

    /**
     * Obtain the target address where the call is directed to
     * @return phone observer
     */
    String getTarget();

    /**
     * Connect the call between {@link PhoneCall#getFrom()} and {@link PhoneCall#getTarget()}
     * using {@link CiscoCall#connect(Terminal, Address, String)}
     */
    void connect();

    /**
     * Connect the call using {@link PhoneCall#connect()} and execute a consumer
     * when the call connects successfully
     * @param consumer to execute when the call is running
     */
    void connect(Consumer<PhoneCall> consumer);

    /**
     * Drop the call {@link CiscoCall#drop()}
     */
    void drop();
}
