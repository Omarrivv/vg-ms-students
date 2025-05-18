package pe.edu.vallegrande.msvstudents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.msvstudents.model.Student;
import pe.edu.vallegrande.msvstudents.service.StudentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Flux<Student> findAll() {
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Student>> findById(@PathVariable String id) {
        return studentService.findById(id)
                .map(student -> ResponseEntity.ok(student))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> save(@RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Student>> update(@PathVariable String id, @RequestBody Student student) {
        return studentService.update(id, student)
                .map(updatedStudent -> ResponseEntity.ok(updatedStudent))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return studentService.delete(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/institution/{institutionId}")
    public Flux<Student> findByInstitutionId(@PathVariable String institutionId) {
        return studentService.findByInstitutionId(institutionId);
    }

    @GetMapping("/status/{status}")
    public Flux<Student> findByStatus(@PathVariable String status) {
        return studentService.findByStatus(status);
    }

    @GetMapping("/gender/{gender}")
    public Flux<Student> findByGender(@PathVariable String gender) {
        return studentService.findByGender(gender);
    }

    @PutMapping("/{id}/restore")
    public Mono<ResponseEntity<Student>> restore(@PathVariable String id) {
        return studentService.restore(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
} 