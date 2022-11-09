package com.example.trua_nay_an_gi.repository;

import com.example.trua_nay_an_gi.model.app_users.AppRoles;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface RoleRepository extends PagingAndSortingRepository<AppRoles,Long> {
    Optional<AppRoles> findByName(String name);
    @Modifying
    @Query(value = "insert into account_role(account_id,role_id) values (?1,?2)", nativeQuery = true)
    void setDefaultRole(Long accountId, Integer roleId);

}
