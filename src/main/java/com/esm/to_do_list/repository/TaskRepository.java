package com.esm.to_do_list.repository;

import com.esm.to_do_list.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository <TaskModel, UUID> {

}
