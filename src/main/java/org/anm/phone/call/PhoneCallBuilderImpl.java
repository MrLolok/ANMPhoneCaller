package org.anm.phone.call;

import com.cisco.jtapi.extensions.CiscoCall;
import com.cisco.jtapi.extensions.CiscoProvider;
import org.anm.phone.observer.PhoneObserver;
import org.anm.phone.provider.PhoneProvider;

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
    public CiscoCall build() {
        if (provider == null)
            throw new IllegalStateException("Call's provider could not be null");
        if (from == null || from.isBlank() || target == null || target.isBlank())
            throw new IllegalStateException("Use a valid address and target");

        CiscoProvider provider = this.provider.getProvider();
        PhoneObserver observer = this.provider.getObserver();
        CiscoCall call;

        try {
            Address from = provider.getAddress(this.from);
            from.addObserver(observer);
            observer.getAddressChanged().waitTrue();
            from.addCallObserver(observer);

            Terminal terminal = from.getTerminals()[0];
            terminal.addObserver(observer);
            observer.getTerminalChanged().waitTrue();

            call = (CiscoCall) provider.createCall();
            call.connect(terminal, from, target);
            observer.getAddressChanged().waitTrue();
        } catch (InvalidArgumentException | InvalidPartyException | ResourceUnavailableException |
                 MethodNotSupportedException | PrivilegeViolationException | InvalidStateException e) {
            throw new RuntimeException("An error occurred while building a phone call", e);
        }
        return call;
    }
}
