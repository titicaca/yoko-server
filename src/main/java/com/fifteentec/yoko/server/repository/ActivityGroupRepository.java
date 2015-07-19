package com.fifteentec.yoko.server.repository;

import com.fifteentec.yoko.server.model.ActivityGroup;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "ActivityGroup")
@Qualifier(value = "activityGroupRepository")
public interface ActivityGroupRepository extends JpaRepository<ActivityGroup, Long>{

}
