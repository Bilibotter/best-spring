package factory.factory;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class TestStream {
    @Test
    public void testInputStream() throws IOException {
        String yhm = "yhm";
        try(InputStream stream = new ByteArrayInputStream(yhm.getBytes(StandardCharsets.UTF_8))) {
            int x;
            while ((x = stream.read()) != -1) {
                System.out.println((char)x);
            }
        };
    }
}
