package ca.access.student.service.impl;

import ca.access.student.domain.Teacher;
import ca.access.student.repository.TeacherRepository;
import ca.access.student.service.ITeacherService;
import ca.access.student.service.dto.TeacherQueryCriteria;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Get teacher list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    @Override
    public Object getList(TeacherQueryCriteria queryCriteria, Pageable pageable) {
        Page<Teacher> page = teacherRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root,queryCriteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }
    /**
     * Add teacher information
     * @param teacher
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addTeacher(Teacher teacher) {
        Teacher dbTeacher = teacherRepository.save(teacher);
        return dbTeacher.getId()!=null;
    }
}
