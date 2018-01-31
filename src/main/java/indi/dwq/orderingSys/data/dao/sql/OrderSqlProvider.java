package indi.dwq.orderingSys.data.dao.sql;

import indi.dwq.orderingSys.data.pojo.Order;
import org.apache.ibatis.jdbc.SQL;

public class OrderSqlProvider {

    public String insertSelective(Order record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("ordertbl");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTableid() != null) {
            sql.VALUES("TableID", "#{tableid,jdbcType=INTEGER}");
        }
        
        if (record.getOrdertime() != null) {
            sql.VALUES("OrderTime", "#{ordertime,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            sql.VALUES("UserID", "#{userid,jdbcType=INTEGER}");
        }
        
        if (record.getPersonnum() != null) {
            sql.VALUES("personNum", "#{personnum,jdbcType=INTEGER}");
        }
        
        if (record.getIdpay() != null) {
            sql.VALUES("idPay", "#{idpay,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.VALUES("remark", "#{remark,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Order record) {
        SQL sql = new SQL();
        sql.UPDATE("ordertbl");
        
        if (record.getTableid() != null) {
            sql.SET("TableID = #{tableid,jdbcType=INTEGER}");
        }
        
        if (record.getOrdertime() != null) {
            sql.SET("OrderTime = #{ordertime,jdbcType=VARCHAR}");
        }
        
        if (record.getUserid() != null) {
            sql.SET("UserID = #{userid,jdbcType=INTEGER}");
        }
        
        if (record.getPersonnum() != null) {
            sql.SET("personNum = #{personnum,jdbcType=INTEGER}");
        }
        
        if (record.getIdpay() != null) {
            sql.SET("idPay = #{idpay,jdbcType=INTEGER}");
        }
        
        if (record.getRemark() != null) {
            sql.SET("remark = #{remark,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}