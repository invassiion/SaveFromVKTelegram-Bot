package com.invassiion.SaveFromVkVideoBot.controller;

import com.invassiion.SaveFromVkVideoBot.service.BotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

@RequiredArgsConstructor
@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private final BotService botService;


    public void onUpdateReceive(@RequestBody Update update) {
        botService.onWebhookUpdateReceived(update);
    }
}
