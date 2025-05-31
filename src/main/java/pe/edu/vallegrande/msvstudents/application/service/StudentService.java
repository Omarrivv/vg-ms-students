package pe.edu.vallegrande.msvstudents.application.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pe.edu.vallegrande.msvstudents.application.mapper.StudentMapper;
import pe.edu.vallegrande.msvstudents.domain.model.Student;
import pe.edu.vallegrande.msvstudents.domain.repository.StudentRepository;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.StudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.StudentResponse;
import pe.edu.vallegrande.msvstudents.infrastructure.exception.ResourceNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public Flux<StudentResponse> findAll() {
        return studentRepository.findAll()
                .map(studentMapper::toResponse);
    }

    public Mono<StudentResponse> findById(String id) {
        return studentRepository.findById(id)
                .map(studentMapper::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estudiante no encontrado con ID: " + id)));
    }

    public Mono<StudentResponse> save(StudentRequest request) {
        Student student = studentMapper.toEntity(request);
        return studentRepository.save(student)
                .map(studentMapper::toResponse);
    }

    public Mono<StudentResponse> update(String id, StudentRequest request) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    Student updatedStudent = studentMapper.toEntity(request);
                    updatedStudent.setId(id);
                    return studentRepository.save(updatedStudent);
                })
                .map(studentMapper::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estudiante no encontrado con ID: " + id)));
    }

    public Mono<Void> delete(String id) {
        return studentRepository.findById(id)
                .flatMap(student -> studentRepository.deleteById(id))
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estudiante no encontrado con ID: " + id)));
    }

    public Flux<StudentResponse> findByInstitutionId(String institutionId) {
        return studentRepository.findByInstitutionId(institutionId)
                .map(studentMapper::toResponse);
    }

    public Flux<StudentResponse> findByStatus(String status) {
        return studentRepository.findByStatus(status)
                .map(studentMapper::toResponse);
    }

    public Flux<StudentResponse> findByGender(String gender) {
        return studentRepository.findByGender(gender)
                .map(studentMapper::toResponse);
    }

    public Mono<StudentResponse> restore(String id) {
        return studentRepository.findById(id)
                .flatMap(student -> {
                    student.setStatus("A");
                    return studentRepository.save(student);
                })
                .map(studentMapper::toResponse)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Estudiante no encontrado con ID: " + id)));
    }
} 