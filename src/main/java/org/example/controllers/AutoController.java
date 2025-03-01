package org.example.controllers;

import org.example.dto.ResponseResult;
import org.example.model.Auto;
import org.example.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auto")
public class AutoController {
    private AutoService autoService;

    @Autowired
    public void setAutoService(AutoService autoService) {
        this.autoService = autoService;
    }

    @GetMapping
    public ResponseEntity<ResponseResult<List<Auto>>> get() {
        List<Auto> autoList = this.autoService.get();
        return new ResponseEntity<>(new ResponseResult<>(null, autoList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseResult<Auto>> get(@PathVariable long id){
        try{
            Auto auto = autoService.get(id);
            return new ResponseEntity<>(new ResponseResult<>(null, auto), HttpStatus.OK);
        }catch (IllegalArgumentException e){
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{idStudent}")
    public ResponseEntity<ResponseResult<Auto>> add(@PathVariable long idStudent,
                                                    @RequestBody Auto auto) {
        try {
            this.autoService.add(idStudent, auto);
            return new ResponseEntity<>(new ResponseResult<>(null, auto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseResult<Auto>> update(@RequestBody Auto auto) {
        try {
            this.autoService.update(auto);
            return new ResponseEntity<>(new ResponseResult<>(null, auto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ResponseResult<Auto>> delete(@PathVariable long id) {
        try {
            Auto auto = this.autoService.delete(id);
            return new ResponseEntity<>(new ResponseResult<>(null, auto), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ResponseResult<>(e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }
}
