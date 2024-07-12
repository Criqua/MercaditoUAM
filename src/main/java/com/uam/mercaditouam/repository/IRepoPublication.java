package com.uam.mercaditouam.repository;

import com.uam.mercaditouam.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRepoPublication extends JpaRepository<Publication, Long> {
    List<Publication> findTop6ByOrderByIdDesc();

    /*@Query(value = "SELECT TOP :limit * FROM Publication WHERE isFeatured = 1", nativeQuery = true)
    List<Publication> findRandomDestacadas(@Param("limit") int limit);*/
}
