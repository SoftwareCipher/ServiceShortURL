package com.service.shortLink.repository;

import com.service.shortLink.entity.Shorter;
import org.springframework.data.repository.CrudRepository;

public interface ShorterRepository extends CrudRepository<Shorter, Long> {
    Shorter findByHash(String hash);
}
