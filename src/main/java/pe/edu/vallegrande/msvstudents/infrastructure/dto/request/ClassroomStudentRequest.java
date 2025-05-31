package pe.edu.vallegrande.msvstudents.infrastructure.dto.request;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ClassroomStudentRequest {
    private String classroomId;
    private String studentId;
    private LocalDate enrollmentDate;
    private String status;
    private String enrollmentYear;
    private String enrollmentPeriod;
} 