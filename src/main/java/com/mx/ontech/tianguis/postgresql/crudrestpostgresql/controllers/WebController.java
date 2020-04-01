package com.mx.ontech.tianguis.postgresql.crudrestpostgresql.controllers;

import com.mx.ontech.tianguis.postgresql.crudrestpostgresql.models.Products;
import com.mx.ontech.tianguis.postgresql.crudrestpostgresql.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@Controller
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class WebController {

    private static final Logger log = LoggerFactory.getLogger(WebController.class);

    @Autowired
    ProductService productService;

    @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> productsForm(@RequestBody Products data) {
        try {
            String resp = productService.doMagic(data);

            if (resp != "ok") {
                return new ResponseEntity<>("{\"status\":\"" + resp + "\"}", HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>("{\"status\":\"Ok\"}", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("{\"status\":\"Error\",\"mensaje\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
        }
    }
}
