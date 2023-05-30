import request from '../request'

export function getGradeClassListApi(data:object) {
    return request({
        url: 'gradeclass',
        method: 'get',
        params: data
    })
}