package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.dao.sql.OrderdetailSqlProvider;
import indi.dwq.orderingSys.data.pojo.Orderdetail;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderdetailMapper {
    @Delete({
        "delete from orderdetailtbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into orderdetailtbl (id, menuID, ",
        "orderID, num, remark)",
        "values (#{id,jdbcType=INTEGER}, #{menuid,jdbcType=INTEGER}, ",
        "#{orderid,jdbcType=INTEGER}, #{num,jdbcType=INTEGER}, #{remark,jdbcType=VARCHAR})"
    })
    int insert(Orderdetail record);

    @InsertProvider(type=OrderdetailSqlProvider.class, method="insertSelective")
    int insertSelective(Orderdetail record);

    @Select({
        "select",
        "id, menuID, orderID, num, remark",
        "from orderdetailtbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="menuID", property="menuid", jdbcType=JdbcType.INTEGER),
        @Result(column="orderID", property="orderid", jdbcType=JdbcType.INTEGER),
        @Result(column="num", property="num", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Orderdetail selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OrderdetailSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Orderdetail record);

    @Update({
        "update orderdetailtbl",
        "set menuID = #{menuid,jdbcType=INTEGER},",
          "orderID = #{orderid,jdbcType=INTEGER},",
          "num = #{num,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Orderdetail record);
}