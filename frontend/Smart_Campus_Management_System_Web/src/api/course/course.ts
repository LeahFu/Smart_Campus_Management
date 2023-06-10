import request from '../request'

//Get course list data
export function getCourseListApi(data:object) {
    return request({
        url: 'course',
        method: 'get',
        params: data
    })
}
// Add course information
export function addCourseApi(data:object) {
    return request({
        url: 'course',
        method: 'post',
        data
    })
}
