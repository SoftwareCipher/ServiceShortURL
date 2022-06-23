package com.service.shortLink.controller;

import com.service.shortLink.entity.Shorter;
import com.service.shortLink.service.impl.ShorterServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/url")
@Api(tags = "ShorterController", description = "ShorterController | Rest questions")
public class ShorterController {

    private final ShorterServiceInterface serviceInterface;

    @Autowired
    public ShorterController(ShorterServiceInterface serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @PostMapping(path = "/addHash", consumes = APPLICATION_JSON_VALUE)
    @ApiOperation("Create short url")
    public String createShortUrl(@RequestBody Shorter shorter) {
        return serviceInterface.createShortUrl(shorter);
    }

    @GetMapping(path = "/{hash}")
    @ApiOperation("Get original url")
    public ResponseEntity redirectShorter(@PathVariable("hash") String hash) {
        return serviceInterface.responseEntity(hash);
    }

    @GetMapping("allHash")
    @ApiOperation("Get all information about urls' ")
    public ResponseEntity getAll() {
        return serviceInterface.getAll();
    }
}
