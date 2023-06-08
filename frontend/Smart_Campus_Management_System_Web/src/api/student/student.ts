import request from '../request'

// Get student list data
export function getStudentListApi(data:object) {
    return request({
        url: 'student',
        method: 'get',
        params: data
    })
}
// Add student information
export function addStudentApi(data:object) {
    return request({
        url: 'student',
        method: 'post',
        data
    })
}
// Get a list of all classes
export function gradeClassListApi() {
    return request({
        url: 'gradeclass/all',
        method: 'get'
    })
}
// Get student details based on ID
export function getStudentApi(id:number) {
    return request({
        url: `student/${id}`,
        method: 'get'
    })
}
// Update student information
export function editStudentApi(data:object) {
    return request({
        url: 'student',
        method: 'put',
        data
    })
}
// Delete student information based on ID
export function deleteStudentApi(id:number) {
    return request({
        url: `student/${id}`,
        method: 'delete'
    })
}