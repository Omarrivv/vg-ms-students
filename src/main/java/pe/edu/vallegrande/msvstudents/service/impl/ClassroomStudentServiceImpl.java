package pe.edu.vallegrande.msvstudents.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.msvstudents.model.ClassroomStudent;
import pe.edu.vallegrande.msvstudents.repository.ClassroomStudentRepository;
import pe.edu.vallegrande.msvstudents.service.ClassroomStudentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClassroomStudentServiceImpl implements ClassroomStudentService {

    @Autowired
    private ClassroomStudentRepository classroomStudentRepository;

    @Override
    public Flux<ClassroomStudent> findAll() {
        // Enhanced to ensure all enrollments are properly loaded
        return classroomStudentRepository.findAll()
                .switchIfEmpty(Flux.empty())
                .onErrorResume(e -> {
                    System.err.println("Error loading enrollments: " + e.getMessage());
                    return Flux.empty();
                });
    }

    @Override
    public Mono<ClassroomStudent> findById(String id) {
        return classroomStudentRepository.findById(id);
    }

    @Override
    public Mono<ClassroomStudent> save(ClassroomStudent classroomStudent) {
        return classroomStudentRepository.save(classroomStudent);
    }

    @Override
    public Mono<ClassroomStudent> update(String id, ClassroomStudent classroomStudent) {
        return classroomStudentRepository.findById(id)
                .flatMap(existingClassroomStudent -> {
                    classroomStudent.setId(id);
                    return classroomStudentRepository.save(classroomStudent);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return classroomStudentRepository.deleteById(id);
    }

    @Override
    public Flux<ClassroomStudent> findByStudentId(String studentId) {
        return classroomStudentRepository.findByStudentId(studentId)
                .switchIfEmpty(Flux.empty())
                .onErrorResume(e -> {
                    System.err.println("Error loading enrollments by student ID: " + e.getMessage());
                    return Flux.empty();
                });
    }

    @Override
    public Flux<ClassroomStudent> findByClassroomId(String classroomId) {
        return classroomStudentRepository.findByClassroomId(classroomId)
                .switchIfEmpty(Flux.empty())
                .onErrorResume(e -> {
                    System.err.println("Error loading enrollments by classroom ID: " + e.getMessage());
                    return Flux.empty();
                });
    }

    @Override
    public Flux<ClassroomStudent> findByStatus(String status) {
        return classroomStudentRepository.findByStatus(status)
                .switchIfEmpty(Flux.empty())
                .onErrorResume(e -> {
                    System.err.println("Error loading enrollments by status: " + e.getMessage());
                    return Flux.empty();
                });
    }
    
    // Additional methods to support the new repository methods
    public Flux<ClassroomStudent> findByEnrollmentYear(String enrollmentYear) {
        return classroomStudentRepository.findByEnrollmentYear(enrollmentYear)
                .switchIfEmpty(Flux.empty())
                .onErrorResume(e -> {
                    System.err.println("Error loading enrollments by year: " + e.getMessage());
                    return Flux.empty();
                });
    }
    
    public Flux<ClassroomStudent> findByEnrollmentPeriod(String enrollmentPeriod) {
        return classroomStudentRepository.findByEnrollmentPeriod(enrollmentPeriod)
                .switchIfEmpty(Flux.empty())
                .onErrorResume(e -> {
                    System.err.println("Error loading enrollments by period: " + e.getMessage());
                    return Flux.empty();
                });
    }
    
    public Flux<ClassroomStudent> findByStudentIdAndStatus(String studentId, String status) {
        return classroomStudentRepository.findByStudentIdAndStatus(studentId, status)
                .switchIfEmpty(Flux.empty())
                .onErrorResume(e -> {
                    System.err.println("Error loading enrollments by student ID and status: " + e.getMessage());
                    return Flux.empty();
                });
    }
    
    public Flux<ClassroomStudent> findByClassroomIdAndStatus(String classroomId, String status) {
        return classroomStudentRepository.findByClassroomIdAndStatus(classroomId, status)
                .switchIfEmpty(Flux.empty())
                .onErrorResume(e -> {
                    System.err.println("Error loading enrollments by classroom ID and status: " + e.getMessage());
                    return Flux.empty();
                });
    }
} 