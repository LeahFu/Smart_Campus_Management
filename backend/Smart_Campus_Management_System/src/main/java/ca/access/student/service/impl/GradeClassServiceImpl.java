package ca.access.student.service.impl;

import ca.access.student.domain.GradeClass;
import ca.access.student.repository.GradeClassRepository;
import ca.access.student.service.IGradeClassService;
import ca.access.student.service.dto.GradeClassQueryCriteria;
import ca.access.utils.PageUtil;
import ca.access.utils.QueryHelp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    /**
     * Get class list data
     * @param queryCriteria
     * @param pageable
     * @return
     */
    @Override
    public Object getList(GradeClassQueryCriteria queryCriteria, Pageable pageable) {
        Page<GradeClass> page = gradeClassRepository.findAll((root,query,criteriaBuilder) ->
        QueryHelp.getPredicate(root,queryCriteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page);
    }
}
