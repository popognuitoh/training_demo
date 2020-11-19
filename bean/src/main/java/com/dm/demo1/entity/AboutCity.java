package com.dm.demo1.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 关于城市
 * </p>
 *
 * @author ${author}
 * @since 2020-08-10
 */
@TableName("about_city")
public class AboutCity extends Model<AboutCity> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer cityCode;

    private String cityNameCn;

    private String cityNameEn;

    private String cityDescription;

    private Long cityVideoId;

    private String cityVideoDescription;

    /**
     * 所有照片id，用 “，”分开
     */
    private String cityPicturesIds;

    /**
     * 所有照片路径,用 "," 分开
     */
    private String cityPicturesAddress;

    private Integer order;

    /**
     * 是否正常。0正常，1已删除
     */
    private Integer status;

    /**
     * 是否热城市
     */
    private Boolean isHotCity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityNameCn() {
        return cityNameCn;
    }

    public void setCityNameCn(String cityNameCn) {
        this.cityNameCn = cityNameCn;
    }

    public String getCityNameEn() {
        return cityNameEn;
    }

    public void setCityNameEn(String cityNameEn) {
        this.cityNameEn = cityNameEn;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }

    public Long getCityVideoId() {
        return cityVideoId;
    }

    public void setCityVideoId(Long cityVideoId) {
        this.cityVideoId = cityVideoId;
    }

    public String getCityVideoDescription() {
        return cityVideoDescription;
    }

    public void setCityVideoDescription(String cityVideoDescription) {
        this.cityVideoDescription = cityVideoDescription;
    }

    public String getCityPicturesIds() {
        return cityPicturesIds;
    }

    public void setCityPicturesIds(String cityPicturesIds) {
        this.cityPicturesIds = cityPicturesIds;
    }

    public String getCityPicturesAddress() {
        return cityPicturesAddress;
    }

    public void setCityPicturesAddress(String cityPicturesAddress) {
        this.cityPicturesAddress = cityPicturesAddress;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getHotCity() {
        return isHotCity;
    }

    public void setHotCity(Boolean isHotCity) {
        this.isHotCity = isHotCity;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "AboutCity{" +
        "id=" + id +
        ", cityCode=" + cityCode +
        ", cityNameCn=" + cityNameCn +
        ", cityNameEn=" + cityNameEn +
        ", cityDescription=" + cityDescription +
        ", cityVideoId=" + cityVideoId +
        ", cityVideoDescription=" + cityVideoDescription +
        ", cityPicturesIds=" + cityPicturesIds +
        ", cityPicturesAddress=" + cityPicturesAddress +
        ", order=" + order +
        ", status=" + status +
        ", isHotCity=" + isHotCity +
        "}";
    }
}
