package ca.access.student.service.impl;

import ca.access.student.repository.GradeClassRepository;
import ca.access.student.service.IGradeClassService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Lei Fu
 * @date: 2023/05/29
 * @description: Class information business interface implementation class
 */
@Service
@Transactional(readOnly = true)
public class GradeClassServiceImpl implements IGradeClassService {
    private final GradeClassRepository gradeClassRepository;

    public GradeClassServiceImpl(GradeClassRepository gradeClassRepository) {
        this.gradeClassRepository = gradeClassRepository;
    }
}
