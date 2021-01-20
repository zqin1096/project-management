package com.jrp.pma.services;

import org.springframework.stereotype.Repository;

// Need to have the annotation. Otherwise, Spring cannot find the Bean for this
// class.
// @Primary: by default, spring will use StaffRepositoryImpl1 for dependency injection.
//@Primary
@Repository
public class StaffRepositoryImpl1 implements IStaffRepository {

}
