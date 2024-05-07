package com.webSocketExample.webSocketExample.services;

import com.webSocketExample.webSocketExample.model.Message;
import com.webSocketExample.webSocketExample.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;


    @Override
    public void saveMessage(Message message) {
        repository.save(message);
    }
}
