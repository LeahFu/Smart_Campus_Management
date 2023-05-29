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
// Get role details by id
export function getRoleApi(id:number) {
    return request({
        url: `role/${id}`,
        method: 'get'
    })
}
// Update role information
export function editRoleApi(data:object) {
    return request({
        url: 'role',
        method: 'put',
        data
    })
}
// Delete role information based on ID
export function deleteRoleApi(id:number) {
    return request({
        url: `role/${id}`,
        method: 'delete'
    })
}