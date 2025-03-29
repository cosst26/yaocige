package com.company.project.model;

import com.company.project.common.PageParam;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_dialogue_details")
public class DialogueDetails extends PageParam {
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
     * 列表ID
     */
    @Column(name = "dialogue_id")
    private Long dialogueId;

    /**
     * 发送人id
     */
    @Column(name = "sender_id")
    private Long senderId;

    /**
     * 发送人姓名
     */
    @Column(name = "sender_name")
    private String senderName;

    /**
     * 发送人头像
     */
    @Column(name = "sender_avatar")
    private String senderAvatar;

    /**
     * 接收人id
     */
    @Column(name = "recipient_id")
    private Long recipientId;

    /**
     * 接收人姓名
     */
    @Column(name = "recipient_name")
    private String recipientName;

    /**
     * 接收人头像
     */
    @Column(name = "recipient_avatar")
    private String recipientAvatar;

    /**
     * 内容
     */
    private String content;
    /**
     * 1我的(右边);2别人(左边)
     */
    @Transient
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

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
     * 获取列表ID
     *
     * @return dialogue_id - 列表ID
     */
    public Long getDialogueId() {
        return dialogueId;
    }

    /**
     * 设置列表ID
     *
     * @param dialogueId 列表ID
     */
    public void setDialogueId(Long dialogueId) {
        this.dialogueId = dialogueId;
    }

    /**
     * 获取发送人id
     *
     * @return sender_id - 发送人id
     */
    public Long getSenderId() {
        return senderId;
    }

    /**
     * 设置发送人id
     *
     * @param senderId 发送人id
     */
    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    /**
     * 获取发送人姓名
     *
     * @return sender_name - 发送人姓名
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * 设置发送人姓名
     *
     * @param senderName 发送人姓名
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * 获取发送人头像
     *
     * @return sender_avatar - 发送人头像
     */
    public String getSenderAvatar() {
        return senderAvatar;
    }

    /**
     * 设置发送人头像
     *
     * @param senderAvatar 发送人头像
     */
    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    /**
     * 获取接收人id
     *
     * @return recipient_id - 接收人id
     */
    public Long getRecipientId() {
        return recipientId;
    }

    /**
     * 设置接收人id
     *
     * @param recipientId 接收人id
     */
    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }

    /**
     * 获取接收人姓名
     *
     * @return recipient_name - 接收人姓名
     */
    public String getRecipientName() {
        return recipientName;
    }

    /**
     * 设置接收人姓名
     *
     * @param recipientName 接收人姓名
     */
    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    /**
     * 获取接收人头像
     *
     * @return recipient_avatar - 接收人头像
     */
    public String getRecipientAvatar() {
        return recipientAvatar;
    }

    /**
     * 设置接收人头像
     *
     * @param recipientAvatar 接收人头像
     */
    public void setRecipientAvatar(String recipientAvatar) {
        this.recipientAvatar = recipientAvatar;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}