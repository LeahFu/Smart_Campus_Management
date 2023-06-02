import request from '../request'

// Get student list data
export function getStudentListApi(data:object) {
    return request({
        url: 'student',
        method: 'get',
        params: data
    })
}