package com.littlePay.paymentDemo.controller;

import com.littlePay.paymentDemo.service.LittlePayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class LittlePayRestController {

    @Autowired
    LittlePayService fileService;

    @RequestMapping(path = "/api/downloadTrips", method = RequestMethod.POST)
    public ResponseEntity<Resource> getFile(@RequestParam("file") MultipartFile file) throws IOException {
    //  generate  output file name
        String filename = "trips.csv";
        InputStreamResource outputFile = new InputStreamResource(fileService.loadTapsAndGenerateTrips(file.getInputStream()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(outputFile);
    }
}
