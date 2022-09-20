package com.joaocigana.mongoapi.services;

import com.joaocigana.mongoapi.dto.UserDTO;
import com.joaocigana.mongoapi.entities.User;
import com.joaocigana.mongoapi.exceptions.ObjectNotFoundException;
import com.joaocigana.mongoapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    UserRepository userRepository;

    public List<User> findAllUsers(){
        List<User> userList = userRepository.findAll();
        return userList;
    }

    public User findUserById(String id){
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
        return user;
    }

    public User insertUser(User user){
        return userRepository.insert(user);
    }

    public User updateUser(User updatedUser){
        User user = findUserById(updatedUser.getId());
        updateData(user, updatedUser);
        return userRepository.save(user);
    }

    private void updateData(User user, User updatedUser) {
        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
    }

    public void deleteUserById(String id){
        findUserById(id);
        userRepository.deleteById(id);
    }

    public User fromDTO(UserDTO dto){
        return new User(dto.getId(), dto.getName(), dto.getEmail());
    }




}
