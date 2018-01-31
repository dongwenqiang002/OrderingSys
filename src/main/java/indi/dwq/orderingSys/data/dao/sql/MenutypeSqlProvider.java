package indi.dwq.orderingSys.data.dao.sql;

import indi.dwq.orderingSys.data.pojo.Menutype;
import org.apache.ibatis.jdbc.SQL;

public class MenutypeSqlProvider {

    public String insertSelective(Menutype record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("menutypetbl");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Menutype record) {
        SQL sql = new SQL();
        sql.UPDATE("menutypetbl");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}