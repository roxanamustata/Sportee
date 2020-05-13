package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.Measurement;
import com.sportee.sportee.data.DAO.MeasurementType;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.DTO.MeasurementDTO;
import com.sportee.sportee.data.repositories.MeasurementRepository;
import com.sportee.sportee.data.repositories.MeasurementTypeRepository;
import com.sportee.sportee.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Service;

import javax.annotation.security.RolesAllowed;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//@EnableGlobalMethodSecurity(jsr250Enabled = true)
//@RolesAllowed("ROLE_TRAINER")
public class MeasurementService implements IMeasurementService {
    private MeasurementRepository measurementRepository;
    private MeasurementTypeRepository measurementTypeRepository;
    private UserRepository userRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository,
                              MeasurementTypeRepository measurementTypeRepository,UserRepository userRepository) {
        this.measurementRepository = measurementRepository;
        this.measurementTypeRepository = measurementTypeRepository;
        this.userRepository = userRepository;

    }

    @Override
    public List<MeasurementDTO> getAllMeasurements() {
        List<MeasurementDTO> measurementTypes = new ArrayList<MeasurementDTO>();
        Iterable<Measurement> all = measurementRepository.findAll();
        all.forEach(m -> measurementTypes.add(new MeasurementDTO(m)));
        return measurementTypes;
    }

    @Override
    public void insertMeasurement(Date date, int value, int measurementTypeId, int userId) {
        Optional<MeasurementType> measurementType = measurementTypeRepository.findById(measurementTypeId);
        Optional<User> user = userRepository.findById(userId);
        measurementType.ifPresent(t -> {
//            sporteeMember.ifPresent(s -> {
            Measurement m = Measurement.builder().date(date).value(value)
                    .measurementType(t).user(user.get()).build();

            measurementRepository.save(m);


//            });
        });
    }

    @Override
    public void deleteMeasurement(Integer id) {
        measurementRepository.deleteById(id);
    }

    @Override
    public void editMeasurement(Integer id, Optional<Date> date, Optional<Integer> value, Optional<MeasurementType> measurementType, Optional<User> user) {
        Optional<Measurement> measurement = measurementRepository.findById(id);


        measurement.ifPresent(m -> {
            date.ifPresent(d -> m.setDate(d));
            value.ifPresent(v -> m.setValue(v));
//            measurementType.ifPresent(mt -> m.setMeasurementType(mt));
//            sporteeMember.ifPresent(s -> m.setSporteeMember(s));

            measurementRepository.save(m);
        });
    }
}
