package pe.edu.vallegrande.msvstudents.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.msvstudents.model.ClassroomStudent;
import reactor.core.publisher.Flux;

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
} 