import request from '../request'
export function getScoresListApi(data:object) {
    return request({
        url: 'scores',
        method: 'get',
        params: data
    })
}
// Register course grades
export function registerScoresApi(gradeClassId:number,courseId:number) {
    return request({
        url: 'scores',
        method: 'post',
        data: {
            gradeClassId: gradeClassId,
            courseId: courseId
        }
    })
}