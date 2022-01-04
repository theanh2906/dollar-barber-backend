package com.dollarbarberhouse.backend.services;

import com.dollarbarberhouse.backend.models.Documents;
import com.dollarbarberhouse.backend.models.FileModel;
import com.dollarbarberhouse.backend.repositories.DocumentRepository;
import com.dollarbarberhouse.backend.repositories.FileRepository;
import com.dollarbarberhouse.backend.utils.FileUtils;
import com.dollarbarberhouse.backend.utils.XLSUtils;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class StorageService {
    @Autowired
    private DocumentRepository documentRepository;
    @Autowired
    private FileRepository fileRepository;

    public StreamingResponseBody downloadFilesAsZip(HttpServletResponse response) {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=File.zip");

        StreamingResponseBody stream = out -> {
            ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
            try {
                byte[] byteArr = new byte[1024];
                if (byteArr != null) {
                    final ZipEntry ze = new ZipEntry("Test.txt");
                    zipOutputStream.putNextEntry(ze);
                    zipOutputStream.write(byteArr, 0, byteArr.length);
                    zipOutputStream.closeEntry();
                }

                if (zipOutputStream != null) {
                    zipOutputStream.finish();
                    zipOutputStream.flush();
                    IOUtils.closeQuietly(zipOutputStream);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        return stream;
    }

    public StreamingResponseBody exportExcel(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment;filename=temp.xlsx");
            List<Documents> docs = new ArrayList<>();
            Flux<Documents> allDocs = documentRepository.findAll();
            allDocs.subscribe(docs::add);

            Workbook workbook = XLSUtils.writeExcel(docs, Documents.class);
            if (workbook == null) {
                return null;
            }
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "temp.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
            InputStream inputStream = new FileInputStream(fileLocation);
            return out -> {
                int idx;
                byte[] data = new byte[1024];
                while ((idx = inputStream.read(data, 0, data.length)) != -1) {
                    out.write(data, 0, idx);
                }
                inputStream.close();
                outputStream.close();
                workbook.close();
                Files.delete(Paths.get(fileLocation));
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public FileModel uploadFile(MultipartFile multipartFile) {
        try {
            if (multipartFile == null) {
                return null;
            }
            File file = FileUtils.convertFile(multipartFile);
            if (file == null) {
                return null;
            }
            byte[] data = org.apache.commons.io.FileUtils.readFileToByteArray(file);
            FileModel uploadedFile = new FileModel();
            uploadedFile.setName(file.getName());
            uploadedFile.setBytes(data);
            List<FileModel> res = new ArrayList<>();
            fileRepository.save(uploadedFile).subscribe(res::add);
            return res.get(0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public StreamingResponseBody downloadFileById(Long id, HttpServletResponse response) {
        try {
            AtomicReference<FileModel> fetchedFile = new AtomicReference<>(new FileModel());
            fileRepository.findById(id).subscribe(fetchedFile::set);
            FileModel receivedFile = fetchedFile.get();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment;filename=temp.pdf");
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = path.substring(0, path.length() - 1) + "temp.pdf";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
//            outputStream.write(receivedFile.getBytes(), 0, receivedFile.getBytes().length);
            org.apache.commons.io.FileUtils.writeByteArrayToFile(new File(fileLocation), receivedFile.getBytes());
            InputStream inputStream = new FileInputStream(fileLocation);
            return out -> {
                out.write(receivedFile.getBytes(), 0, receivedFile.getBytes().length);
            };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
