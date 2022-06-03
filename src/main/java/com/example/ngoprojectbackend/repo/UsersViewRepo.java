package com.example.ngoprojectbackend.repo;

import com.example.ngoprojectbackend.model.UserView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersViewRepo extends JpaRepository<UserView, Integer>  {

}
