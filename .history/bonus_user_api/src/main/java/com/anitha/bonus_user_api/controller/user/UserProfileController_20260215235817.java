package com.anitha.bonus_user_api.controller.user;

import com.anitha.bonus_user_api.model.user.ApiResponse;
import com.anitha.bonus_user_api.model.user.UserProfile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    public UserProfileController() {
        users.add(new UserProfile(1L, "john_doe", "john@example.com", "John Doe", 25, "USA", "Software Engineer", true));
        users.add(new UserProfile(2L, "jane_admin", "jane@example.com", "Jane Smith", 32, "Canada", "Administrator", true));
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ApiResponse<UserProfile>> createUser(@RequestBody UserProfile user) {
        users.add(user);
        return new ResponseEntity<>(
            new ApiResponse<>(true, "User profile created successfully", user), 
            HttpStatus.CREATED
        );
    }

    // SEARCH by username, country, or age range
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<UserProfile>>> searchUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        
        List<UserProfile> results = users.stream()
            .filter(u -> (username == null || u.getUsername().equalsIgnoreCase(username)))
            .filter(u -> (country == null || u.getCountry().equalsIgnoreCase(country)))
            .filter(u -> (minAge == null || u.getAge() >= minAge))
            .filter(u -> (maxAge == null || u.getAge() <= maxAge))
            .collect(Collectors.toList());

        return ResponseEntity.ok(new ApiResponse<>(true, "Search completed successfully", results));
    }

    // ACTIVATE / DEACTIVATE
    @PatchMapping("/{userId}/status")
    public ResponseEntity<ApiResponse<UserProfile>> toggleStatus(
            @PathVariable Long userId, 
            @RequestParam boolean active) {
        
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(active);
                String statusMsg = active ? "User profile activated" : "User profile deactivated";
                return ResponseEntity.ok(new ApiResponse<>(true, statusMsg, user));
            }
        }
        return new ResponseEntity<>(
            new ApiResponse<>(false, "User not found", null), 
            HttpStatus.NOT_FOUND
        );
    }

    // DELETE
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long userId) {
        boolean removed = users.removeIf(u -> u.getUserId().equals(userId));
        if (removed) {
            return ResponseEntity.ok(new ApiResponse<>(true, "User deleted successfully", null));
        }
        return new ResponseEntity<>(
            new ApiResponse<>(false, "User not found", null), 
            HttpStatus.NOT_FOUND
        );
    }
}