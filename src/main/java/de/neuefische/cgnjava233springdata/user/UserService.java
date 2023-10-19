package de.neuefische.cgnjava233springdata.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepsository userRepsository;

	public UserResponse addNewUser(NewUser newUser) {
		User savedUser = userRepsository.save(new User(
				null,
				newUser.username(),
				newUser.password(),
				newUser.email(),
				new ArrayList<>()
		));
		return new UserResponse(
				savedUser.id(),
				savedUser.username(),
				savedUser.cars()
		);
	}

	public List<UserResponse> getAllUsers(){
		return userRepsository.findAll().stream()
				.map(user -> new UserResponse(user.id(), user.username(), user.cars()))
				.collect(Collectors.toList());
	}

	public UserResponse updateUser(String id, NewUser newUser){
		User userFromDb = userRepsository.findById(id).orElseThrow();
		User savedUser = userRepsository.save(new User(
				id,
				newUser.username(),
				newUser.password(),
				newUser.email(),
				userFromDb.cars()
		));
		return new UserResponse(
				savedUser.id(),
				savedUser.username(),
				savedUser.cars()
		);
	}

	public void deleteUserById(String id){
		User userToDelete = userRepsository.findById(id).orElseThrow();
		userRepsository.delete(userToDelete);
	}

}
