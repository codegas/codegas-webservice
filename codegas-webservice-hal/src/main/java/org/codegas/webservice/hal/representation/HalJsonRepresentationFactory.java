package org.codegas.webservice.hal.representation;

import java.io.Serializable;
import java.util.Collection;

import org.codegas.webservice.hal.api.HalConfig;
import org.codegas.webservice.hal.api.HalLink;
import org.codegas.webservice.hal.api.HalRepresentation;
import org.codegas.webservice.hal.api.HalRepresentationFactory;

import com.theoryinpractise.halbuilder.json.JsonRepresentationFactory;

public class HalJsonRepresentationFactory extends JsonRepresentationFactory implements HalRepresentationFactory {

    private final HalConfig halConfig;

    public HalJsonRepresentationFactory(HalConfig halConfig) {
        this.halConfig = halConfig;
        halConfig.getCuries().forEach(this::withNamespace);
        withFlag(PRETTY_PRINT);
        withFlag(COALESCE_ARRAYS);
    }

    public HalRepresentation createFor(Object resource) {
        return new HalRepresentationImpl(newRepresentation(), HAL_JSON, halConfig.getBaseHref()).withBean(wrapResource(resource));
    }

    public HalRepresentation createForLinks(Iterable<HalLink> links) {
        return new HalRepresentationImpl(newRepresentation(), HAL_JSON, halConfig.getBaseHref()).withLinks(links);
    }

    protected Object wrapResource(Object resource) {
        if (resource == null || resource.getClass().isPrimitive() || CharSequence.class.isInstance(resource)) {
            return new PrimitiveWrapper(resource);
        } else if (Collection.class.isInstance(resource)) {
            return new CollectionWrapper(Collection.class.cast(resource));
        } else {
            return resource;
        }
    }

    protected static final class PrimitiveWrapper implements Serializable {

        private final Object value;

        public PrimitiveWrapper(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }
    }

    protected static final class CollectionWrapper implements Serializable {

        private final Collection values;

        private CollectionWrapper(Collection values) {
            this.values = values;
        }

        public Collection getValues() {
            return values;
        }
    }
}
