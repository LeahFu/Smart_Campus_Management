package ca.access.student.service.impl;

import ca.access.student.repository.StudentRepository;
import ca.access.student.service.IStudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Lei Fu
 * @date: 2023/06/01
 * @description: Student management interface implementation class
 */
@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements IStudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
}
