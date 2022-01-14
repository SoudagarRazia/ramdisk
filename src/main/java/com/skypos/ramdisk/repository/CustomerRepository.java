package com.skypos.ramdisk.repository;

import com.skypos.ramdisk.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer,Long> {

}
