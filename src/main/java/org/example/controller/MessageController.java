package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.CassandraPage;
import org.example.dto.MessageDto;
import org.example.dto.Paginated;
import org.example.entity.cassandra.Message;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/message")
@Validated
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/chat/{chatId}")
    public CassandraPage<MessageDto> getPageOfUsers(final @Valid Paginated paginated, @PathVariable("chatId") long chatId) {
        return messageService.getPageOfMessages(chatId, paginated, Message::toDto);
    }

}
