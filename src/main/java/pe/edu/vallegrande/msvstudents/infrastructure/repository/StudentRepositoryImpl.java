package pe.edu.vallegrande.msvstudents.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.vallegrande.msvstudents.domain.model.Student;
import pe.edu.vallegrande.msvstudents.domain.repository.StudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class StudentRepositoryImpl implements StudentRepository {
    private final MongoStudentRepository mongoRepository;

    @Override
    public Flux<Student> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public Mono<Student> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public Mono<Student> save(Student student) {
        return mongoRepository.save(student);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoRepository.deleteById(id);
    }

    @Override
    public Flux<Student> findByInstitutionId(String institutionId) {
        return mongoRepository.findByInstitutionId(institutionId);
    }

    @Override
    public Flux<Student> findByStatus(String status) {
        return mongoRepository.findByStatus(status);
    }

    @Override
    public Flux<Student> findByGender(String gender) {
        return mongoRepository.findByGender(gender);
    }
} 