import request from '../request'

export function getGradeClassListApi(params:object) {
    return request({
        url: 'gradeclass',
        method: 'get',
        params
    })
}
// Add class information
export function addGradeClassApi(data:object) {
    return request({
        url: 'gradeclass',
        method: 'post',
        data
    })
}
// Get class details by ID
export function getGradeClassApi(id:number) {
    return request({
        url: `gradeclass/${id}`,
        method: 'get'
    })
}
// Update class information
export function editGradeClassApi(data:object) {
    return request({
        url: 'gradeclass',
        method: 'put',
        data
    })
}
// Delete class information based on ID
export function deleteGradeClassApi(id:number) {
    return request({
        url: `gradeclass/${id}`,
        method: 'delete'
    })
}