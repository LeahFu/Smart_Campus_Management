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
// Refresh user information
export function editUserApi(data:object) {
    return request({
        url: 'user',
        method: 'put',
        data
    })
}
// Delete user information based on ID
export function deleteUserApi(id:number) {
    return request({
        url: `user/${id}`,
        method: 'delete'
    })
}
// Update personal information
export function updateInfoApi(data:object) {
    return request({
        url: 'user/updateInfo',
        method: 'put',
        data
    })
}
// Send the verification code
export function sendEmailApi(email:string) {
    return request({
        url: 'user/sendEmail',
        method: 'get',
        params: {
            email
        }
    })
}
// Verify that the verification code entered by the user is correct
export function verifyCodeApi(code:string) {
    return request({
        url: 'user/verifyCode',
        method: 'get',
        params: {
            code
        }

    })
}

// Change bound email
export function updateEmailApi(email:string,code:number) {
    return request({
        url: 'user/updateEmail',
        method: 'put',
        params: {
            email,
            code
        }

    })
}