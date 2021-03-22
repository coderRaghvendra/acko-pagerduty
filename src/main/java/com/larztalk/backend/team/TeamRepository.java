package com.larztalk.backend.team;

import com.larztalk.backend.test.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by raghvendra.mishra on 02/08/20.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "select * from team where name = :name limit 1", nativeQuery = true)
    Team getByName(@Param("name") String name);
}
