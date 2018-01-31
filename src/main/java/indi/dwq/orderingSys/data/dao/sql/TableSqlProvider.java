package indi.dwq.orderingSys.data.dao.sql;

import indi.dwq.orderingSys.data.pojo.Table;
import org.apache.ibatis.jdbc.SQL;

public class TableSqlProvider {

    public String insertSelective(Table record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tabletbl");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getOrdId() != null) {
            sql.VALUES("Ord_id", "#{ordId,jdbcType=INTEGER}");
        }
        
        if (record.getNum() != null) {
            sql.VALUES("num", "#{num,jdbcType=VARCHAR}");
        }
        
        if (record.getFlag() != null) {
            sql.VALUES("flag", "#{flag,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.VALUES("description", "#{description,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Table record) {
        SQL sql = new SQL();
        sql.UPDATE("tabletbl");
        
        if (record.getOrdId() != null) {
            sql.SET("Ord_id = #{ordId,jdbcType=INTEGER}");
        }
        
        if (record.getNum() != null) {
            sql.SET("num = #{num,jdbcType=VARCHAR}");
        }
        
        if (record.getFlag() != null) {
            sql.SET("flag = #{flag,jdbcType=VARCHAR}");
        }
        
        if (record.getDescription() != null) {
            sql.SET("description = #{description,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}