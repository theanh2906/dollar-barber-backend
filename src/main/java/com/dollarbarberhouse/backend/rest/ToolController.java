package com.dollarbarberhouse.backend.rest;

import com.dollarbarberhouse.backend.services.DocumentService;
import com.dollarbarberhouse.backend.utils.HelpUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/tools")
public class ToolController {
    @Autowired
    private DocumentService documentService;
    @PostMapping("/encodeJson")
    public Mono<String> encodeJson(@RequestBody Map json) {
        return Mono.just(HelpUtils.encodeBase64Str(new JSONObject(json).toString()));
    }
    @PostMapping("/stringify")
    public Mono<String> stringify(@RequestBody Map json) {
        return Mono.just(HelpUtils.stringifyJson(json));
    }

    @PostMapping("/fromExcelToJSON")
    public Flux<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        return documentService.parseJSON(file);
    }

}
