package org.pgm.jpa01.repository;

import org.pgm.jpa01.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member as m where m.name=:name")
    Member name(@RequestParam("name") String name);
    Member findByEmail(String email);
    //Member findByUsername(String name);

}
