package com.skypos.ramdisk.controller;

import com.skypos.ramdisk.model.Customer;
import com.skypos.ramdisk.repository.CustomerRepository;
import com.skypos.ramdisk.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${skypos.controller.base.url}")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    @PostMapping(AppConstants.BULKUPLOAD)
    public HashMap<String,List<Customer>> getCustomers(@RequestBody List<Customer> customers){
        System.out.println("In CustomerController");
        List<Customer> customerList = (List<Customer>) customerRepository.saveAll(customers);
        HashMap<String, List<Customer>> map = new HashMap();
        map.put("data",customerList);
        return map;
    }
}
