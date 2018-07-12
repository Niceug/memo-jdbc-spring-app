package com.ltp.memo.dao.impl;
import com.ltp.memo.dao.MemoGroupDao;
import com.ltp.memo.DaoCompoment;
import com.ltp.memo.entity.MemoGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class MemoGroupDaoImpl implements MemoGroupDao {


    private DaoCompoment daoCompoment;
    private Connection conn;

    @Autowired
    public MemoGroupDaoImpl(DaoCompoment daoCompoment) {
        this.daoCompoment = daoCompoment;
        this.conn = daoCompoment.getConnection();
        if(this.conn == null) {
            throw new RuntimeException("Connection Failure");
        }
    }

    @Override
    public int insetMemoGroup(MemoGroup memoGroup) {
        if(memoGroup == null) {
            return 0;
        }

        String sql = "insert into memo_group(name, created_time) values(?, ?);";
        PreparedStatement ps = daoCompoment.getStatement(conn, sql);
        if(ps == null) {
            return 0;
        }
        try {
            ps.setString(1, memoGroup.getName());
            ps.setString(2, new java.sql.Date(memoGroup.getCreatedTime().getTime()).toString());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoCompoment.closeResource(null, ps, null);
        }
        return 0;
    }

    @Override
    public int updateMemoGroup(int id, String name) {
        String sql = "update memo_group set name = ? where id = ?;";
        PreparedStatement ps = daoCompoment.getStatement(conn, sql);
        if(ps == null) {
            return 0;
        }
        try {
            ps.setString(1, name);
            ps.setInt(2, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoCompoment.closeResource(null, ps, null);
        }

        return 0;
    }

    @Override
    public List<MemoGroup> queryMemoGroup(int id) {
        List<MemoGroup> list = new ArrayList<>();

        String sql = "select id, name, created_time, modify_time from memo_group where id = ?;";
        PreparedStatement ps = daoCompoment.getStatement(this.conn, sql);

        if(ps == null) {
            return list;
        }

        ResultSet rs = null;
        try {
            ps.setInt(1, id);

            rs = ps.executeQuery();
            return handleResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoCompoment.closeResource(rs, ps, null);
        }
        return list;
    }

    public List<MemoGroup> handleResultSet(ResultSet rs) {
        List<MemoGroup> list = new ArrayList<>();
        if(rs != null) {
            try{
                while(rs.next()) {
                    MemoGroup memoGroup = new MemoGroup();
                    memoGroup.setId(rs.getInt("id"));
                    memoGroup.setName(rs.getString("name"));
                    memoGroup.setCreatedTime(rs.getDate("created_time"));
                    memoGroup.setModifyTime(rs.getDate("modify_time"));
                    list.add(memoGroup);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return list;

    }

    @Override
    public List<MemoGroup> queryMemoGroupByCreatedTime(Date startTime, Date endTime) {
        List<MemoGroup> list = new ArrayList<>();
        String sql = "select id, name, created_time, modify_time from memo_group where created_time between ? and ?;";
        PreparedStatement ps = daoCompoment.getStatement(this.conn, sql);

        if(ps == null || startTime == null || endTime == null) {
            return list;
        }
        ResultSet rs = null;
        try {
            ps.setDate(1, new java.sql.Date(startTime.getTime()));
            ps.setDate(2, new java.sql.Date(endTime.getTime()));

            rs = ps.executeQuery();

            return handleResultSet(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            daoCompoment.closeResource(rs, ps, null);
        }
        return list;
    }

    @Override
    public int deleteMemoGroup(int id) {

        String sql = "delete from memo_group where id = ?";
        PreparedStatement ps = daoCompoment.getStatement(conn, sql);
        if(ps == null) {
            return 0;
        }
        try {
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoCompoment.closeResource(null, ps, null);
        }

        return 0;
    }

    @Override
    public int deleteAll() {
        String sql = "delete from memo_group";
        PreparedStatement ps = daoCompoment.getStatement(conn, sql);
        if(ps == null)
            return 0;

        try {
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            daoCompoment.closeResource(null, ps, null);
        }

        return 0;
    }


    /**
     * 关闭连接
     */

    @Override
    public void finalize() {
        try {
            if(conn != null) {
                this.conn.close();
                System.out.println("连接断开");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
