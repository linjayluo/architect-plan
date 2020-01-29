package com.llj.cloud.lambda123;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class OnlineCntDO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 统计时间
     */
    private Date stateTime;


    /**
     * 平台，1:2.0 2:3.0
     */
    private Integer platform;


    /**
     * 大品类
     */
    private String category;


    /**
     * 型号
     */
    private String devType;

    /**
     * 数量值
     */
    private Integer value;


    /**
     * 创建时间
     */
    private Date createdTime;


    /**
     * 修改时间
     */
    private Date updatedTime;


    /**
     * 逻辑删除
     */
    private Integer enable = 1;

    public OnlineCntDO(String category, Integer platform, int onlineNum) {
        this.category = category;
        this.platform = platform;
        this.value = onlineNum;
    }

    public OnlineCntDO init() {
        this.createdTime = new Date();
        this.updatedTime = createdTime;
        return this;
    }

    public OnlineCntDO update() {
        this.updatedTime = new Date();
        return this;
    }

}
