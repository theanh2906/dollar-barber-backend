package com.dollarbarberhouse.backend.services;

import com.dollarbarberhouse.backend.models.FileModel;
import com.dollarbarberhouse.backend.repositories.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FileService {
    @Autowired
    private FileRepository fileRepository;

    public Flux<FileModel> findAll() {
        return fileRepository.findAll();
    }

    public Mono<FileModel> findById(Long id) {
        return fileRepository.findById(id);
    }

    public Mono<Boolean> upload(FileModel file) {
        return fileRepository
                .save(file)
                .map(f -> true)
                .switchIfEmpty(Mono.just(false));
    }
}
