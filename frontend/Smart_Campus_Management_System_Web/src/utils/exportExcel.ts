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
    console.log('data----------:',data)
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
    console.log('column----------:',column)
    for (let item in column) {
        console.log('item----------:',item)
    }
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
    // Add row
    worksheet.addRows(data)
    // Write to file
    const uint8Array =
        format === "xlsx"
            ? await workbook.xlsx.writeBuffer()
            : await workbook.csv.writeBuffer()
    const blob = new Blob([uint8Array],{type: 'application/octet-binary'})
    // Determine whether to allow users to save files on the client
    if(window.navigator.msSaveOrOpenBlob){
        // msSaveOrOpenBlob return boolean
        navigator.msSaveBlob(blob, filename + `.${format}`);
        // Save locally
    }else {
        // Download label "a"
        const link = document.createElement("a");
        // The href attribute specifies the download link
        link.href = window.URL.createObjectURL(blob);
        // The download attribute specifies the file name
        link.download = filename + `.${format}`;
        // The click() event triggers the download
        link.click();
        // Free memory
        window.URL.revokeObjectURL(link.href);
    }
}