package com.skypos.ramdisk.repository;

import com.skypos.ramdisk.model.Customer;
import com.skypos.ramdisk.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product,Long> {

}
