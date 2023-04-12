package com.home.chat.pojo.entity;

/**
 *
 * 此类由工具自动生成,重新生成后该类将被覆盖,请勿手动修改!
 * 对应表: tb_apikey
 *
 * 创建日期:2023-04-10 18:06:33 星期一
 */
public class TbApikeyEntity {

    /**
     *
     *  column tb_apikey.row_id
     */
    private Long rowId;

    /**
     *
     *  column tb_apikey.create_date
     */
    private Long createDate;

    /**
     *
     *  column tb_apikey.create_time
     */
    private Long createTime;

    /**
     *
     *  column tb_apikey.update_date
     */
    private Long updateDate;

    /**
     *
     *  column tb_apikey.update_time
     */
    private Long updateTime;

    /**
     *
     *  column tb_apikey.update_times
     */
    private Long updateTimes;

    /**
     * api key;
     *
     *  column tb_apikey.api_key
     */
    private String apiKey;

    /**
     * 余额;
     *
     *  column tb_apikey.balance
     */
    private String balance;

    /**
     * 失效日期
     *
     *  column tb_apikey.expire_date
     */
    private Long expireDate;

    /**
     * 使用次数
     *
     *  column tb_apikey.use_times
     */
    private Long useTimes;

    /**
     * 正常-1,失效-2
     *
     *  column tb_apikey.valid_status
     */
    private String validStatus;

    /**
     * row_id
     *
     * @return the value of tb_apikey.row_id
     */
    public Long getRowId() {
        return rowId;
    }

    /**
     * row_id
     *
     * @param rowId the value for tb_apikey.row_id
     */
    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    /**
     * create_date
     *
     * @return the value of tb_apikey.create_date
     */
    public Long getCreateDate() {
        return createDate;
    }

    /**
     * create_date
     *
     * @param createDate the value for tb_apikey.create_date
     */
    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    /**
     * create_time
     *
     * @return the value of tb_apikey.create_time
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * create_time
     *
     * @param createTime the value for tb_apikey.create_time
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * update_date
     *
     * @return the value of tb_apikey.update_date
     */
    public Long getUpdateDate() {
        return updateDate;
    }

    /**
     * update_date
     *
     * @param updateDate the value for tb_apikey.update_date
     */
    public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * update_time
     *
     * @return the value of tb_apikey.update_time
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * update_time
     *
     * @param updateTime the value for tb_apikey.update_time
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * update_times
     *
     * @return the value of tb_apikey.update_times
     */
    public Long getUpdateTimes() {
        return updateTimes;
    }

    /**
     * update_times
     *
     * @param updateTimes the value for tb_apikey.update_times
     */
    public void setUpdateTimes(Long updateTimes) {
        this.updateTimes = updateTimes;
    }

    /**
     * api key;
     *
     * @return the value of tb_apikey.api_key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * api key;
     *
     * @param apiKey the value for tb_apikey.api_key
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey == null ? null : apiKey.trim();
    }

    /**
     * 余额;
     *
     * @return the value of tb_apikey.balance
     */
    public String getBalance() {
        return balance;
    }

    /**
     * 余额;
     *
     * @param balance the value for tb_apikey.balance
     */
    public void setBalance(String balance) {
        this.balance = balance == null ? null : balance.trim();
    }

    /**
     * 失效日期
     *
     * @return the value of tb_apikey.expire_date
     */
    public Long getExpireDate() {
        return expireDate;
    }

    /**
     * 失效日期
     *
     * @param expireDate the value for tb_apikey.expire_date
     */
    public void setExpireDate(Long expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 使用次数
     *
     * @return the value of tb_apikey.use_times
     */
    public Long getUseTimes() {
        return useTimes;
    }

    /**
     * 使用次数
     *
     * @param useTimes the value for tb_apikey.use_times
     */
    public void setUseTimes(Long useTimes) {
        this.useTimes = useTimes;
    }

    /**
     * 正常-1,失效-2
     *
     * @return the value of tb_apikey.valid_status
     */
    public String getValidStatus() {
        return validStatus;
    }

    /**
     * 正常-1,失效-2
     *
     * @param validStatus the value for tb_apikey.valid_status
     */
    public void setValidStatus(String validStatus) {
        this.validStatus = validStatus == null ? null : validStatus.trim();
    }
}