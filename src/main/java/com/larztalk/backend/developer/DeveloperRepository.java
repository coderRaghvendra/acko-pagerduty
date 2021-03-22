package com.larztalk.backend.developer;

import com.larztalk.backend.team.Team;
import com.larztalk.backend.test.Test;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by raghvendra.mishra on 02/08/20.
 */
@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    @Query(value = "select * from developer where team_id = :id", nativeQuery = true)
    List<Developer> getByTeamId(@Param("id") long id);
}
