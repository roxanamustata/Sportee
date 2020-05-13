package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.Room;
import com.sportee.sportee.data.DTO.RoomDTO;
import com.sportee.sportee.data.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements IRoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {

        this.roomRepository = roomRepository;
    }

    @Override
    public List<RoomDTO> getAllRooms() {
        List<RoomDTO> rooms = new ArrayList<RoomDTO>();
        Iterable<Room> all = roomRepository.findAll();
        all.forEach(m -> rooms.add(new RoomDTO(m)));
        return rooms;
    }

    @Override
    public void insertRoom(String name, int capacity) {
        Room r = Room.builder().name(name).capacity(capacity).build();
        roomRepository.save(r);


    }

    @Override
    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);

    }
}
