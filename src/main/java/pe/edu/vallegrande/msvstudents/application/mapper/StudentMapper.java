package pe.edu.vallegrande.msvstudents.application.mapper;

import org.springframework.stereotype.Component;

import pe.edu.vallegrande.msvstudents.domain.model.Student;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.StudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.StudentResponse;

@Component
public class StudentMapper {
    
    public Student toEntity(StudentRequest request) {
        Student student = new Student();
        student.setInstitutionId(request.getInstitutionId());
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setDocumentType(request.getDocumentType());
        student.setDocumentNumber(request.getDocumentNumber());
        student.setGender(request.getGender());
        student.setBirthDate(request.getBirthDate());
        student.setAddress(request.getAddress());
        student.setPhone(request.getPhone());
        student.setEmail(request.getEmail());
        student.setNameQr(request.getNameQr());
        student.setStatus(request.getStatus());
        return student;
    }

    public StudentResponse toResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .institutionId(student.getInstitutionId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .documentType(student.getDocumentType())
                .documentNumber(student.getDocumentNumber())
                .gender(student.getGender())
                .birthDate(student.getBirthDate())
                .address(student.getAddress())
                .phone(student.getPhone())
                .email(student.getEmail())
                .nameQr(student.getNameQr())
                .status(student.getStatus())
                .build();
    }
} 