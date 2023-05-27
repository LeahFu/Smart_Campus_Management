import request from '../request'
// Get role list data
export function getRoleListApi(data:object) {
    return request({
        url: 'role',
        method: 'get',
        params: data
    })
}
// Add role
export function addRoleApi(data:object) {
    return request({
        url: 'role',
        method: 'post',
        data
    })
}