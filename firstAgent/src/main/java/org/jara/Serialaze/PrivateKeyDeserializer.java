package org.jara.Serialaze;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class PrivateKeyDeserializer extends JsonDeserializer<PrivateKey> {

    @Override
    public PrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String encodedKey = p.getText();
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
            return keyFactory.generatePrivate(keySpec);
        } catch (Exception e) {
            throw new IOException("Failed to deserialize private key", e);
        }
    }
}