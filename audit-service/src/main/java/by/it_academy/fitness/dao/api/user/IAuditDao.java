package by.it_academy.fitness.dao.api.user;

import by.it_academy.fitness.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAuditDao extends JpaRepository<AuditEntity, UUID> {
}
