package com.llj.cloud;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PackageName:   com.llj.cloud
 * projectName:   architect-plan
 * Description:   luolinjie 补充
 * Creator:     by luolinjie
 * Create_Date: 2019/9/14 19:48
 * Updater:     by luolinjie
 * Update_Date: 2019/9/14
 * Update_Description: luolinjie 补充
 **/
@Data
@NoArgsConstructor
public class RowModel implements Serializable {
    String classifyType;
    Date countDate;
    int year;
    int month;
    int dayOfMonth;
    int hour;
    long countValue;
    int id;

    public RowModel(Date dateUnit, String typeUnit, long count1) {
        this.countDate =  dateUnit;
        this.year = dateUnit.getYear();
        this.month = dateUnit.getMonth()-1;
        this.dayOfMonth = dateUnit.getDate();
        this.hour = dateUnit.getHours();
        this.classifyType =  typeUnit;
        this.countValue = count1;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "RowModel{" +
                "classifyType='" + classifyType + '\'' +
                ", countDate=" + sdf.format(countDate) +
                ", countValue=" + countValue +
                '}';
    }
}
