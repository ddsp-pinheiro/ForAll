package com.ForAll.repository;

import com.ForAll.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    public List<UserModel> findByNameContainingIgnoreCase(@Param("name") String name);

    public List<UserModel> findByEmailContainingIgnoreCase(@Param("email") String email);
}
