package com.example.springbootquartzjpa.repository;

import com.example.springbootquartzjpa.entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TaskRepository extends JpaRepository<Tasks,Long> {
}
