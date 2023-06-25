import request from '../request'
export function getScoreCensusApi(courseId:number,gradeClassId:number) {
    return request({
        url: 'scores/getScoreCensus',
        method: 'get',
        params: {
            courseId,
            gradeClassId
        }
    })
}