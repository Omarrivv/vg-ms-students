package pe.edu.vallegrande.msvstudents.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.msvstudents.model.Student;
import reactor.core.publisher.Flux;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String> {
    Flux<Student> findByInstitutionId(String institutionId);
    Flux<Student> findByStatus(String status);
    Flux<Student> findByGender(String gender);
} 