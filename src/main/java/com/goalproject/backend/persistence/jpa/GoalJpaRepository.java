package com.goalproject.backend.persistence.jpa;

import com.goalproject.backend.persistence.entity.GoalEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalJpaRepository extends JpaRepository<GoalEntity, Long> {

    @Modifying
    @Query("DELETE FROM goal g WHERE g.userId = :userId")
    Long deleteAllByUserId(@Param("userId") Long userId);

    @Query("SELECT COUNT(*) FROM goal WHERE userId = :userId AND isCompleted= true")
    Long getCompletedGoalsCount(Long userId);

    Page<GoalEntity> findAllByUserId(Long userId, Pageable pageable);
}
