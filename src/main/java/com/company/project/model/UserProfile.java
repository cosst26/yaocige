package com.company.project.model;

import com.company.project.common.PageParam;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_user_profile")
public class UserProfile extends PageParam {
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
     * 昵称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 关系
     */
    private String relationship;

    /**
     * 性别(0女;1男)
     */
    private Integer sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 出生地点
     */
    private String place;

    /**
     * 现居地
     */
    private String residence;

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
     * 获取昵称
     *
     * @return user_name - 昵称
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置昵称
     *
     * @param userName 昵称
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取关系
     *
     * @return relationship - 关系
     */
    public String getRelationship() {
        return relationship;
    }

    /**
     * 设置关系
     *
     * @param relationship 关系
     */
    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    /**
     * 获取性别(0女;1男)
     *
     * @return sex - 性别(0女;1男)
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别(0女;1男)
     *
     * @param sex 性别(0女;1男)
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取出生日期
     *
     * @return birthday - 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 设置出生日期
     *
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取出生地点
     *
     * @return place - 出生地点
     */
    public String getPlace() {
        return place;
    }

    /**
     * 设置出生地点
     *
     * @param place 出生地点
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * 获取现居地
     *
     * @return residence - 现居地
     */
    public String getResidence() {
        return residence;
    }

    /**
     * 设置现居地
     *
     * @param residence 现居地
     */
    public void setResidence(String residence) {
        this.residence = residence;
    }
}