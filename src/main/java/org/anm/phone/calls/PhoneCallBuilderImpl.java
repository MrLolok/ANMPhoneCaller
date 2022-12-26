package org.anm.phone.calls;

import com.cisco.jtapi.extensions.CiscoCall;
import com.cisco.jtapi.extensions.CiscoProvider;
import org.anm.phone.calls.objects.DefaultPhoneCall;
import org.anm.phone.calls.objects.PhoneCall;
import org.anm.phone.observer.PhoneObserver;
import org.anm.phone.provider.objects.PhoneProvider;

import javax.telephony.*;

public class PhoneCallBuilderImpl implements PhoneCallBuilder {
    private PhoneProvider provider;
    private String from, target;

    protected static PhoneCallBuilder create() {
        return new PhoneCallBuilderImpl();
    }

    @Override
    public PhoneCallBuilder provider(PhoneProvider provider) {
        this.provider = provider;
        return this;
    }

    @Override
    public PhoneCallBuilder from(String from) {
        this.from = from;
        return this;
    }

    @Override
    public PhoneCallBuilder target(String target) {
        this.target = target;
        return this;
    }

    @Override
    public PhoneCall build() {
        if (provider == null)
            throw new IllegalStateException("Call's provider could not be null");
        if (from == null || from.isBlank() || target == null || target.isBlank())
            throw new IllegalStateException("Use a valid address and target");

        CiscoProvider provider = this.provider.getProvider();
        PhoneObserver observer = this.provider.getObserver();
        PhoneCall call;

        try {
            Address from = provider.getAddress(this.from);
            from.addObserver(observer);
            observer.getAddressChanged().waitTrue();
            from.addCallObserver(observer);

            Terminal terminal = from.getTerminals()[0];
            terminal.addObserver(observer);
            observer.getTerminalChanged().waitTrue();

            CiscoCall ciscoCall = (CiscoCall) provider.createCall();
            ciscoCall.addObserver(observer);
            call = new DefaultPhoneCall(ciscoCall, observer, terminal, from, target);
        } catch (InvalidArgumentException | ResourceUnavailableException | MethodNotSupportedException | PrivilegeViolationException | InvalidStateException e) {
            throw new RuntimeException("An error occurred while building a phone call", e);
        }
        return call;
    }
}
