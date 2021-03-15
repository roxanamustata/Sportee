package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.GymClassType;
import com.sportee.sportee.data.DAO.Room;
import com.sportee.sportee.data.DTO.GymClassDTO;
import com.sportee.sportee.data.DTO.HourDTO;
import com.sportee.sportee.repositories.GymClassRepository;
import com.sportee.sportee.repositories.GymClassTypeRepository;
import com.sportee.sportee.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GymClassService implements IGymClassService {
    private GymClassRepository gymClassRepository;
    private RoomRepository roomRepository;
    private GymClassTypeRepository gymClassTypeRepository;


    @Autowired
    public GymClassService(GymClassRepository gymClassRepository, RoomRepository roomRepository, GymClassTypeRepository gymClassTypeRepository) {
        this.gymClassRepository = gymClassRepository;
        this.roomRepository = roomRepository;
        this.gymClassTypeRepository = gymClassTypeRepository;

    }

    @Override
    public List<GymClassDTO> getAllGymClasses() {
        List<GymClassDTO> gymClasses = new ArrayList<GymClassDTO>();
        Iterable<GymClass> all = gymClassRepository.findAll();
        all.forEach(g -> gymClasses.add(new GymClassDTO(g)));
        return gymClasses;
    }

    //TODO do not allow two classes in the same room

    @Override
    public void insertGymClass(LocalDateTime date, int gymClassTypeId, int roomId) {
        Optional<GymClassType> gymClassType = gymClassTypeRepository.findById(gymClassTypeId);
        Optional<Room> room = roomRepository.findById(roomId);
        gymClassType.ifPresent(g -> {
            room.ifPresent(r -> {
                GymClass m = GymClass.builder().date(date).gymClassType(g).room(r).build();

                gymClassRepository.save(m);


            });
        });
    }

    @Override
    public GymClass findGymClass(Integer id) {
        GymClass gymClass = gymClassRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid gym class: " + id));
        return gymClass;
    }

    @Override
    public void deleteGymClass(Integer id) {
        gymClassRepository.deleteById(id);
    }


    @Override
    public HourDTO[] getTimetable() {

        HourDTO[] timetable = new HourDTO[12];
        for (int i = 0; i < 12; i++) {
            timetable[i] = new HourDTO(String.format("%02d:00", i + 9));
        }

        LocalDateTime currentDate = LocalDateTime.now();
        int currentDateNumber = currentDate.getDayOfWeek().getValue();
        LocalDateTime startDate = currentDate.minusDays(currentDateNumber);
        LocalDateTime endDate = startDate.plusDays(7);

        Iterable<GymClass> all = gymClassRepository.findAllByDateBetween(startDate, endDate);
        all.forEach(g -> {
                    int position = g.getDate().getHour();
                    timetable[position - 9].addGymClass(new GymClassDTO(g));

                }

        );
        return timetable;
    }

    @Override
    public void editGymClass(Integer id, LocalDateTime date, Integer gymClassTypeId, Integer roomId) {
        Optional<GymClass> gymClass = gymClassRepository.findById(id);
        Optional<GymClassType> gymClassType = gymClassTypeRepository.findById(gymClassTypeId);
        Optional<Room> room = roomRepository.findById(roomId);

        gymClass.ifPresent(g -> {
            g.setDate(date);
            gymClassType.ifPresent(t -> g.setGymClassType(t));
            room.ifPresent(r -> g.setRoom(r));
            gymClassRepository.save(g);
        });
    }

    @Override
    public List<GymClassDTO> searchGymClasses(String keyword) {
        if (keyword != null) {
            List<GymClassDTO> gymClasses = new ArrayList<GymClassDTO>();
            Iterable<GymClass> all = gymClassRepository.search(keyword);
            all.forEach(g -> gymClasses.add(new GymClassDTO(g)));
            return gymClasses;
        }
        return getAllGymClasses();
    }


}
