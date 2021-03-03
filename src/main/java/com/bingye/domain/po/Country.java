package com.bingye.domain.po;

import com.bingye.utils.UUIdGenId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.IdentityDialect;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Transient;

@Data
@NoArgsConstructor
@AllArgsConstructor
//配置该注解后，对该类和其中的字段进行转换时，会将形如 userName 的字段转换为表中的 USER_NAME 字段。
@NameStyle(Style.camelhumpAndUppercase)
public class Country {

    //1、支持自增的数据库，jdbc方式
    //@KeySql(useGeneratedKeys = true)
    //@GeneratedValue(generator = "JDBC")
    //<insert id="insert" useGeneratedKeys="true" keyProperty="id">
    //    insert into country (id, countryname, countrycode)
    //    values (#{id},#{countryname},#{countrycode})
    //</insert>

    //2、支持自增的数据库
    //DB2: VALUES IDENTITY_VAL_LOCAL()
    //MYSQL: SELECT LAST_INSERT_ID()
    //SQLSERVER: SELECT SCOPE_IDENTITY()
    //CLOUDSCAPE: VALUES IDENTITY_VAL_LOCAL()
    //DERBY: VALUES IDENTITY_VAL_LOCAL()
    //HSQLDB: CALL IDENTITY()
    //SYBASE: SELECT @@IDENTITY
    //DB2_MF: SELECT IDENTITY_VAL_LOCAL() FROM SYSIBM.SYSDUMMY1
    //INFORMIX: select dbinfo('sqlca.sqlerrd1') from systables where tabid=1
    //DEFAULT 需要配合 IDENTITY 参数（ORDER默认AFTER）

    //@KeySql(dialect = IdentityDialect.DEFAULT)
    //建议直接指定数据库
    //@KeySql(dialect = IdentityDialect.MYSQL)
    //或者@GeneratedValue(strategy = GenerationType.IDENTITY)

    //3、通过序列和任意 SQL 获取主键值
    //@KeySql(sql = "select SEQ_ID.nextval from dual", order = ORDER.BEFORE)
    //或者 @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select SEQ_ID.nextval from dual")
    //@GeneratedValue(strategy = GenerationType.IDENTITY,generator = "select uuid()")  注意此时主键应该是string类型

    //4、uuid 方式@GeneratedValue(generator = "UUID") 不推荐使用
    //@KeySql(genId = UUIdGenId.class)
    @Id
    @KeySql(dialect = IdentityDialect.MYSQL)
    private Integer id;

    private String countryname;

    private String countrycode;

    //除了直接映射 name 到 user_name 这种用法外，在使用关键字的情况，还会有下面的用法：
    //@Column(name = "`order`")
    //private String order;

    //非数据库表中字段
    @Transient
    private String otherThings;

}
