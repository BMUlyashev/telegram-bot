package pro.sky.telegrambot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pro.sky.telegrambot.entity.Planner;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface PlannerRepository extends JpaRepository<Planner, Long> {

    @Query("SELECT p FROM Planner as p WHERE p.timeTask = ?1")
    Collection<Planner> findTask(LocalDateTime time);
}
