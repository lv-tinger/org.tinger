package org.tinger.data.metadata.builder.wrapper;

import org.tinger.core.common.Ordered;
import org.tinger.data.metadata.Metadata;

public interface MetadataWrapper extends Ordered {
    void process(Metadata<?, ?> metadata, Class<?> type);
}
