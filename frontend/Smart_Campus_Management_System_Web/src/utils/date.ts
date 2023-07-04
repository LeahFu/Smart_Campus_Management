/**
 * @param {date} time time to convert
 * @param {String} fmt format to convert : yyyy-MM-dd、yyyy-MM-dd HH:mm:ss
 */
export function formatTime(time: any, fmt: string) {
    if (!time) return ''
    else {
        const date = new Date(time)
        const o = {
            'M+': date.getMonth() + 1,//getMonth() the return value is an integer between 0 (January) and 11 (December)
            'd+': date.getDate(), // getDate() the return value is an integer between 1 and 31
            'H+': date.getHours(),// hour, returns the hour (0 ~ 23) of the Date object.
            'm+': date.getMinutes(),// minute, returns the minute (0 ~ 59) of the Date object.
            's+': date.getSeconds(), // seconds, returns the seconds (0 ~ 59) of the Date object.
            'q+': Math.floor((date.getMonth() + 3) / 3),// 季度
            'S': date.getMilliseconds() // milliseconds, returns the milliseconds (0 ~ 999) of the Date object.
        }
        // handle year
        // RegExp.$1 refers to the first one that matches the regular expression
        // getFullYear() returns a 4-digit number representing the year
        // The output result is as：2022-MM-dd
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length))

        for (const k in o) {
            if (new RegExp('(' + k + ')').test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : ((
                    '00' + o[k]).substr(('' + o[k]).length)))
            }
        }
        return fmt
    }
}
/**
 * Calculate days
 * @param time
 */
export function calculateDays(time:any) {
    if (!time) return ''
    else {
        let day = Math.floor(new Date().getTime() / 1000) - (new Date(time).getTime() / 1000)
        let day2 = Math.floor(day / (24 * 3600));
        return day2
    }

}
