package org.tinger.data.metadata.builder;

import org.tinger.data.metadata.Metadata;

public interface MetadataBuilder {
    Metadata<?, ?> build(Class<?> type);
}
