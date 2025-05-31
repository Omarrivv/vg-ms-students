package pe.edu.vallegrande.msvstudents.domain.repository;

import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClassroomStudentRepository {
    Flux<ClassroomStudent> findAll();
    Mono<ClassroomStudent> findById(String id);
    Mono<ClassroomStudent> save(ClassroomStudent classroomStudent);
    Mono<Void> deleteById(String id);
    Flux<ClassroomStudent> findByStudentId(String studentId);
    Flux<ClassroomStudent> findByClassroomId(String classroomId);
    Flux<ClassroomStudent> findByStatus(String status);
    Flux<ClassroomStudent> findByEnrollmentYear(String year);
    Flux<ClassroomStudent> findByEnrollmentPeriod(String period);
    Mono<Boolean> existsByStudentIdAndStatus(String studentId, String status);
} 