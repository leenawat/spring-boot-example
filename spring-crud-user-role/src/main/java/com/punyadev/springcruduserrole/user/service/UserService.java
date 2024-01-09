package com.punyadev.springcruduserrole.user.service;

import com.punyadev.springcruduserrole.user.model.User;
import com.punyadev.springcruduserrole.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Create
    public User create(User user) {
        return userRepository.save(user);
    }

    // Read
    public List<User> readAll() {
        return userRepository.findAll();
    }

    // Read byId
    public Optional<User> read(Long id) {
        return userRepository.findById(id);
    }

    // Update
    public User update(Long id, User user) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            // TODO Auto-generated method stub
            User userOld = userOptional.get();
            userOld.setFirstName(user.getFirstName());
            userOld.setLastName(user.getLastName());
            userOld.setEmail(user.getEmail());
            userOld.setPassword(user.getPassword());
            return userRepository.save(userOld);
        }else{
            throw new Exception("user not found");
        }
    }

    // Update
    public User updatePatial(Long id, User user) throws Exception {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isPresent()) {
            User existing = userOptional.get();
            copyNonNullProperties(user, existing);
            return userRepository.save(existing);
        }else {
            throw new Exception("user not found");
        }
    }

    // Delete
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public static void copyNonNullProperties(Object src, Object target) {
        // BeanUtils.copyProperties(src, target, ignoreProperties)
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    public static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}