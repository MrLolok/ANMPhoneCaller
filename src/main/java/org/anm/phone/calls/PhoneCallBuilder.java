package org.anm.phone.calls;

import org.anm.phone.calls.objects.PhoneCall;
import org.anm.phone.provider.PhoneProviderBuilder;
import org.anm.phone.provider.objects.PhoneProvider;

public interface PhoneCallBuilder {
    /**
     * Create and return a new instance of {@link PhoneCallBuilderImpl}
     * @return new builder instance
     */
    static PhoneCallBuilder create() {
        return PhoneCallBuilderImpl.create();
    }

    /**
     * Set the provider to use to build a new call from
     * this {@link PhoneProviderBuilder}
     * @param provider to set
     * @return same builder instance
     */
    PhoneCallBuilder provider(PhoneProvider provider);

    /**
     * Set the address where the call come from
     * @param from address to set
     * @return same builder instance
     */
    PhoneCallBuilder from(String from);

    /**
     * Set the target address where the call is directed to
     * @param target address to set
     * @return same builder instance
     */
    PhoneCallBuilder target(String target);

    /**
     * Build a new {@link PhoneCall} with the information
     * provided before
     * @return new Cisco call
     */
    PhoneCall build();
}
