package com.sportee.sportee.services;

import com.sportee.sportee.data.DAO.Role;
import com.sportee.sportee.data.DAO.User;
import com.sportee.sportee.data.DTO.UserDTO;
import com.sportee.sportee.data.repositories.RoleRepository;
import com.sportee.sportee.data.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;

@Service("iUserService")

public class UserService implements IUserService {

    private UserRepository userRepository;

    private RoleRepository roleRepository;
//    private JavaMailSender javaMailSender;

    @Autowired
    public UserService(UserRepository userRepository,  RoleRepository roleRepository) {
        this.userRepository = userRepository;

        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<UserDTO>();
        Iterable<User> all = userRepository.findAll();
        all.forEach(u -> users.add(new UserDTO(u)));
        return users;
    }

//    @Override
//    public void sendMail(Integer userId, String subject, String content) {
//        Optional<User> user = userRepository.findById(userId);
//        user.ifPresent(u -> {
//            SimpleMailMessage mail = new SimpleMailMessage();
//            mail.setTo(u.getEmail());
//            mail.setFrom("ralured1979@gmail.com");
//            mail.setSubject(subject);
//            mail.setText(content);
//            javaMailSender.send(mail);
//        });
//    }

    @Override
    public void modifyUser(Integer id, Optional<String> userName, Optional<String> password, Optional<String> firstName, Optional<String> lastName,
                           Optional<Date> birthDate, Optional<Integer> height) {
        Optional<User> user = userRepository.findById(id);
        user.ifPresent(u -> {
            userName.ifPresent(n -> u.setUserName(n));
            password.ifPresent(e -> u.setPassword(e));
            firstName.ifPresent(f -> u.setFirstName(f));
            lastName.ifPresent(l -> u.setLastName(l));
            birthDate.ifPresent(b -> u.setBirthDate(b));
            height.ifPresent(h -> u.setHeight(h));
            userRepository.save(u);
        });
    }

    @Override
    public void insertUser(String userName, String password, String firstName, String lastName, Date birthDate, int height, int roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        role.ifPresent(r -> {
            User user = User.builder().userName(userName).password(password).firstName(firstName).lastName(lastName)
                    .birthDate(birthDate).height(height).role(role.get()).build();
            userRepository.save(user);
        });


    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

//    @Override
//    public void changeAddress(Integer userId, Optional<String> city, Optional<String> country, Optional<String> street) {
//        Optional<User> user = userRepository.findById(userId);
//        user.ifPresent(u -> {
//            Address address = getUserAddress(u);
//            city.ifPresent(c -> address.setCity(c));
//            country.ifPresent(c -> address.setCountry(c));
//            street.ifPresent(s -> address.setStreet(s));
//            Address updatedAddress = addressRepository.save(address);
//            u.setAddress(updatedAddress);
//            userRepository.save(u);
//        });
//
//    }

//    @Override
//    public String getServiceTitle(Integer id) throws NotFoundException {
//
//        Optional<User> user = userRepository.findById(id);
//        if (user.isPresent()) {
//            User u = user.get();
//            RestTemplate template = new RestTemplate();
//            String url = "https://jsonplaceholder.typicode.com/todos/" + u.getId();
//            String response = template.getForObject(url, String.class);
//
//            try {
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode root = mapper.readTree(response);
//                return root.path("title").asText();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//       throw new NotFoundException();
//    }

//    private Address getUserAddress(User u) {
//        Address address = u.getAddress();
//        if (address == null) {
//            address = new Address();
//            address.setUser(u);
//        }
//        return address;
//    }

    public String getUserName(Integer userId) throws NotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            User u = user.get();
            return u.getUserName();
        }
        throw new NotFoundException();
    }

}
