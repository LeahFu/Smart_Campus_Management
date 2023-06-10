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
// Get course information by id
export function getCourseApi(id:number) {
    return request({
        url: `course/${id}`,
        method: 'get'
    })
}
