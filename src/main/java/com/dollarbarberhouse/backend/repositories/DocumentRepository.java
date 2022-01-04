package com.dollarbarberhouse.backend.repositories;

import com.dollarbarberhouse.backend.models.Documents;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends ReactiveCrudRepository<Documents, Long> {
}
