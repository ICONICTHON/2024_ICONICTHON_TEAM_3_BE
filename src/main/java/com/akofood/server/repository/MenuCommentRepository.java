package com.akofood.server.repository;

import com.akofood.server.entity.MenuComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuCommentRepository extends JpaRepository<MenuComment, Long> {
}