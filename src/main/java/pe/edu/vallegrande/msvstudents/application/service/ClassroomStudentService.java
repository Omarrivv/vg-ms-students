package pe.edu.vallegrande.msvstudents.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pe.edu.vallegrande.msvstudents.application.mapper.ClassroomStudentMapper;
import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import pe.edu.vallegrande.msvstudents.domain.repository.ClassroomStudentRepository;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.ClassroomStudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.ClassroomStudentResponse;
import pe.edu.vallegrande.msvstudents.infrastructure.exception.ResourceNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ClassroomStudentService {
    private final ClassroomStudentRepository classroomStudentRepository;
    private final ClassroomStudentMapper classroomStudentMapper;

    public Flux<ClassroomStudentResponse> findAll() {
        return classroomStudentRepository.findAll()
                .map(classroomStudentMapper::toResponse);
    }

    public Mono<ClassroomStudentResponse> findById(String id) {
        return classroomStudentRepository.findById(id)
                .map(classroomStudentMapper::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Matrícula no encontrada con ID: " + id)));
    }

    public Mono<ClassroomStudentResponse> save(ClassroomStudentRequest request) {
        ClassroomStudent classroomStudent = classroomStudentMapper.toEntity(request);
        return classroomStudentRepository.save(classroomStudent)
                .map(classroomStudentMapper::toResponse);
    }

    public Mono<ClassroomStudentResponse> update(String id, ClassroomStudentRequest request) {
        return classroomStudentRepository.findById(id)
                .flatMap(existingClassroomStudent -> {
                    ClassroomStudent updatedClassroomStudent = classroomStudentMapper.toEntity(request);
                    updatedClassroomStudent.setId(id);
                    return classroomStudentRepository.save(updatedClassroomStudent);
                })
                .map(classroomStudentMapper::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Matrícula no encontrada con ID: " + id)));
    }

    public Mono<Void> delete(String id) {
        return classroomStudentRepository.findById(id)
                .flatMap(classroomStudent -> classroomStudentRepository.deleteById(id))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Matrícula no encontrada con ID: " + id)));
    }

    public Flux<ClassroomStudentResponse> findByStudentId(String studentId) {
        return classroomStudentRepository.findByStudentId(studentId)
                .map(classroomStudentMapper::toResponse);
    }

    public Flux<ClassroomStudentResponse> findByClassroomId(String classroomId) {
        return classroomStudentRepository.findByClassroomId(classroomId)
                .map(classroomStudentMapper::toResponse);
    }

    public Flux<ClassroomStudentResponse> findByStatus(String status) {
        return classroomStudentRepository.findByStatus(status)
                .map(classroomStudentMapper::toResponse);
    }

    public Flux<ClassroomStudentResponse> findByEnrollmentYear(String year) {
        return classroomStudentRepository.findByEnrollmentYear(year)
                .map(classroomStudentMapper::toResponse);
    }

    public Flux<ClassroomStudentResponse> findByEnrollmentPeriod(String period) {
        return classroomStudentRepository.findByEnrollmentPeriod(period)
                .map(classroomStudentMapper::toResponse);
    }

    public Mono<ClassroomStudentResponse> restore(String id) {
        return classroomStudentRepository.findById(id)
                .flatMap(classroomStudent -> {
                    classroomStudent.setStatus("A");
                    return classroomStudentRepository.save(classroomStudent);
                })
                .map(classroomStudentMapper::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Matrícula no encontrada con ID: " + id)));
    }
} 