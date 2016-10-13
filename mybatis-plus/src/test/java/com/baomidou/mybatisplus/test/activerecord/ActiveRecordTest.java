/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.test.activerecord;

import com.baomidou.mybatisplus.MybatisActiveRecord;
import com.baomidou.mybatisplus.MybatisSessionFactoryBuilder;
import com.baomidou.mybatisplus.activerecord.DB;
import com.baomidou.mybatisplus.activerecord.Record;
import com.baomidou.mybatisplus.test.mysql.TestMapper;
import com.baomidou.mybatisplus.toolkit.TableInfoHelper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * ActiveRecord 测试
 * </p>
 * 
 * @author Caratacus
 * @date 2016-10-13
 */
public class ActiveRecordTest {

	/**
	 * 原生Mybatis加载
	 */
	@Test
	public void m1() {
		// 加载配置文件
		InputStream inputStream = TestMapper.class.getClassLoader().getResourceAsStream("mysql-config.xml");
		MybatisSessionFactoryBuilder factoryBuilder = new MybatisSessionFactoryBuilder();
		SqlSessionFactory factory = factoryBuilder.build(inputStream);
		DB db = MybatisActiveRecord.open(factory);
		List<Record> records = db.active("test").select().all();
		System.out.println(records);
	}

	/**
	 * 原生JDBC加载
	 */
	@Test
	public void m2() {
		// jdbc原生没有缓存tableinfo信息,这里做初始化工作
		TableInfoHelper.initTableInfo(com.baomidou.mybatisplus.test.mysql.entity.Test.class);
		DB db = MybatisActiveRecord.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/mybatis-plus", "root", "521");
		List<Record> records = db.active("test").select().all();
		System.out.println(records);
	}

}
