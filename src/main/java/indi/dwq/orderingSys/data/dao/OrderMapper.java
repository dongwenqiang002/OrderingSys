package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.dao.sql.OrderSqlProvider;
import indi.dwq.orderingSys.data.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {
    @Delete({
        "delete from ordertbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into ordertbl (id, TableID, ",
        "OrderTime, UserID, ",
        "personNum, idPay, ",
        "remark)",
        "values (#{id,jdbcType=INTEGER}, #{tableid,jdbcType=INTEGER}, ",
        "#{ordertime,jdbcType=VARCHAR}, #{userid,jdbcType=INTEGER}, ",
        "#{personnum,jdbcType=INTEGER}, #{idpay,jdbcType=INTEGER}, ",
        "#{remark,jdbcType=VARCHAR})"
    })
    int insert(Order record);

    @InsertProvider(type=OrderSqlProvider.class, method="insertSelective")
    int insertSelective(Order record);

    @Select({
        "select",
        "id, TableID, OrderTime, UserID, personNum, idPay, remark",
        "from ordertbl",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="TableID", property="tableid", jdbcType=JdbcType.INTEGER),
        @Result(column="OrderTime", property="ordertime", jdbcType=JdbcType.VARCHAR),
        @Result(column="UserID", property="userid", jdbcType=JdbcType.INTEGER),
        @Result(column="personNum", property="personnum", jdbcType=JdbcType.INTEGER),
        @Result(column="idPay", property="idpay", jdbcType=JdbcType.INTEGER),
        @Result(column="remark", property="remark", jdbcType=JdbcType.VARCHAR)
    })
    Order selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Order record);

    @Update({
        "update ordertbl",
        "set TableID = #{tableid,jdbcType=INTEGER},",
          "OrderTime = #{ordertime,jdbcType=VARCHAR},",
          "UserID = #{userid,jdbcType=INTEGER},",
          "personNum = #{personnum,jdbcType=INTEGER},",
          "idPay = #{idpay,jdbcType=INTEGER},",
          "remark = #{remark,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Order record);
}