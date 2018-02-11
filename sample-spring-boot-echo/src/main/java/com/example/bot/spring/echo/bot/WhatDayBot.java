package com.example.bot.spring.echo.bot;

import com.example.bot.spring.echo.Room;
import com.example.bot.spring.echo.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WhatDayBot {

    private final RoomRepository roomRepository;

    public CommandResponse handle(StartCommand command) {
        roomRepository.save(new Room(command.getFrom(), true));
        return new CommandResponse("OK! START!");
    }

    public CommandResponse handle(StopCommand command) {
        roomRepository.save(new Room(command.getFrom(), false));
        return new CommandResponse("OK! STOP!");
    }
}
