package com.hana8.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public class HashRepository extends JpaRepository<Hash, Long> {
	@Query(select h from Hashtag h inner join hashtagPosts p on h.id = p.hashtag)


}
