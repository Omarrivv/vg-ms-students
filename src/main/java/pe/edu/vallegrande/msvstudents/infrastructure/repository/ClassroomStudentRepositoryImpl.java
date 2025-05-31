package pe.edu.vallegrande.msvstudents.infrastructure.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import pe.edu.vallegrande.msvstudents.domain.repository.ClassroomStudentRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClassroomStudentRepositoryImpl implements ClassroomStudentRepository {
    private final MongoClassroomStudentRepository mongoRepository;

    @Override
    public Flux<ClassroomStudent> findAll() {
        return mongoRepository.findAll();
    }

    @Override
    public Mono<ClassroomStudent> findById(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public Mono<ClassroomStudent> save(ClassroomStudent classroomStudent) {
        return mongoRepository.save(classroomStudent);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return mongoRepository.deleteById(id);
    }

    @Override
    public Flux<ClassroomStudent> findByStudentId(String studentId) {
        return mongoRepository.findByStudentId(studentId);
    }

    @Override
    public Flux<ClassroomStudent> findByClassroomId(String classroomId) {
        return mongoRepository.findByClassroomId(classroomId);
    }

    @Override
    public Flux<ClassroomStudent> findByStatus(String status) {
        return mongoRepository.findByStatus(status);
    }

    @Override
    public Flux<ClassroomStudent> findByEnrollmentYear(String year) {
        return mongoRepository.findByEnrollmentYear(year);
    }

    @Override
    public Flux<ClassroomStudent> findByEnrollmentPeriod(String period) {
        return mongoRepository.findByEnrollmentPeriod(period);
    }

    @Override
    public Mono<Boolean> existsByStudentIdAndStatus(String studentId, String status) {
        return mongoRepository.existsByStudentIdAndStatus(studentId, status);
    }
} 