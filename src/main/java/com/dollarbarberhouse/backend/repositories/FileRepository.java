package com.dollarbarberhouse.backend.repositories;

import com.dollarbarberhouse.backend.models.FileModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends R2dbcRepository<FileModel, Long> {
}
