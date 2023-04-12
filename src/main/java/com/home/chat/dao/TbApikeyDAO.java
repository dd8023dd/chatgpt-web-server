package com.home.chat.dao;

import com.home.chat.pojo.entity.TbApikeyEntity;
import com.home.chat.pojo.query.TbApikeyQuery;
import java.util.List;

/**
 * 此类由工具自动生成,重新生成不会覆盖,可以手动修改.
 *
 * 创建日期:2023-04-10 18:06:33 星期一
 */
public interface TbApikeyDAO {
    /**
     * 删除表记录,通过主键id删除记录
     * @param rowId 主键id 
     * @return 影响行数 
     */
    int deleteByPrimaryKey(Long rowId);

    /**
     * 插入表记录,所有字段的插入语句
     * 如果数据库字段不允许为空且没有默认值,该属性必须赋值,否则无法插入.
     * 如果数据库字段允许为空或有默认值,该属性为null时,将插入null.
     * @param entity 表记录实体类 
     * @return 影响行数
     */
    int insert(TbApikeyEntity entity);

    /**
     * 插入表记录,生成属性不为null的语句
     * 如果数据库字段不允许为空又没有默认值,该属性必须赋值,否则无法插入.
     * 如果数据库字段允许为空或有默认值,该属性为null时,将插入默认值.
     * @param entity 表记录实体类 
     * @return 影响行数
     */
    int insertSelective(TbApikeyEntity entity);

    /**
     * 通过主键查询表记录对象
     * @param rowId 主键ID 
     * @return 表记录实体类对象
     */
    TbApikeyEntity selectByPrimaryKey(Long rowId);

    /**
     * 通过主键选择性更新表字段.
     * 如果入参对象属性为null,该字段不更新.
     * @param entity 表记录实体类对象 
     * @return 影响行数
     */
    int updateByPrimaryKeySelective(TbApikeyEntity entity);

    /**
     * 根据查询条件删除表记录
     * @param query 查询条件对象 
     * @return 影响行数
     */
    int deleteByQuery(TbApikeyQuery query);

    /**
     * 通过查询条件查询记录总数
     * @param query 查询条件对象
     * @return 记录总数
     */
    int countByQuery(TbApikeyQuery query);

    /**
     * 分页查询,通过查询条件分页查询表记录列表,默认pageSize:50条.
     * @param query 查询条件对象
     * @return 表记录实体类对象集合list
     */
    List<TbApikeyEntity> queryForPage(TbApikeyQuery query);

    /**
     * 通过查询条件查询表记录列表
     * @param query 查询条件对象
     * @return 表记录实体类对象
     */
    TbApikeyEntity queryForObject(TbApikeyQuery query);
    int useOnece(String apikey);
    /**
     * 通过查询条件查询所有记录,不分页
     * @param query 查询条件对象
     * @return 表记录实体类对象集合list
     */
    List<TbApikeyEntity> queryForList(TbApikeyQuery query);

    /**
     * 批量删除,通过主键list删除一批表记录
     * @param idList 主键ID列表list 
     * @return 影响行数
     */
    int batchDelete(List<Long> idList);

    /**
     * 批量插入,通过实体类List插入一批表记录
     * @param entityList 实体类List 
     * @return int 插入条数
     */
    int batchInsert(List<TbApikeyEntity> entityList);
}