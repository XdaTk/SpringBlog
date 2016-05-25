package com.leftcoding.blog.repositories;

import com.leftcoding.blog.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by XdaTk on 16/5/22.
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
