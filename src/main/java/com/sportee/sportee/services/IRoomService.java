package com.sportee.sportee.services;

import com.sportee.sportee.data.DTO.RoomDTO;

import java.sql.Date;
import java.util.List;

public interface IRoomService {
    List<RoomDTO> getAllRooms();

    void insertRoom(String name, int capacity);

    void deleteRoom(Integer id);
}
