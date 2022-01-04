package com.dollarbarberhouse.backend.rest;

import com.dollarbarberhouse.backend.dtos.ResponseDto;
import com.dollarbarberhouse.backend.models.Documents;
import com.dollarbarberhouse.backend.models.FileModel;
import com.dollarbarberhouse.backend.services.DocumentService;
import com.dollarbarberhouse.backend.services.FileService;
import com.dollarbarberhouse.backend.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private StorageService storageService;
    @Autowired
    private DocumentService documentService;
    @Autowired
    private FileService fileService;

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreamingResponseBody> downloadFile(final HttpServletResponse response) {
        StreamingResponseBody stream = storageService.downloadFilesAsZip(response);
        return new ResponseEntity<>(stream, HttpStatus.OK);
    }

    @GetMapping(value = "/download/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreamingResponseBody> downloadFileById(@PathVariable Long id, final HttpServletResponse response) {
        try {
            StreamingResponseBody stream = storageService.downloadFileById(id, response);
            return new ResponseEntity<>(stream, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<ResponseDto<String>> upload(@RequestParam MultipartFile file) {
        try {
            FileModel uploadedFile = storageService.uploadFile(file);
            return ResponseEntity.ok(new ResponseDto<>(true, uploadedFile.getName()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDto<>(false, e.getLocalizedMessage()));
        }
    }

    @GetMapping("")
    public Flux<FileModel> getAllFiles() {
        return fileService.findAll();
    }

    @GetMapping(value = "/exportExcel", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreamingResponseBody> exportExcel(final HttpServletResponse response) {
        try {
            StreamingResponseBody stream = storageService.exportExcel(response);
            return new ResponseEntity<>(stream, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
