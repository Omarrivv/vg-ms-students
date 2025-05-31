package pe.edu.vallegrande.msvstudents.domain.enums;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ClassroomStudentRepository extends ReactiveMongoRepository<ClassroomStudent, String> {
    Flux<ClassroomStudent> findByStudentId(String studentId);
    Flux<ClassroomStudent> findByClassroomId(String classroomId);
    Flux<ClassroomStudent> findByStatus(String status);
    
    // Additional query methods to ensure all enrollments are properly loaded
    Flux<ClassroomStudent> findByEnrollmentYear(String enrollmentYear);
    Flux<ClassroomStudent> findByEnrollmentPeriod(String enrollmentPeriod);
    Flux<ClassroomStudent> findByStudentIdAndStatus(String studentId, String status);
    Flux<ClassroomStudent> findByClassroomIdAndStatus(String classroomId, String status);
    
    // Método para verificar si un estudiante ya tiene una matrícula activa
    Mono<Boolean> existsByStudentIdAndStatus(String studentId, String status);
} 