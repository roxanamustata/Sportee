package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.GymClassType;
import com.sportee.sportee.data.DAO.MeasurementType;
import com.sportee.sportee.data.DTO.GymClassTypeDTO;
import com.sportee.sportee.data.DTO.MeasurementTypeDTO;
import com.sportee.sportee.data.repositories.GymClassTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GymClassTypeService implements IGymClassTypeService {
    private GymClassTypeRepository gymClassTypeRepository;

    @Autowired
    public GymClassTypeService(GymClassTypeRepository gymClassTypeRepository) {
        this.gymClassTypeRepository = gymClassTypeRepository;
    }

    @Override
    public List<GymClassTypeDTO> getAllGymClassTypes() {
        List<GymClassTypeDTO> gymClassTypes = new ArrayList<GymClassTypeDTO>();
        Iterable<GymClassType> all = gymClassTypeRepository.findAll();
        all.forEach(g -> gymClassTypes.add(new GymClassTypeDTO(g)));
        return gymClassTypes;
    }

    @Override
    public void insertGymClassType(String name, int duration) {
        GymClassType g = GymClassType.builder().name(name).duration(duration).build();
        gymClassTypeRepository.save(g);

    }

    @Override
    public void deleteGymClassType(Integer id) {
        gymClassTypeRepository.deleteById(id);

    }
}
