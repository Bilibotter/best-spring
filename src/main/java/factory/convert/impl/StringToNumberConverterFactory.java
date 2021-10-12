package factory.convert.impl;

import com.sun.istack.internal.Nullable;
import factory.convert.Converter;
import factory.convert.ConverterFactory;
import factory.util.NumberUtils;

public class StringToNumberConverterFactory implements ConverterFactory {

    @Override
    public Converter getConverter(Class targetType) {
        return null;
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {
        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Nullable
        @Override
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }

}
