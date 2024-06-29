package com.devsuperior.hruser.resources;
import com.devsuperior.hruser.entities.Role;
import com.devsuperior.hruser.entities.User;
import com.devsuperior.hruser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResouce {

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping
    public ResponseEntity<List<User>> listAll(){
        List<User> users = userRepository.findAll();
        
        return !users.isEmpty() ? ResponseEntity.ok(users) : ResponseEntity.ok().build();
    }
	

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        User user = userRepository.findById(id).orElse(null);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }
    
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String password_enc = encoder.encode(user.getPassword());
		
        user.setPassword(password_enc);
        User createdUser = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userUpdate) {
        return userRepository.findById(id)
            .map(user -> {
                if (userUpdate.getName() != null) {
                    user.setName(userUpdate.getName());
                }
                if (userUpdate.getEmail() != null) {
                    user.setEmail(userUpdate.getEmail());
                }
                if (userUpdate.getPassword() != null) {
                    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                    user.setPassword(encoder.encode(userUpdate.getPassword()));
                }
                
                User updatedUser = userRepository.save(user);
                return ResponseEntity.ok(updatedUser);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<User> findByEmail(@RequestParam String email){
        User user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }
    
}
