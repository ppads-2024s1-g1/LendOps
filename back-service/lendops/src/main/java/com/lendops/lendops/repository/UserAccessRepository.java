package com.lendops.lendops.repository;

import com.lendops.lendops.orm.Media;
import com.lendops.lendops.orm.UserAccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccessRepository extends CrudRepository<UserAccess, Long> {
}