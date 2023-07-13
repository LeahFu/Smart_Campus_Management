import request from '../request'
export function getScoresListApi(params:object) {
    return request({
        url: 'scores',
        method: 'get',
        params
    })
}
// Register course grades
export function registerScoresApi(gradeClassId:number,courseId:number) {
    return request({
        url: 'scores',
        method: 'post',
        data: {
            gradeClassId,
            courseId
        }
    })
}
// Refresh grades
export function editScoresApi(id:number,score: number) {
    return request({
        url: 'scores',
        method: 'put',
        data: {
            id:id,
            score:score
        }
    })
}
// Delete grade information based on ID
export function deleteScoresApi(id:number) {
    return request({
        url: `scores/${id}`,
        method: 'delete'
    })
}