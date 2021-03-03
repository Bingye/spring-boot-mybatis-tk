package com.bingye;

import com.bingye.domain.po.Country;
import com.bingye.persistence.CountryMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Primary;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;
import tk.mybatis.mapper.weekend.WeekendSqls;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MybatisTkApplicationTests {

    @Resource
    private CountryMapper countryMapper;

    @Test
    void insertList() {
        Country country = new Country();
        //country.setId(3);
        country.setCountryname("泰国");
        country.setCountrycode("002");

        Country country2 = new Country();
        //country.setId(3);
        country2.setCountryname("泰国");
        country2.setCountrycode("002");

        ArrayList<Country> countries = new ArrayList<>();
        countries.add(country);
        countries.add(country2);

        int insert = this.countryMapper.insertList(countries);
        System.out.println("插入："+insert);
    }

    @Test
    void insert() {
        Country country = new Country();
        //country.setId(3);
        country.setCountryname("泰国");
        country.setCountrycode("002");
        int insert = this.countryMapper.insert(country);
        System.out.println("插入："+insert);
    }

    @Test
    void selectOne(){
        Country country = new Country();
        country.setId(1);
        country = this.countryMapper.selectOne(country);
        System.out.println("查询一条记录："+country);
    }

    @Test
    void select(){
        Country country = new Country();
        List<Country> select = this.countryMapper.select(country);
        System.out.println("可能查询多行数据："+select);
    }

    @Test
    void findByCountryname(){
        Country country = this.countryMapper.findByCountryname("中国");
        System.out.println("自定义查询："+country);
    }

    @Test
    void findByCountrynameInXml(){
        Country country = this.countryMapper.findByCountrynameInXml("美国");
        System.out.println("xml配置查询："+country);
    }

    @Test
    void selectAll(){
        List<Country> countries = this.countryMapper.selectAll();
        System.out.println("查询所有数据："+countries);
    }

    @Test
    void selectCount(){
        Country country = new Country();
        int i = this.countryMapper.selectCount(country);
        System.out.println("总共多少条数据："+i);
    }

    @Test
    void selectByPrimaryKey(){
        Country country = this.countryMapper.selectByPrimaryKey(1);
        System.out.println("通过主键查询："+country);
    }

    @Test
    void selectByExample(){
        Example example = new Example(Country.class);
        example.setForUpdate(true);
        //example.createCriteria().andGreaterThan("id",1).andLessThan("id",100);
        example.createCriteria()
                .andGreaterThanOrEqualTo("id",1)
                .andLessThanOrEqualTo("id",100)
                .andLike("countryname","中%");
        example.or().andLessThan("id",3);
        example.orderBy("countrycode").desc();

        List<Country> countries = this.countryMapper.selectByExample(example);
        System.out.println("selectByExample："+ countries);
    }

    @Test
    void selectByExampleBuilder(){
        Example example = Example.builder(Country.class)
                .select("countryname")
                .where(Sqls.custom().andGreaterThan("id", 100))
                .orderByAsc("countrycode")
                .forUpdate()
                .build();
        List<Country> countries = this.countryMapper.selectByExample(example);
    }

    @Test
    void selectByWeekend(){
        Example example = Example.builder(Country.class)
                .where(
                        WeekendSqls.<Country>custom()
                                .andLike(Country::getCountryname,"中%")
                                .andGreaterThan(Country::getId,0)
                ).build();
        List<Country> countries = this.countryMapper.selectByExample(example);
        System.out.println("selectByWeekend："+countries);
    }

    @Test
    void page(){
        Page<Country> objects = PageHelper.startPage(1,2);
        List<Country> countries = this.countryMapper.selectAll();
        System.out.println("分页查询："+countries);
    }
}