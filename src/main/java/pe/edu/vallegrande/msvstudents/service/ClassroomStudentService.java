package pe.edu.vallegrande.msvstudents.service;

import pe.edu.vallegrande.msvstudents.model.ClassroomStudent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClassroomStudentService {
    Flux<ClassroomStudent> findAll();
    Mono<ClassroomStudent> findById(String id);
    Mono<ClassroomStudent> save(ClassroomStudent classroomStudent);
    Mono<ClassroomStudent> update(String id, ClassroomStudent classroomStudent);
    Mono<Void> delete(String id);
    Flux<ClassroomStudent> findByStudentId(String studentId);
    Flux<ClassroomStudent> findByClassroomId(String classroomId);
    Flux<ClassroomStudent> findByStatus(String status);
    
    // Additional methods for enhanced enrollment loading
    Flux<ClassroomStudent> findByEnrollmentYear(String enrollmentYear);
    Flux<ClassroomStudent> findByEnrollmentPeriod(String enrollmentPeriod);
    Flux<ClassroomStudent> findByStudentIdAndStatus(String studentId, String status);
    Flux<ClassroomStudent> findByClassroomIdAndStatus(String classroomId, String status);
} 