package de.neuefische.cgnjava233springdata.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public UserResponse saveNewUser(@RequestBody NewUser newUser){
		return userService.addNewUser(newUser);
	}

	@GetMapping
	public List<UserResponse> getAllUsers(){
		return userService.getAllUsers();
	}

	@PutMapping("/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody NewUser newUser){
		return userService.updateUser(id, newUser);
	}

	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable String id){
		userService.deleteUserById(id);
	}

}
