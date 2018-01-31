package indi.dwq.orderingSys.data.dao.sql;

import indi.dwq.orderingSys.data.pojo.Orderdetail;
import org.apache.ibatis.jdbc.SQL;

public class OrderdetailSqlProvider {

    public String insertSelective(Orderdetail record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("orderdetailtbl");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getMenuid() != null) {
            sql.VALUES("menuID", "#{menuid,jdbcType=INTEGER}");
        }
        
        if (record.getOrderid() != null) {
            sql.VALUES("orderID", "#{orderid,jdbcType=INTEGER}");
        }
        
        if (record.getNum() != null) {
            sql.VALUES("num", "#{num,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Orderdetail record) {
        SQL sql = new SQL();
        sql.UPDATE("orderdetailtbl");
        
        if (record.getMenuid() != null) {
            sql.SET("menuID = #{menuid,jdbcType=INTEGER}");
        }
        
        if (record.getOrderid() != null) {
            sql.SET("orderID = #{orderid,jdbcType=INTEGER}");
        }
        
        if (record.getNum() != null) {
            sql.SET("num = #{num,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}