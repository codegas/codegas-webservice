package org.codegas.webservice.hal.api;

public interface HalRepresentationFactory {

    HalRepresentation createFor(Object resource);

    HalRepresentation createForLinks(Iterable<HalLink> links);
}
