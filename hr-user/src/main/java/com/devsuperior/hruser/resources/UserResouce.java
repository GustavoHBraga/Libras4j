package com.devsuperior.hruser.resources;

import com.devsuperior.hruser.entities.User;
import com.devsuperior.hruser.repositories.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResouce {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userRepository.findById(id).get();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        User user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }
}
