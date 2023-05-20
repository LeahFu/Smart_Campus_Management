import request from '../request'

// Get user list data
export function getUserListApi(data:object) {
    return request({
        url: 'user',
        method: 'get',
        params: data

    })
}