package com.ltp.memo.dao;

import com.ltp.memo.entity.MemoGroup;

import java.util.Date;
import java.util.List;

/**
 * Author: secondriver
 * Created: 2018/6/18
 */
public interface MemoGroupDao {

    /**
     * 插入便签组
     * @param memoGroup
     * @return
     */
    int insetMemoGroup(MemoGroup memoGroup);

    /**
     * 更新便签组的名称
     * @param id
     * @param name
     * @return
     */
    int updateMemoGroup(int id, String name);

    /**
     * 查询id对应的便签组
     * @param id
     * @return
     */
    List<MemoGroup> queryMemoGroup(int id);

    /**
     * 根据时间段查询定义的便签组
     * @param startTime
     * @param endTime
     * @return
     */
    List<MemoGroup> queryMemoGroupByCreatedTime(Date startTime, Date endTime);

    /**
     * 删除便签组
     * @param id
     * @return
     */
    int deleteMemoGroup(int id);

    /**
     * 删除所有便签
     * @return
     */
    int deleteAll();
}
