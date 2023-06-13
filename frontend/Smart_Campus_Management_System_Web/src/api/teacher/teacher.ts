import request from '../request'

// Get teacher list data
export function getTeacherListApi(data:object) {
    return request({
        url: 'teacher',
        method: 'get',
        params: data
    })
}