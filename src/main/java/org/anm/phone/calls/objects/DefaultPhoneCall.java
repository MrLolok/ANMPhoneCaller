package org.anm.phone.calls.objects;

import com.cisco.jtapi.extensions.CiscoCall;
import lombok.Data;
import org.anm.phone.log.LogLevel;
import org.anm.phone.log.Logger;
import org.anm.phone.observer.PhoneObserver;

import javax.telephony.*;
import java.util.function.Consumer;

@Data
public class DefaultPhoneCall implements PhoneCall {
    private final CiscoCall call;
    private final PhoneObserver observer;
    private final Terminal terminal;
    private final Address from;
    private final String target;

    @Override
    public void connect() {
        try {
            call.connect(terminal, from, target);
            Logger.log(LogLevel.SUCCESS, "Call connected successfully");
        } catch (ResourceUnavailableException | PrivilegeViolationException | InvalidPartyException |
                 InvalidArgumentException | InvalidStateException | MethodNotSupportedException e) {
            throw new RuntimeException("Unable to connect the call", e);
        }
    }

    @Override
    public void connect(Consumer<PhoneCall> consumer) {
        connect();
        observer.getCallChanged().waitTrue();
        consumer.accept(this);
    }

    @Override
    public void drop() {
        try {
            call.drop();
        } catch (InvalidStateException | MethodNotSupportedException | PrivilegeViolationException |
                 ResourceUnavailableException e) {
            throw new RuntimeException("Unable to drop the call", e);
        }
    }
}
