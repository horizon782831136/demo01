package com.liuwei.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ShowMusicVo implements Serializable {
    /**
     * 音乐id
     */
    @TableId
    private Long musicId;
    /**
     * 音乐名
     */
    private String name;
    /**
     * 音乐路径
     */
    private String musicLoc;
    /**
     * 备注
     */
    private String remark;
}
