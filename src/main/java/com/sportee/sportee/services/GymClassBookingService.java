package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.GymClass;
import com.sportee.sportee.data.DAO.GymClassBooking;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.DTO.GymClassBookingDTO;
import com.sportee.sportee.repositories.GymClassBookingRepository;
import com.sportee.sportee.repositories.GymClassRepository;
import com.sportee.sportee.repositories.UserRepository;
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
    public List<GymClassBookingDTO> getAllGymClassBookingsByUserName(String userName) {
        User user = userRepository.findByUserName(userName);

        List<GymClassBookingDTO> gymClassBookings = new ArrayList<GymClassBookingDTO>();
        Iterable<GymClassBooking> all = gymClassBookingRepository.findAllByUserId(user.getId());
        all.forEach(g -> gymClassBookings.add(new GymClassBookingDTO(g)));
        return gymClassBookings;
    }

    @Override
    public void insertGymClassBooking(GymClassBooking gymClassBooking) {
        Optional<GymClass> gymClass = gymClassRepository.findById(gymClassBooking.getGymClass().getId());
        Optional<User> user = userRepository.findById(gymClassBooking.getUser().getId());

        gymClass.ifPresent(
                g -> {
                    if (g.getAvailable() >= 1) {
                        user.ifPresent(u -> {

                            GymClassBooking gb = GymClassBooking.builder()
                                    .gymClass(g).user(u).build();
                            gymClassBookingRepository.save(gb);
                        });
                    }
                });

    }

    @Override
    public void deleteGymClassBooking(Integer userId, Integer gymClassId) {
        GymClassBooking gymClassBooking = gymClassBookingRepository.findByUserIdAndGymClassId(userId, gymClassId);
        gymClassBookingRepository.delete(gymClassBooking);
        Optional<GymClass> gymClass = gymClassRepository.findById(gymClassId);
        gymClass.ifPresent(g -> {
            g.setAvailable(g.getAvailable() + 1);
            gymClassRepository.save(g);
        });
    }

    @Override
    public void editGymClassBooking(Integer id, Optional<Boolean> value, Optional<GymClass> gymClass, Optional<User> user) {

    }

    @Override
    public void bookGymClassBooking(Integer gymClassId, String remoteUserName) {
        Optional<GymClass> gymClass = gymClassRepository.findById(gymClassId);
        User user = userRepository.findByUserName(remoteUserName);

        gymClass.ifPresent(g -> {
            if (g.getAvailable() >= 1) {
                GymClassBooking gb = GymClassBooking.builder()
                        .gymClass(g).user(user).build();

                gymClassBookingRepository.save(gb);
            }
        });


    }

    @Override
    public List<GymClassBookingDTO> searchGymClassBookings(String keyword) {
        if (keyword != null) {
            List<GymClassBookingDTO> gymClassBookings = new ArrayList<GymClassBookingDTO>();
            Iterable<GymClassBooking> all = gymClassBookingRepository.search(keyword);
            all.forEach(g -> gymClassBookings.add(new GymClassBookingDTO(g)));
            return gymClassBookings;
        }
        return getAllGymClassBookings();
    }


}
