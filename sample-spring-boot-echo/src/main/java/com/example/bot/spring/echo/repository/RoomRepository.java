package com.example.bot.spring.echo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bot.spring.echo.Room;

public interface RoomRepository extends JpaRepository<Room, String> {
}
