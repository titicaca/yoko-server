package com.fifteentec.yoko.server.repository;

import java.util.Set;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fifteentec.yoko.server.model.Tag;

@Repository
@Table(name = "Tag")
@Qualifier(value = "tagRepository")
public interface TagRepository  extends JpaRepository<Tag, Long> {
	public Set<Tag> findByUser_id(Long user_id);
	public Tag findById(Long id);
	
}

