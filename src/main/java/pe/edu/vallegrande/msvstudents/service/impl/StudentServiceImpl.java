package pe.edu.vallegrande.msvstudents.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.msvstudents.model.Student;
import pe.edu.vallegrande.msvstudents.repository.StudentRepository;
import pe.edu.vallegrande.msvstudents.service.StudentService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Flux<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Mono<Student> findById(String id) {
        return studentRepository.findById(id);
    }

    @Override
    public Mono<Student> save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Mono<Student> update(String id, Student student) {
        return studentRepository.findById(id)
                .flatMap(existingStudent -> {
                    student.setId(id);
                    return studentRepository.save(student);
                });
    }

    @Override
    public Mono<Void> delete(String id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public Flux<Student> findByInstitutionId(String institutionId) {
        return studentRepository.findByInstitutionId(institutionId);
    }

    @Override
    public Flux<Student> findByStatus(String status) {
        return studentRepository.findByStatus(status);
    }

    @Override
    public Flux<Student> findByGender(String gender) {
        return studentRepository.findByGender(gender);
    }
} 