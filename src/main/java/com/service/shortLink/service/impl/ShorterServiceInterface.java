package com.service.shortLink.service.impl;

import com.service.shortLink.entity.Shorter;
import org.springframework.http.ResponseEntity;

public interface ShorterServiceInterface {
    Shorter createShortUrl(Shorter shorter);

    ResponseEntity responseEntity(String hash);

    ResponseEntity getAll();
}
