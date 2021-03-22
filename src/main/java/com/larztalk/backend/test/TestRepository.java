package com.larztalk.backend.test;

import com.larztalk.backend.user.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by raghvendra.mishra on 02/08/20.
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {


}
