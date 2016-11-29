package ru.itis.converters;


import org.springframework.beans.factory.annotation.Autowired;
import ru.itis.dao.MessagesDao;
import ru.itis.dto.MessageDto;
import ru.itis.models.Messages;

import java.util.List;

public class MessagesConverter {

    @Autowired
    private MessagesDao messagesDao;

    @Autowired
    public MessagesConverter() {
    }

    public MessageDto ConvertMessagesToMessageDto(Messages messages) {
        MessageDto messageDto = new MessageDto(messages.getMessageId(), messages.getMessageText(), messages.getChatId());
        return messageDto;
    }

    public Messages ConvertMessageDtoToMessages(MessageDto messageDto) {
        Messages messages = null;
        List<MessageDto> messageDtoList = messagesDao.findAllByChatId(messageDto.getChatId());
        return messages;
    }
}
