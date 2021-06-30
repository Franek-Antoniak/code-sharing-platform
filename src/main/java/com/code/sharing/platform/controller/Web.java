package com.code.sharing.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.code.sharing.platform.model.CodeHolder;
import com.code.sharing.platform.service.*;

import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


@RestController
public class Web {

    @Autowired
    private CRUD crud;

    @Autowired
    private WebService webService;

    @GetMapping("/code/new")
    public ResponseEntity<String> postCodeWeb() throws IOException {
        return ResponseEntity.ok(new String
                (Files.readAllBytes (new ClassPathResource("static/html/WebPost.html").getFile()
                        .toPath())));
    }

    @GetMapping("/code/{uniqueId}")
    public ResponseEntity<String> getCodeWeb(@PathVariable("uniqueId") String uniqueId) {
        Optional<CodeHolder> codeHolder = crud.getByUniqueId(uniqueId);
        return codeHolder.map(code -> webService.getResponseEntityHTML("WebGet.ftl", "CodeHolder", code))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/code/latest")
    public ResponseEntity<String> getLatestWeb() {
        Optional<List<CodeHolder>> codeHolders = crud.getLatest();
        return codeHolders.map(codes -> webService.getResponseEntityHTML("WebLatest.ftl", "CodeHolders", codes))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
