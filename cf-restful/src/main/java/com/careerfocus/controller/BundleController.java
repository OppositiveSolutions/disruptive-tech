package com.careerfocus.controller;

import com.careerfocus.service.BundleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sarath on 04/06/17.
 */

@RestController
@RequestMapping("/bundle")
public class BundleController {

    @Autowired
    BundleService service;


}
