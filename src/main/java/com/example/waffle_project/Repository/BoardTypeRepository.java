package com.example.waffle_project.Repository;

import com.example.waffle_project.Entity.BoardTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardTypeRepository extends JpaRepository<BoardTypeEntity, String> {
    @Query(value = "SELECT * FROM board_type_entity where board_type = :type", nativeQuery = true)
    BoardTypeEntity finByType(@Param("type") String type);
}
