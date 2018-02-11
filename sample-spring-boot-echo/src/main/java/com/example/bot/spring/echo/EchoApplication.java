/*
 * Copyright 2018 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.example.bot.spring.echo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bot.spring.echo.bot.StartCommand;
import com.example.bot.spring.echo.bot.StopCommand;
import com.example.bot.spring.echo.bot.WhatDayBot;

import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

@SpringBootApplication
@LineMessageHandler
public class EchoApplication {
    public static void main(String[] args) {
        SpringApplication.run(EchoApplication.class, args);
    }

    private static final Logger logger = LoggerFactory.getLogger(EchoApplication.class);

    @Autowired
    private WhatDayBot whatDayBot;

    @EventMapping
    public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
        logger.info("event: {}", event);
        return new TextMessage(handle(event));
    }

    private String handle(MessageEvent<TextMessageContent> event) {
        switch (event.getMessage().getText().toUpperCase()) {
            case "START":
                return whatDayBot.handle(new StartCommand(event.getSource().getSenderId())).getText();
            case "STOP":
                return whatDayBot.handle(new StopCommand(event.getSource().getSenderId())).getText();
            default:
                return "";
        }
    }

    @EventMapping
    public void handleDefaultMessageEvent(Event event) {
        logger.info("event: {}", event);
    }
}
