package com.sportee.sportee.data.DTO;

import com.sportee.sportee.data.DAO.Room;
import lombok.Getter;

@Getter
public class RoomDTO {
    private int id;
    private String name;
    private int capacity;

    public RoomDTO(Room room) {
        this.name = room.getName();
        this.capacity = room.getCapacity();
    }
}
