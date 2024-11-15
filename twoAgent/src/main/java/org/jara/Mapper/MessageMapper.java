package org.jara.Mapper;

import org.jara.Dto.MessageDto;
import org.jara.Entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    Message messageDtoToMessage(MessageDto messageDto);
}
