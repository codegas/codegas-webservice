package org.codegas.webservice.hal.representation;

import java.io.Writer;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;

import com.theoryinpractise.halbuilder.api.Contract;
import com.theoryinpractise.halbuilder.api.Link;
import com.theoryinpractise.halbuilder.api.ReadableRepresentation;
import com.theoryinpractise.halbuilder.api.Representable;
import com.theoryinpractise.halbuilder.api.Representation;

class HalRepresentationImpl implements HalRepresentation, Representation {

    private final Representation delegate;

    private final String contentType;

    private final String hrefPrefix;

    public HalRepresentationImpl(Representation delegate, String contentType, String hrefPrefix) {
        this.delegate = delegate;
        this.contentType = contentType;
        this.hrefPrefix = hrefPrefix;
    }

    public HalRepresentation withLinks(Iterable<HalLink> halLinks) {
        for (HalLink halLink : halLinks) {
            withLink(halLink);
        }
        return this;
    }

    public HalRepresentation withLink(HalLink halLink) {
        return withLink(halLink.getRel(), halLink.getHref());
    }

    public HalRepresentation withEmbeddeds(String rel, Iterable<HalRepresentation> halResources) {
        for (HalRepresentation halRepresentation : halResources) {
            withEmbedded(rel, halRepresentation);
        }
        return this;
    }

    public HalRepresentation withEmbedded(String rel, HalRepresentation halRepresentation) {
        if (halRepresentation instanceof ReadableRepresentation) {
            return withRepresentation(rel, ReadableRepresentation.class.cast(halRepresentation));
        }

        throw new IllegalArgumentException("I don't know how to represent this Embedded HAL Resource: " + halRepresentation);
    }

    @Override
    public String toString() {
        return toString(contentType);
    }

    @Override
    public HalRepresentationImpl withLink(String rel, String href) {
        delegate.withLink(rel, hrefPrefix + href);
        return this;
    }

    @Override
    public HalRepresentationImpl withLink(String rel, URI uri) {
        delegate.withLink(rel, uri);
        return this;
    }

    @Override
    public HalRepresentationImpl withLink(String rel, String href, String name, String title, String hrefLang, String profile) {
        delegate.withLink(rel, hrefPrefix + href, name, title, hrefLang, profile);
        return this;
    }

    @Override
    public HalRepresentationImpl withProperty(String name, Object value) {
        delegate.withProperty(name, value);
        return this;
    }

    @Override
    public HalRepresentationImpl withBean(Object value) {
        delegate.withBean(value);
        return this;
    }

    @Override
    public HalRepresentationImpl withFields(Object value) {
        delegate.withFields(value);
        return this;
    }

    @Override
    public HalRepresentationImpl withRepresentable(Representable representable) {
        delegate.withRepresentable(representable);
        return this;
    }

    @Override
    public HalRepresentationImpl withFieldBasedRepresentation(String rel, String href, Object o) {
        delegate.withFieldBasedRepresentation(rel, href, o);
        return this;
    }

    @Override
    public HalRepresentationImpl withBeanBasedRepresentation(String rel, String href, Object o) {
        delegate.withBeanBasedRepresentation(rel, href, o);
        return this;
    }

    @Override
    public HalRepresentationImpl withNamespace(String namespace, String href) {
        delegate.withNamespace(namespace, href);
        return this;
    }

    @Override
    public HalRepresentationImpl withRepresentation(String rel, ReadableRepresentation resource) {
        delegate.withRepresentation(rel, resource);
        return this;
    }

    @Override
    public Link getResourceLink() {
        return delegate.getResourceLink();
    }

    @Override
    public Map<String, String> getNamespaces() {
        return delegate.getNamespaces();
    }

    @Override
    public List<Link> getCanonicalLinks() {
        return delegate.getCanonicalLinks();
    }

    @Override
    public List<Link> getLinks() {
        return delegate.getLinks();
    }

    @Override
    public Link getLinkByRel(String rel) {
        return delegate.getLinkByRel(rel);
    }

    @Override
    public List<Link> getLinksByRel(String rel) {
        return delegate.getLinksByRel(rel);
    }

    @Override
    public List<? extends ReadableRepresentation> getResourcesByRel(String rel) {
        return delegate.getResourcesByRel(rel);
    }

    @Override
    public Object getValue(String name) {
        return delegate.getValue(name);
    }

    @Override
    public Object getValue(String name, Object defaultValue) {
        return delegate.getValue(name, defaultValue);
    }

    @Override
    public Map<String, Object> getProperties() {
        return delegate.getProperties();
    }

    @Override
    public boolean hasNullProperties() {
        return delegate.hasNullProperties();
    }

    @Override
    public Collection<Map.Entry<String, ReadableRepresentation>> getResources() {
        return delegate.getResources();
    }

    @Override
    public Map<String, Collection<ReadableRepresentation>> getResourceMap() {
        return delegate.getResourceMap();
    }

    @Override
    public boolean isSatisfiedBy(Contract contract) {
        return delegate.isSatisfiedBy(contract);
    }

    @Override
    public <T> T toClass(Class<T> anInterface) {
        return delegate.toClass(anInterface);
    }

    @Override
    public String toString(String contentType) {
        return delegate.toString(contentType);
    }

    @Override
    @Deprecated
    public String toString(String contentType, Set<URI> flags) {
        return delegate.toString(contentType, flags.toArray(new URI[flags.size()]));
    }

    @Override
    public String toString(String contentType, URI... flags) {
        return delegate.toString(contentType, flags);
    }

    @Override
    public void toString(String contentType, Writer writer) {
        delegate.toString(contentType, writer);
    }

    @Override
    @Deprecated
    public void toString(String contentType, Set<URI> flags, Writer writer) {
        delegate.toString(contentType, writer, new URI[flags.size()]);
    }

    @Override
    public void toString(String contentType, Writer writer, URI... flags) {
        delegate.toString(contentType, writer, flags);
    }
}
