package ca.access.student.service.impl;

import ca.access.student.repository.TeacherRepository;
import ca.access.student.service.ITeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: Lei Fu
 * @date: 2023/06/12
 * @description: Teacher information business interface implementation class
 */
@Service
@Transactional(readOnly = true)
public class TeacherServiceImpl implements ITeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

}
