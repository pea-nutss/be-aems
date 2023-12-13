package com.aaronbujatin.beaems.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(User user){
        user.setRole("ADMIN");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username){
        User user = userRepository.findByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("Username : " + username + " was not found."));
        return user;

    }

    public User updateUser(String id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // Update fields as needed
            existingUser.setFullName(updatedUser.getFullName());
            existingUser.setAge(updatedUser.getAge());
            existingUser.setGender(updatedUser.getGender());
            existingUser.setDob(updatedUser.getDob());
            existingUser.setPermanentAddress(updatedUser.getPermanentAddress());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setContactNumber(updatedUser.getContactNumber());
            existingUser.setHighestDegree(updatedUser.getHighestDegree());
            existingUser.setDegreeSchool(updatedUser.getDegreeSchool());
            existingUser.setDateGraduated(updatedUser.getDateGraduated());
            existingUser.setPosition(updatedUser.getPosition());
            existingUser.setTeam(updatedUser.getTeam());
            existingUser.setPayRate(updatedUser.getPayRate());
            existingUser.setStartDate(updatedUser.getStartDate());
            existingUser.setEndDate(updatedUser.getEndDate());
            existingUser.setPreviousPositionHeld(updatedUser.getPreviousPositionHeld());
            existingUser.setPreviousCompany(updatedUser.getPreviousCompany());
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setRfid(updatedUser.getRfid());
            existingUser.setRole(updatedUser.getRole());
            existingUser.setImage(updatedUser.getImage());

            return userRepository.save(existingUser);
        } else {
            // Handle user not found
            throw new RuntimeException("User not found with id: " + id);
        }
    }

}
