package com.checklist.repositories;

import com.checklist.model.CheckListHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckRepositoryHistory extends JpaRepository<CheckListHistory, Integer> {
}
