package org.tinger.data.metadata;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GenericMetadata<T, ID> extends Metadata<T, ID> {
    private List<Property> properties;
}
