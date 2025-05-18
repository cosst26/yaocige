package com.company.project.model;

import com.company.project.common.PageParam;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_zodiac")
public class Zodiac extends PageParam {
    /**
     * 记录ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 创建时间
     */
    @Column(name = "created_at")
    private Date createdAt;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新时间
     */
    @Column(name = "updated_at")
    private Date updatedAt;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 是否删除(0false未删除 1true已删除)
     */
    @Column(name = "is_delete")
    private Boolean isDelete;

    /**
     * 版本号
     */
    private Integer version;

    /**
     * 业务状态
     */
    private Integer status;

    /**
     * 生肖名称
     */
    private String name;

    /**
     * 用户档案ID
     */
    @Column(name = "user_profile_id")
    private Long userProfileId;

    /**
     * 图片
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 描述
     */
    @Column(name = "zodiac_desc")
    private String zodiacDesc;

    /**
     * 属相生辰
     */
    @Column(name = "zodiac_birth")
    private String zodiacBirth;

    /**
     * 紫薇描述
     */
    @Column(name = "ziwei_desc")
    private String ziweiDesc;

    /**
     * 优点
     */
    @Column(name = "advantage_desc")
    private String advantageDesc;

    /**
     * 缺点
     */
    @Column(name = "disad_desc")
    private String disadDesc;

    /**
     * 事业
     */
    @Column(name = "career_desc")
    private String careerDesc;

    /**
     * 财富
     */
    @Column(name = "wealth_desc")
    private String wealthDesc;

    /**
     * 爱情
     */
    @Column(name = "love_desc")
    private String loveDesc;

    /**
     * 获取记录ID
     *
     * @return id - 记录ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置记录ID
     *
     * @param id 记录ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     *
     * @return created_at - 创建时间
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置创建时间
     *
     * @param createdAt 创建时间
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新时间
     *
     * @return updated_at - 更新时间
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置更新时间
     *
     * @param updatedAt 更新时间
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取修改人
     *
     * @return updated_by - 修改人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置修改人
     *
     * @param updatedBy 修改人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取是否删除(0false未删除 1true已删除)
     *
     * @return is_delete - 是否删除(0false未删除 1true已删除)
     */
    public Boolean getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除(0false未删除 1true已删除)
     *
     * @param isDelete 是否删除(0false未删除 1true已删除)
     */
    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取版本号
     *
     * @return version - 版本号
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * 设置版本号
     *
     * @param version 版本号
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * 获取业务状态
     *
     * @return status - 业务状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置业务状态
     *
     * @param status 业务状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取生肖名称
     *
     * @return name - 生肖名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置生肖名称
     *
     * @param name 生肖名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取用户档案ID
     *
     * @return user_profile_id - 用户档案ID
     */
    public Long getUserProfileId() {
        return userProfileId;
    }

    /**
     * 设置用户档案ID
     *
     * @param userProfileId 用户档案ID
     */
    public void setUserProfileId(Long userProfileId) {
        this.userProfileId = userProfileId;
    }

    /**
     * 获取图片
     *
     * @return img_url - 图片
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片
     *
     * @param imgUrl 图片
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取描述
     *
     * @return zodiac_desc - 描述
     */
    public String getZodiacDesc() {
        return zodiacDesc;
    }

    /**
     * 设置描述
     *
     * @param zodiacDesc 描述
     */
    public void setZodiacDesc(String zodiacDesc) {
        this.zodiacDesc = zodiacDesc;
    }

    /**
     * 获取属相生辰
     *
     * @return zodiac_birth - 属相生辰
     */
    public String getZodiacBirth() {
        return zodiacBirth;
    }

    /**
     * 设置属相生辰
     *
     * @param zodiacBirth 属相生辰
     */
    public void setZodiacBirth(String zodiacBirth) {
        this.zodiacBirth = zodiacBirth;
    }

    /**
     * 获取紫薇描述
     *
     * @return ziwei_desc - 紫薇描述
     */
    public String getZiweiDesc() {
        return ziweiDesc;
    }

    /**
     * 设置紫薇描述
     *
     * @param ziweiDesc 紫薇描述
     */
    public void setZiweiDesc(String ziweiDesc) {
        this.ziweiDesc = ziweiDesc;
    }

    /**
     * 获取优点
     *
     * @return advantage_desc - 优点
     */
    public String getAdvantageDesc() {
        return advantageDesc;
    }

    /**
     * 设置优点
     *
     * @param advantageDesc 优点
     */
    public void setAdvantageDesc(String advantageDesc) {
        this.advantageDesc = advantageDesc;
    }

    /**
     * 获取缺点
     *
     * @return disad_desc - 缺点
     */
    public String getDisadDesc() {
        return disadDesc;
    }

    /**
     * 设置缺点
     *
     * @param disadDesc 缺点
     */
    public void setDisadDesc(String disadDesc) {
        this.disadDesc = disadDesc;
    }

    /**
     * 获取事业
     *
     * @return career_desc - 事业
     */
    public String getCareerDesc() {
        return careerDesc;
    }

    /**
     * 设置事业
     *
     * @param careerDesc 事业
     */
    public void setCareerDesc(String careerDesc) {
        this.careerDesc = careerDesc;
    }

    /**
     * 获取财富
     *
     * @return wealth_desc - 财富
     */
    public String getWealthDesc() {
        return wealthDesc;
    }

    /**
     * 设置财富
     *
     * @param wealthDesc 财富
     */
    public void setWealthDesc(String wealthDesc) {
        this.wealthDesc = wealthDesc;
    }

    /**
     * 获取爱情
     *
     * @return love_desc - 爱情
     */
    public String getLoveDesc() {
        return loveDesc;
    }

    /**
     * 设置爱情
     *
     * @param loveDesc 爱情
     */
    public void setLoveDesc(String loveDesc) {
        this.loveDesc = loveDesc;
    }
}