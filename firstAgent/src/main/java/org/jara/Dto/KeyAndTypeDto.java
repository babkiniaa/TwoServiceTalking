package org.jara.Dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;
import org.jara.Serialaze.*;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

@Setter
@Getter
public class KeyAndTypeDto {

    private String method;

    private Map<Character, Character> substitutionMap;

    @JsonSerialize(using = SecretKeySerializer.class)
    @JsonDeserialize(using = SecretKeyDeserializer.class)
    private SecretKey secretKey;

    @JsonSerialize(using = PrivateKeySerializer.class)
    @JsonDeserialize(using = PrivateKeyDeserializer.class)
    private PrivateKey privateKey;

    @JsonSerialize(using = PublicKeySerializer.class)
    @JsonDeserialize(using = PublicKeyDeserializer.class)
    private PublicKey publicKey;
}
