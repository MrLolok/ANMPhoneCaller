package org.anm.phone.log;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LogLevel {
    INFO("", Logger.ANSI_WHITE),
    SUCCESS("", Logger.ANSI_GREEN),
    PRIMARY("", Logger.ANSI_CYAN),
    WARN("WARN", Logger.ANSI_YELLOW),
    ERROR("ERROR", Logger.ANSI_RED);

    @Getter
    private final String prefix, color;
}
