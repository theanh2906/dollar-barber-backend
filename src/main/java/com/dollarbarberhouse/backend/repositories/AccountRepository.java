package com.dollarbarberhouse.backend.repositories;

import com.dollarbarberhouse.backend.models.Accounts;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends ReactiveCrudRepository<Accounts, Long> {
}
