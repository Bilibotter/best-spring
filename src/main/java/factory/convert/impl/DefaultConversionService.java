package factory.convert.impl;

import factory.convert.ConverterRegistry;

public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry registry) {
        registry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
