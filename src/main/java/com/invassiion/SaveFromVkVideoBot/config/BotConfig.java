package com.invassiion.SaveFromVkVideoBot.config;

import com.invassiion.SaveFromVkVideoBot.service.BotService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Configuration
public class BotConfig {

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    @Value("${telegram.bot.webhook-path}")
    private String webhookPath;


    @Bean
    public BotService telegramBot() throws TelegramApiException {
        SetWebhook setWebhook = SetWebhook.builder()
                .url(webhookPath)
                .build();


        BotService botService = new BotService(botUsername, botToken, setWebhook);

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(botService, setWebhook);

        return botService;
    }
}
