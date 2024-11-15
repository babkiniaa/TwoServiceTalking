package org.jara.Mapper;

import org.jara.Dto.Key.KeyDto;
import org.jara.Entity.key.Key;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface KeyMapper {

    KeyDto keyDtoToKey (Key key);

    Key keyToKeyDto (KeyDto key);
}
