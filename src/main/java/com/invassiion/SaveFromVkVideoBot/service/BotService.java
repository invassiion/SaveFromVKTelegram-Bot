package com.invassiion.SaveFromVkVideoBot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import org.telegram.telegrambots.meta.generics.WebhookBot;

@Service
public class BotService implements WebhookBot {

    private final String botUsername;
    private final String botToken;
    private final String botWebhookPath;


    public BotService(String botUsername, String botToken, SetWebhook setWebhook) {
        super();
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.botWebhookPath = setWebhook.getUrl();
    }


    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String responseText = processMessage(messageText);
            return sendMessage(chatId, responseText);
        }
        return null;
    }

    @Override
    public void setWebhook(SetWebhook setWebhook) throws TelegramApiException {

    }

    private BotApiMethod<?> sendMessage(long chatId, String responseText) {
        return null;
    }

    private String processMessage(String messageText) {
        if ("/start".equals(messageText)) {
            return "Привет! пришли мне сылку на видео, и я помогу тебе его скачать.";
        }
        return "Вы прислали: " + messageText;
    }



    @Override
    public String getBotPath() {
   return  botWebhookPath;
    }

    @Override
    public String getBotUsername() {
            return botUsername;
    }

    @Override
    public String getBotToken() {
            return  botToken;
    }

}
