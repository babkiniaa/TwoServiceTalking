package org.jara.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class KeyAndTypeDto {

    private String method;

    private Map<Character, Character> substitutionMap;

}
