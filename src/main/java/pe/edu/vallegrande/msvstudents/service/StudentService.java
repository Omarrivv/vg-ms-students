package pe.edu.vallegrande.msvstudents.service;

import pe.edu.vallegrande.msvstudents.model.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {
    Flux<Student> findAll();
    Mono<Student> findById(String id);
    Mono<Student> save(Student student);
    Mono<Student> update(String id, Student student);
    Mono<Void> delete(String id);
    Flux<Student> findByInstitutionId(String institutionId);
    Flux<Student> findByStatus(String status);
    Flux<Student> findByGender(String gender);
    Mono<Student> restore(String id);
} 