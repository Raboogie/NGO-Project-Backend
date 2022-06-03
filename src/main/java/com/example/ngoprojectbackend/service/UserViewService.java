package com.example.ngoprojectbackend.service;

import com.example.ngoprojectbackend.model.UserView;
import com.example.ngoprojectbackend.repo.UsersViewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserViewService {
	@Autowired
	private UsersViewRepo usersviewRepo;
	public List<UserView> getAllUsersView() {
		return usersviewRepo.findAll();
	}

	public UserView  getUserViewById(Integer id) {
		Optional<UserView> userView = null;
		try {
			userView = usersviewRepo.findById(id);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if(userView.isPresent()) {
			return userView.get();
		}
		return null;
	}

    public UserView createUserView(UserView userview) {
        usersviewRepo.save(userview);
        return userview;
    }

    public void deleteUserView(int id) {
        usersviewRepo.deleteById(id);
    }

}


