package org.jara.Serialaze;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Base64;

public class SecretKeySerializer extends JsonSerializer<SecretKey> {
    @Override
    public void serialize(SecretKey secretKey, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        jsonGenerator.writeString(encodedKey);
    }
}