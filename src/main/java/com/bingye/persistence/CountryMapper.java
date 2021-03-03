package com.bingye.persistence;

import com.bingye.config.TkMapper;
import com.bingye.domain.po.Country;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

public interface CountryMapper extends TkMapper<Country> {

    @Select("select * from country where countryname=#{countryname}")
    Country findByCountryname(String countryname);

    Country findByCountrynameInXml(String countryname);

}
