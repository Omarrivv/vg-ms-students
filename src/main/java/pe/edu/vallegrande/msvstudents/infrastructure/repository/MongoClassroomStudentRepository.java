package pe.edu.vallegrande.msvstudents.infrastructure.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface MongoClassroomStudentRepository extends ReactiveMongoRepository<ClassroomStudent, String> {
    Flux<ClassroomStudent> findByStudentId(String studentId);
    Flux<ClassroomStudent> findByClassroomId(String classroomId);
    Flux<ClassroomStudent> findByStatus(String status);
    Flux<ClassroomStudent> findByEnrollmentYear(String year);
    Flux<ClassroomStudent> findByEnrollmentPeriod(String period);
    Mono<Boolean> existsByStudentIdAndStatus(String studentId, String status);
} 