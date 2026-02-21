package com.alexandra.careflow.services;

import com.alexandra.careflow.dtos.UserRequestDTO;
import com.alexandra.careflow.dtos.UserResponseDTO;
import com.alexandra.careflow.exceptions.ResourceNotFoundException;
import com.alexandra.careflow.models.User;
import com.alexandra.careflow.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findByActiveTrue().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return convertToResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setPassword(dto.password());
        user.setFullName(dto.fullName());
        user.setEmail(dto.email());
        user.setPhone(dto.phone());
        user.setRole(dto.role());

        User savedUser = userRepository.save(user);
        return convertToResponseDTO(savedUser);
    }

    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setActive(false);
        userRepository.save(user);
    }

    private UserResponseDTO convertToResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole()
        );
    }
}