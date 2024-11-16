package org.jara.Serialaze;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;

public class SecretKeyDeserializer extends JsonDeserializer<SecretKey> {
    @Override
    public SecretKey deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String keyBase64 = jsonParser.getText();
        byte[] decodedKey = Base64.getDecoder().decode(keyBase64);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }
}