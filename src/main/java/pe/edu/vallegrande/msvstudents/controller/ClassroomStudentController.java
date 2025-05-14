package pe.edu.vallegrande.msvstudents.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.msvstudents.model.ClassroomStudent;
import pe.edu.vallegrande.msvstudents.service.ClassroomStudentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/classroom-students")
@CrossOrigin(origins = "*")
public class ClassroomStudentController {

    @Autowired
    private ClassroomStudentService classroomStudentService;

    @GetMapping
    public Flux<ClassroomStudent> findAll() {
        return classroomStudentService.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<ClassroomStudent>> findById(@PathVariable String id) {
        return classroomStudentService.findById(id)
                .map(classroomStudent -> ResponseEntity.ok(classroomStudent))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ClassroomStudent> save(@RequestBody ClassroomStudent classroomStudent) {
        return classroomStudentService.save(classroomStudent);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<ClassroomStudent>> update(@PathVariable String id, @RequestBody ClassroomStudent classroomStudent) {
        return classroomStudentService.update(id, classroomStudent)
                .map(updatedClassroomStudent -> ResponseEntity.ok(updatedClassroomStudent))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return classroomStudentService.delete(id)
                .then(Mono.just(ResponseEntity.ok().<Void>build()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/student/{studentId}")
    public Flux<ClassroomStudent> findByStudentId(@PathVariable String studentId) {
        return classroomStudentService.findByStudentId(studentId);
    }

    @GetMapping("/classroom/{classroomId}")
    public Flux<ClassroomStudent> findByClassroomId(@PathVariable String classroomId) {
        return classroomStudentService.findByClassroomId(classroomId);
    }

    @GetMapping("/status/{status}")
    public Flux<ClassroomStudent> findByStatus(@PathVariable String status) {
        return classroomStudentService.findByStatus(status);
    }
    
    // New endpoints for enhanced enrollment loading
    @GetMapping("/year/{enrollmentYear}")
    public Flux<ClassroomStudent> findByEnrollmentYear(@PathVariable String enrollmentYear) {
        return classroomStudentService.findByEnrollmentYear(enrollmentYear);
    }
    
    @GetMapping("/period/{enrollmentPeriod}")
    public Flux<ClassroomStudent> findByEnrollmentPeriod(@PathVariable String enrollmentPeriod) {
        return classroomStudentService.findByEnrollmentPeriod(enrollmentPeriod);
    }
    
    @GetMapping("/student/{studentId}/status/{status}")
    public Flux<ClassroomStudent> findByStudentIdAndStatus(
            @PathVariable String studentId,
            @PathVariable String status) {
        return classroomStudentService.findByStudentIdAndStatus(studentId, status);
    }
    
    @GetMapping("/classroom/{classroomId}/status/{status}")
    public Flux<ClassroomStudent> findByClassroomIdAndStatus(
            @PathVariable String classroomId,
            @PathVariable String status) {
        return classroomStudentService.findByClassroomIdAndStatus(classroomId, status);
    }
}