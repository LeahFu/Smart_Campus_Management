import request from '../request'

// Get teacher list data
export function getTeacherListApi(data:object) {
    return request({
        url: 'teacher',
        method: 'get',
        params: data
    })
}
// Add teacher information
export function addTeacherApi(data:object) {
    return request({
        url: 'teacher',
        method: 'post',
        data
    })
}
// Get a list of all courses
export function getAllCourseListApi() {
    return request({
        url: 'course/all',
        method: 'get'
    })
}
// Get teacher details based on ID
export function getTeacherApi(id:number) {
    return request({
        url: `teacher/${id}`,
        method: 'get'
    })
}