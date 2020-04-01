package com.mx.ontech.tianguis.postgresql.crudrestpostgresql.service;

import com.mx.ontech.tianguis.postgresql.crudrestpostgresql.models.Products;
import com.mx.ontech.tianguis.postgresql.crudrestpostgresql.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplementation implements ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductServiceImplementation.class);
    @Autowired
    private ProductRepository repo;

    @Override
    public String doMagic(Products data) {
        String resp = "";
        if (log.isTraceEnabled()) {
            log.trace(data.toString());
        }
        resp = repo.create(data);
        return resp;
    }
}
