package org.jara.Serialaze;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.security.PublicKey;
import java.util.Base64;

public class PublicKeySerializer extends JsonSerializer<PublicKey> {

    @Override
    public void serialize(PublicKey value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String encodedKey = Base64.getEncoder().encodeToString(value.getEncoded());
        gen.writeString(encodedKey);  // Сериализуем в Base64 строку
    }
}