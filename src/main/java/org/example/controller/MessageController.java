package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.CassandraPage;
import org.example.dto.MessageDto;
import org.example.dto.Paginated;
import org.example.entity.cassandra.Message;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/message")
@Validated
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PutMapping("/generate")
    public Map<String, String> generate(@RequestParam(value = "chatCount", required = false, defaultValue = "1") int chatCount, @RequestParam("messageCount") int messageCount, @RequestParam("pinned") boolean pinned) {
        messageService.generate(chatCount, messageCount, pinned);
        return Map.of("status", "success");
    }

    @GetMapping("/chat/{chatId}")
    public CassandraPage<MessageDto> getPageOfMessages(final @Valid Paginated paginated, @PathVariable("chatId") long chatId) {
        return messageService.getPageOfMessages(chatId, paginated, Message::toDto);
    }

}
