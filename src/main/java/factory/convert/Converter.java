package factory.convert;

public interface Converter<S, T> {
    T convert(S source);
}
