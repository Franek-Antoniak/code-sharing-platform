package com.code.sharing.platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.code.sharing.platform.model.CodeHolder;
import com.code.sharing.platform.service.CRUD;

import java.util.List;
import java.util.Optional;


@RestController
public class Api {

    @Autowired
    private CRUD crud;

    @PostMapping(value = "/api/code/new", consumes = "application/json")
    public ResponseEntity<String> postCodeApi(@RequestBody CodeHolder object) {
        Optional<CodeHolder> codeHolder = Optional.ofNullable(object);
        codeHolder.ifPresent(code -> crud.saveAndEditCode(code));
        return codeHolder.isEmpty() ? ResponseEntity.noContent().build() :
                ResponseEntity.ok("{" + "id : \"" + object.getUniqueId() + "\"}");
    }

    @GetMapping("/api/code/{uniqueId}")
    public ResponseEntity<CodeHolder> getCodeApi(@PathVariable("uniqueId") String uniqueId) {
        return crud.getByUniqueId(uniqueId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<CodeHolder>> getLatestApi() {
            Optional<List<CodeHolder>> codeHolder = crud.getLatest();
            return ResponseEntity.ok(codeHolder.orElseThrow());
    }


}
