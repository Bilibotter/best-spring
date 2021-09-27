package factory.io;

import java.io.IOException;
import java.io.InputStream;

public interface Resource {
    String CLASSPATH_URL_PREFIX = "classpath:";
    InputStream getInputStream() throws IOException;
}
