package com.dm.demo1.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2020-07-31
 */
@TableName("world_countries_cities")
public class WorldCountriesCities extends Model<WorldCountriesCities> {

private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String cityCode;

    private String cityEn;

    private String cityCn;

    private String countryCode;

    private String countryEn;

    private String countryCn;

    private String continentCode;

    private String continentEn;

    private String continentCn;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityEn() {
        return cityEn;
    }

    public void setCityEn(String cityEn) {
        this.cityEn = cityEn;
    }

    public String getCityCn() {
        return cityCn;
    }

    public void setCityCn(String cityCn) {
        this.cityCn = cityCn;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryEn() {
        return countryEn;
    }

    public void setCountryEn(String countryEn) {
        this.countryEn = countryEn;
    }

    public String getCountryCn() {
        return countryCn;
    }

    public void setCountryCn(String countryCn) {
        this.countryCn = countryCn;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public String getContinentEn() {
        return continentEn;
    }

    public void setContinentEn(String continentEn) {
        this.continentEn = continentEn;
    }

    public String getContinentCn() {
        return continentCn;
    }

    public void setContinentCn(String continentCn) {
        this.continentCn = continentCn;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "WorldCountriesCities{" +
        "id=" + id +
        ", cityCode=" + cityCode +
        ", cityEn=" + cityEn +
        ", cityCn=" + cityCn +
        ", countryCode=" + countryCode +
        ", countryEn=" + countryEn +
        ", countryCn=" + countryCn +
        ", continentCode=" + continentCode +
        ", continentEn=" + continentEn +
        ", continentCn=" + continentCn +
        "}";
    }
}
