import ExcelJS from 'exceljs'

export const autoWidthAction = (val,width=10)=> {
    if(val==null){
        width = 10
    }else if(val.toString().charCodeAt(0)>255){
        width = val.toString().length * 2;
    }else {
        width = val.toString().length;
    }
}
// Export Excel
export const exportExcel = async ({column,data,filename,autoWidth,format})=> {
    // Create excel workbook
    const workbook = new ExcelJS.Workbook()
    // Set workbook properties
    workbook.creator = 'Me'
    workbook.title = filename
    workbook.created = new Date()
    workbook.modified = new Date()
    // Add worksheet
    const worksheet = workbook.addWorksheet(filename)
    // set column name
    const columnsName = []
    column.forEach((item,index)=>{
        const obj = {
            header: item.label,
            key: item.name,
            width:null
        }
        if(autoWidth){
            const maxArr = [autoWidthAction(item.label)]
            data.forEach(ite=> {
                const str = ite[item.name] || ''
                if(str){
                    maxArr.push(autoWidthAction(str))
                }
            })
            obj.width = Math.max(...maxArr)+5
        }
        columnsName.push(obj)
    })
    worksheet.columns = columnsName
}