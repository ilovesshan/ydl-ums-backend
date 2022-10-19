package com.ilovesshan.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/19
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelBean implements Serializable {

    private static final long serialVersionUID = 7294226506305774427L;

    private String headTextName; //列头（标题）名
    private String propertyName; //对应字段名
    private Integer cols; //合并单元格数
    private XSSFCellStyle cellStyle;

    public ExcelBean(String headTextName, String propertyName, Integer cols) {
        this.headTextName = headTextName;
        this.propertyName = propertyName;
        this.cols = cols;
    }
}
