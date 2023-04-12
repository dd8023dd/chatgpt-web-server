package com.home.chat.pojo.entity;

/**
 *
 * 此类由工具自动生成,重新生成后该类将被覆盖,请勿手动修改!
 * 对应表: tb_user_key
 *
 * 创建日期:2023-04-10 18:06:33 星期一
 */
public class TbUserKeyEntity {

    /**
     *
     *  column tb_user_key.row_id
     */
    private Long rowId;

    /**
     *
     *  column tb_user_key.create_date
     */
    private Long createDate;

    /**
     *
     *  column tb_user_key.create_time
     */
    private Long createTime;

    /**
     *
     *  column tb_user_key.update_date
     */
    private Long updateDate;

    /**
     *
     *  column tb_user_key.update_time
     */
    private Long updateTime;

    /**
     *
     *  column tb_user_key.update_times
     */
    private Long updateTimes;

    /**
     * user key;
     *
     *  column tb_user_key.user_key
     */
    private String userKey;

    /**
     * 剩余次数;
     *
     *  column tb_user_key.remaining_count
     */
    private Long remainingCount;

    /**
     * 失效日期
     *
     *  column tb_user_key.expire_date
     */
    private Long expireDate;

    /**
     * 使用次数
     *
     *  column tb_user_key.use_times
     */
    private Long useTimes;

    /**
     * 正常-1,失效-2,未激活-3
     *
     *  column tb_user_key.valid_status
     */
    private String validStatus;

    /**
     * 绑定IP
     *
     *  column tb_user_key.bind_ip
     */
    private String bindIp;

    /**
     * 绑定手机
     *
     *  column tb_user_key.bind_phone
     */
    private String bindPhone;

    /**
     * row_id
     *
     * @return the value of tb_user_key.row_id
     */
    public Long getRowId() {
        return rowId;
    }

    /**
     * row_id
     *
     * @param rowId the value for tb_user_key.row_id
     */
    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    /**
     * create_date
     *
     * @return the value of tb_user_key.create_date
     */
    public Long getCreateDate() {
        return createDate;
    }

    /**
     * create_date
     *
     * @param createDate the value for tb_user_key.create_date
     */
    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    /**
     * create_time
     *
     * @return the value of tb_user_key.create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * create_time
     *
     * @param createTime the value for tb_user_key.create_time
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * update_date
     *
     * @return the value of tb_user_key.update_date
     */
    public Long getUpdateDate() {
        return updateDate;
    }

    /**
     * update_date
     *
     * @param updateDate the value for tb_user_key.update_date
     */
    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * update_time
     *
     * @return the value of tb_user_key.update_time
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * update_time
     *
     * @param updateTime the value for tb_user_key.update_time
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * update_times
     *
     * @return the value of tb_user_key.update_times
     */
    public Long getUpdateTimes() {
        return updateTimes;
    }

    /**
     * update_times
     *
     * @param updateTimes the value for tb_user_key.update_times
     */
    public void setUpdateTimes(Long updateTimes) {
        this.updateTimes = updateTimes;
    }

    /**
     * user key;
     *
     * @return the value of tb_user_key.user_key
     */
    public String getUserKey() {
        return userKey;
    }

    /**
     * user key;
     *
     * @param userKey the value for tb_user_key.user_key
     */
    public void setUserKey(String userKey) {
        this.userKey = userKey == null ? null : userKey.trim();
    }

    /**
     * 剩余次数;
     *
     * @return the value of tb_user_key.remaining_count
     */
    public Long getRemainingCount() {
        return remainingCount;
    }

    /**
     * 剩余次数;
     *
     * @param remainingCount the value for tb_user_key.remaining_count
     */
    public void setRemainingCount(Long remainingCount) {
        this.remainingCount = remainingCount;
    }

    /**
     * 失效日期
     *
     * @return the value of tb_user_key.expire_date
     */
    public Long getExpireDate() {
        return expireDate;
    }

    /**
     * 失效日期
     *
     * @param expireDate the value for tb_user_key.expire_date
     */
    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 使用次数
     *
     * @return the value of tb_user_key.use_times
     */
    public Long getUseTimes() {
        return useTimes;
    }

    /**
     * 使用次数
     *
     * @param useTimes the value for tb_user_key.use_times
     */
    public void setUseTimes(Long useTimes) {
        this.useTimes = useTimes;
    }

    /**
     * 正常-1,失效-2,未激活-3
     *
     * @return the value of tb_user_key.valid_status
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * 正常-1,失效-2,未激活-3
     *
     * @param validStatus the value for tb_user_key.valid_status
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }

    /**
     * 绑定IP
     *
     * @return the value of tb_user_key.bind_ip
     */
    public String getBindIp() {
        return bindIp;
    }

    /**
     * 绑定IP
     *
     * @param bindIp the value for tb_user_key.bind_ip
     */
    public void setBindIp(String bindIp) {
        this.bindIp = bindIp == null ? null : bindIp.trim();
    }

    /**
     * 绑定手机
     *
     * @return the value of tb_user_key.bind_phone
     */
    public String getBindPhone() {
        return bindPhone;
    }

    /**
     * 绑定手机
     *
     * @param bindPhone the value for tb_user_key.bind_phone
     */
    public void setBindPhone(String bindPhone) {
        this.bindPhone = bindPhone == null ? null : bindPhone.trim();
    }
}