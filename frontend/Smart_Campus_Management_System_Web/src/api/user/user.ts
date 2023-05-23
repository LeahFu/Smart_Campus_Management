import request from '../request'

// Get user list data
export function getUserListApi(data:object) {
    return request({
        url: 'user',
        method: 'get',
        params: data

    })
}
// Add user information
export function addUserApi(data:object) {
    return request({
        url: 'user',
        method: 'post',
        data
    })
}
// Get user details by id
export function getUserApi(id:number) {
    return request({
        url: `user/${id}`,
        method: 'get'
    })
}