package com.skypos.ramdisk.controller;

import com.opencsv.CSVReader;
import com.skypos.ramdisk.model.Product;
import com.skypos.ramdisk.repository.ProductRepository;
import com.skypos.ramdisk.util.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${skypos.controller.base.url}")
public class ProductBulkUploadController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    ProductRepository productRepository;


    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        logger.info("Uploading accountId: {}");
        try {
            logger.info("file.getOriginalFilename() " + file.getOriginalFilename());
            HashMap hashMap = null;
            if (!file.getOriginalFilename().isEmpty()) {
                Path copyLocation = Paths.get(AppConstants.FILE_UPLOAD_FOLDER, file.getOriginalFilename());
                file.transferTo(copyLocation);
                String filename = file.getOriginalFilename();
                logger.info("name " + filename);
                if (filename != null && filename.length() > 0) {
                    String[] strings = filename.split("\\.");
                    String path = AppConstants.FILE_UPLOAD_FOLDER + "/" + filename;
                    String extension = strings[strings.length - 1];
                    logger.info("extension " + extension);

                        saveData(AppConstants.FILE_UPLOAD_FOLDER + "/" + filename);

                }
            } else {
                hashMap = new HashMap();
                hashMap.put("message", "Please upload the file");
                hashMap.put("status", "-1");
            }
            return new ResponseEntity<>(hashMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Exception while copying file", e.getMessage());
            return new ResponseEntity<>("Error while uploaded file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void saveData(String filePath) {
        CSVReader reader = null;
        try {
            HashMap hashMap = new HashMap();
            reader = new CSVReader(new FileReader(filePath), ',');
            String[] nextLine;
            int count = 0;
            List<Product> productList = new ArrayList();
            while ((nextLine = reader.readNext()) != null) {
                if (count > 0) {
                    Product product = new Product();
                    product.setName(nextLine[0]);
                    productList.add(product);
                }
                count++;
            }
            System.out.println(productList);
            productList = (List<Product>) productRepository.saveAll(productList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
