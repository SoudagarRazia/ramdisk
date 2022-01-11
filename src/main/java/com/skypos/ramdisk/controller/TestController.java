package com.skypos.ramdisk.controller;

import com.skypos.ramdisk.util.AppConstants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${skypos.controller.base.url}")
public class TestController {

    @GetMapping(AppConstants.TESTAPIURL)
    public String getSampleText(@RequestParam String text){
        System.out.println("MsgFromAPI: "+text);
        return text;
    }
}
