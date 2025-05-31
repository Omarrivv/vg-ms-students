package pe.edu.vallegrande.msvstudents.application.mapper;

import org.springframework.stereotype.Component;

import pe.edu.vallegrande.msvstudents.domain.model.ClassroomStudent;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.request.ClassroomStudentRequest;
import pe.edu.vallegrande.msvstudents.infrastructure.dto.response.ClassroomStudentResponse;

@Component
public class ClassroomStudentMapper {
    
    public ClassroomStudent toEntity(ClassroomStudentRequest request) {
        ClassroomStudent classroomStudent = new ClassroomStudent();
        classroomStudent.setClassroomId(request.getClassroomId());
        classroomStudent.setStudentId(request.getStudentId());
        classroomStudent.setEnrollmentDate(request.getEnrollmentDate());
        classroomStudent.setStatus(request.getStatus());
        classroomStudent.setEnrollmentYear(request.getEnrollmentYear());
        classroomStudent.setEnrollmentPeriod(request.getEnrollmentPeriod());
        return classroomStudent;
    }

    public ClassroomStudentResponse toResponse(ClassroomStudent classroomStudent) {
        return ClassroomStudentResponse.builder()
                .id(classroomStudent.getId())
                .classroomId(classroomStudent.getClassroomId())
                .studentId(classroomStudent.getStudentId())
                .enrollmentDate(classroomStudent.getEnrollmentDate())
                .status(classroomStudent.getStatus())
                .enrollmentYear(classroomStudent.getEnrollmentYear())
                .enrollmentPeriod(classroomStudent.getEnrollmentPeriod())
                .build();
    }
} 