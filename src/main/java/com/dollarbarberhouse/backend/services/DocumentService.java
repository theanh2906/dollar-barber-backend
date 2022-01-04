package com.dollarbarberhouse.backend.services;

import com.dollarbarberhouse.backend.models.Documents;
import com.dollarbarberhouse.backend.repositories.DocumentRepository;
import com.dollarbarberhouse.backend.utils.FileUtils;
import com.dollarbarberhouse.backend.utils.XLSUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    public Flux<Documents> findAll() {
        return documentRepository.findAll();
    }

    public Flux<Map<String, Object>> parseJSON(MultipartFile multipartFile) {
        List<Map<String, Object>> listDocuments = new ArrayList<>();
        try {
            if (multipartFile == null) {
                return Flux.empty();
            }
            File file = FileUtils.convertFile(multipartFile);
            if (file == null) {
                return Flux.empty();
            }
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            List<String> headers = new ArrayList<>();
            for (Cell cell: sheet.getRow(0)) {
                headers.add(cell.getStringCellValue());
            }
            sheet.shiftRows(1, sheet.getLastRowNum(), -1);
            for (Row row: sheet) {
                Map<String, Object> rowMap = new HashMap<>();
                for (String header: headers) {
                    rowMap.put(header, row.getCell(headers.indexOf(header)).getStringCellValue());
                }
                listDocuments.add(rowMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Flux.fromIterable(listDocuments);
    }
}
