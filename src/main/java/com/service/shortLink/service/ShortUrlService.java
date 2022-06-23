package com.service.shortLink.service;

import com.service.shortLink.entity.Shorter;
import com.service.shortLink.repository.ShorterRepository;
import com.service.shortLink.service.codeGenerator.CodeGenerator;
import com.service.shortLink.service.impl.ShorterServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.net.URLDecoder;
import java.time.ZonedDateTime;

@Service
public class ShortUrlService implements ShorterServiceInterface {
    ShorterRepository repository;
    CodeGenerator codeGenerator;
    @Value("6")
    int shorterUrlLength;

    @Autowired
    public ShortUrlService(ShorterRepository repository) {
        this.repository = repository;
        this.codeGenerator = new CodeGenerator();
    }

    @Override
    public String createShortUrl(Shorter shorter) {
        String hash = codeGenerator.generate(shorterUrlLength);
        if (shorter != null) {
            String shorterString = URLDecoder.decode(shorter.getOriginalUrl());
            shorter = new Shorter(null, hash, shorterString, ZonedDateTime.now());
            repository.save(shorter);
            return "http://localhost:8080/url/" + shorter.getHash();
        }else{
            return null;
        }
    }

    @Override
    public ResponseEntity responseEntity(String hash) {
        Shorter shorter = repository.findByHash(hash);
        if (shorter != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", shorter.getOriginalUrl());
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        }else{
            return null;
        }
    }

    @Override
    public ResponseEntity getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}
