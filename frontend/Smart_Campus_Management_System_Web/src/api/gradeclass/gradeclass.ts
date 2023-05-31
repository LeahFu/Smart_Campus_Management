import request from '../request'

export function getGradeClassListApi(data:object) {
    return request({
        url: 'gradeclass',
        method: 'get',
        params: data
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