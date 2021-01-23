package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.DTO.GymClassBookingDTO;
import com.sportee.sportee.data.repositories.GymClassBookingRepository;
import com.sportee.sportee.data.repositories.GymClassRepository;
import com.sportee.sportee.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GymClassBookingService implements IGymClassBookingService {

    private GymClassRepository gymClassRepository;
    private UserRepository userRepository;
    private GymClassBookingRepository gymClassBookingRepository;

    @Autowired
    public GymClassBookingService(GymClassRepository gymClassRepository, UserRepository userRepository, GymClassBookingRepository gymClassBookingRepository) {
        this.gymClassRepository = gymClassRepository;
        this.userRepository = userRepository;
        this.gymClassBookingRepository = gymClassBookingRepository;
    }

    @Override
    public List<GymClassBookingDTO> getAllGymClassBookings() {
        List<GymClassBookingDTO> gymClassBookings = new ArrayList<GymClassBookingDTO>();
        Iterable<GymClassBooking> all = gymClassBookingRepository.findAll();
        all.forEach(g -> gymClassBookings.add(new GymClassBookingDTO(g)));
        return gymClassBookings;
    }

    @Override
    public void insertGymClassBooking(Integer gymClassId, Integer userId) {
        Optional<GymClass> gymClass = gymClassRepository.findById(gymClassId);

        Optional<User> user = userRepository.findById(userId);

        gymClass.ifPresent(g -> {

            user.ifPresent(u -> {
                GymClassBooking gb = GymClassBooking.builder()
                        .gymClass(g).user(u).build();

                gymClassBookingRepository.save(gb);
            });


        });


    }

//    @Override
//    public void bookGymClass(Integer gymClassId, Integer userId) {
//
//        Optional<GymClass> gymClass= gymClassRepository.findById(gymClassId);
//        Optional<User> user = userRepository.findById(userId);
//        GymClassBooking gb = GymClassBooking.builder()
//                .gymClass(gymClass).user(user).build();
//
//        gymClassBookingRepository.save(gb);
//
//
//    }


    @Override
    public void deleteGymClassBooking(Integer id) {
        gymClassBookingRepository.deleteById(id);
    }

    @Override
    public void editGymClassBooking(Integer id, Optional<Boolean> value, Optional<GymClass> gymClass, Optional<User> user) {

    }

    @Override
    public void bookGymClassBooking(Integer gymClassId, String remoteUserName) {
        Optional<GymClass> gymClass = gymClassRepository.findById(gymClassId);
        System.out.println("gasit clasa in servicu");
        User user = userRepository.findByUserName(remoteUserName);
        System.out.println("gasit user in serviciu");
        gymClass.ifPresent(g -> {


                GymClassBooking gb = GymClassBooking.builder()
                        .gymClass(g).user(user).build();

                gymClassBookingRepository.save(gb);
            });



    }
}
