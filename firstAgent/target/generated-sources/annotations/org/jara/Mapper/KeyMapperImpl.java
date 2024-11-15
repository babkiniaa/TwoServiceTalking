package org.jara.Mapper;

import javax.annotation.Generated;
import org.jara.Dto.Key.KeyDto;
import org.jara.Entity.key.Key;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-16T00:21:40+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class KeyMapperImpl implements KeyMapper {

    @Override
    public KeyDto keyDtoToKey(Key key) {
        if ( key == null ) {
            return null;
        }

        KeyDto keyDto = new KeyDto();

        return keyDto;
    }

    @Override
    public Key keyToKeyDto(KeyDto key) {
        if ( key == null ) {
            return null;
        }

        Key key1 = new Key();

        return key1;
    }
}
