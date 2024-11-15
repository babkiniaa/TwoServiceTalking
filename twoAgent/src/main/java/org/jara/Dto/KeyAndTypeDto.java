package org.jara.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;

@Setter
@Getter
public class KeyAndTypeDto {

    private String method;

    private Map<Character, Character> substitutionMap;

    private SecretKey secretKey;

    private PrivateKey privateKey;

    private PublicKey publicKey;
}
