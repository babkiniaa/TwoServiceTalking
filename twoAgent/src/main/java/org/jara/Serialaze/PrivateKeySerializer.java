package org.jara.Serialaze;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.Base64;

public class PrivateKeySerializer extends JsonSerializer<PrivateKey> {

    @Override
    public void serialize(PrivateKey value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String encodedKey = Base64.getEncoder().encodeToString(value.getEncoded());
        gen.writeString(encodedKey);  // Сериализуем в Base64 строку
    }
}