package org.anm.phone.call;

import com.cisco.jtapi.extensions.CiscoCall;
import org.anm.phone.provider.PhoneProvider;

public interface PhoneCallBuilder {
    static PhoneCallBuilder create() {
        return PhoneCallBuilderImpl.create();
    }

    PhoneCallBuilder provider(PhoneProvider provider);

    PhoneCallBuilder from(String from);

    PhoneCallBuilder target(String target);

    CiscoCall build();
}
