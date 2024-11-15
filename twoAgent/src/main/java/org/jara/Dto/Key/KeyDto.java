package org.jara.Dto.Key;

import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

@Getter
@Setter
public class KeyDto {

    private Map<Character, Character> substitutionMap;

    private SecretKey secretKey;

    private PrivateKey privateKey;

    private PublicKey publicKey;

}
