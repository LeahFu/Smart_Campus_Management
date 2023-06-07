package ca.access.student.service.impl;

import ca.access.student.domain.Student;
import ca.access.student.repository.StudentRepository;
import ca.access.student.service.IStudentService;
import ca.access.student.service.dto.StudentQueryCriteria;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Get student list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    @Override
    public Object getList(StudentQueryCriteria queryCriteria, Pageable pageable) {
        Page<Student> page = studentRepository.findAll((root, query, criteriaBuilder) -> QueryHelp.getPredicate(root,queryCriteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }

    /**
     * Add student information
     * @param student
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addStudent(Student student) {
        Student dbStudent = studentRepository.save(student);
        return dbStudent.getId()!=null;
    }
    /**
     * Get student details based on ID
     * @param id
     * @return
     */
    @Override
    public Student getById(Long id) {
            return studentRepository.findById(id).orElseGet(Student::new);
    }
}
