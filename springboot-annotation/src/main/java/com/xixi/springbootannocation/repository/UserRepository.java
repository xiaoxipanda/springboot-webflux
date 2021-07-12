package com.xixi.springbootannocation.repository;

import com.xixi.springbootannocation.domain.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * mongoDB Dao层
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

	/**
	 * 根据年龄查找用户
	 * 
	 * @param start 开始年龄
	 * @param end	结束年龄
	 * @return		flux user
	 */
	Flux<User> findByAgeBetween(int start, int end);

	/**
	 * 查询年龄大于等于20并且小于等于30的用户信息
	 * @return flux user
	 */
	@Query("{'age':{ '$gte': 20, '$lte' : 30}}")
	Flux<User> oldUser();
}
