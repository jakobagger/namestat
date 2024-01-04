package dk.kea.namestat.repository;

import dk.kea.namestat.dto.NameStatsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameStatsRepository extends JpaRepository<NameStatsResponse, Integer> {
    boolean existsByName(String name);

    NameStatsResponse getByName(String name);
}
