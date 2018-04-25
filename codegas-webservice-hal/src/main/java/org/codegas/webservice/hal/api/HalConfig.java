package org.codegas.webservice.hal.api;

import java.util.Collections;
import java.util.Map;

public final class HalConfig {

    private final Map<String, String> curies;

    private final String baseHref;

    public HalConfig() {
        this(Collections.emptyMap(), "");
    }

    public HalConfig(Map<String, String> curies, String baseHref) {
        this.curies = curies;
        this.baseHref = baseHref;
    }

    public Map<String, String> getCuries() {
        return curies;
    }

    public String getBaseHref() {
        return baseHref;
    }
}
