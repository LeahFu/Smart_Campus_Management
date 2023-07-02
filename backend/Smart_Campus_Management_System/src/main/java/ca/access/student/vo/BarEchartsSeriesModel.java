package ca.access.student.vo;

import java.util.List;

/**
 * @author: Lei Fu
 * @date: 2023/06/29
 * @description: Column chart returns result set object
 */
public class BarEchartsSeriesModel {
    private List<Double> data;
    private String type;
    private String name;

   /** public void setData(List<Double> averageList) {
    }

    public void setType(String bar) {
    }

    public void setName(String averageScore) {
    }**/
    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
