import request from '../request'

//Get course list data
export function getCourseListApi(data:object) {
    return request({
        url: 'course',
        method: 'get',
        params: data
    })
}
