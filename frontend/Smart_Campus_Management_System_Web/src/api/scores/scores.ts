import request from '../request'
export function getScoresListApi(data:object) {
    return request({
        url: 'scores',
        method: 'get',
        params: data
    })
}