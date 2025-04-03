package org.pgm.jpa01.repository;

import org.pgm.jpa01.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
