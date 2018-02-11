package com.example.bot.spring.echo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.bot.spring.echo.bot.WhatDayBot;
import com.example.bot.spring.echo.repository.RoomRepository;

@Configuration
public class BotConfig {

    @Bean
    public WhatDayBot whatDayBot(RoomRepository roomRepository) {
        return new WhatDayBot(roomRepository);
    }
}
