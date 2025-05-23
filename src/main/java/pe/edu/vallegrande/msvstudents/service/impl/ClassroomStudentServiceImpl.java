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
        // Verificar si el estudiante ya tiene una matrícula activa
        return classroomStudentRepository.existsByStudentIdAndStatus(classroomStudent.getStudentId(), "A")
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.error(new RuntimeException("El estudiante ya tiene una matrícula activa"));
                    }
                    // Si no existe matrícula activa, proceder con el guardado
                    return classroomStudentRepository.save(classroomStudent);
                });
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
        return classroomStudentRepository.findById(id)
            .flatMap(classroomStudent -> {
                classroomStudent.setStatus("I");
                return classroomStudentRepository.save(classroomStudent).then();
            });
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

    @Override
    public Mono<ClassroomStudent> restore(String id) {
        return classroomStudentRepository.findById(id)
                .flatMap(classroomStudent -> {
                    if (classroomStudent.getStatus().equals("I")) {
                        classroomStudent.setStatus("A");
                        return classroomStudentRepository.save(classroomStudent);
                    } else {
                        return Mono.just(classroomStudent); // Ya está activo
                    }
                })
                .switchIfEmpty(Mono.error(new RuntimeException("ClassroomStudent not found")));
    }
} 