package io.cde.project.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.Repository;

import io.cde.project.domain.Project;

/**
 * @author lcl
 *
 */
public interface ProjectRepository extends Repository<Project, String> {

	/**
	 * 查询所有的项目，其中包括我创建的和我参与的项目。
	 * @param  accountId 用户id
	 * @return 返回查询出来的结果
	 */
	@Query(value = "{$or:[{'_id':?0}, {'friends': ?0]}", fields = "{'projectName':1, 'isPublic':1}")
	List<Project> findByAccountIdOrMembers(final String accountId);

	/**
	 * 根据用户id查询该用户创建的所有项目的集合
	 * 
	 * @param  accountId 用户id
	 * @return 返回用户创建的项目的集合
	 */
	@Query(fields = "{'projectName':1, 'isPublic':1}")
	List<Project> findByAccountId(final String accountId);

	/**
	 * 根据用户id查询用户参与的所有项目的集合
	 * 
	 * @param  accountId 用户id
	 * @return  返回用户参与的所有项目的集合
	 */
	@Query(fields = "{'projectName':1, 'isPublic':1}")
	List<Project> findByMembers(final String accountId);

	/**
	 * 根据项目id获取项目的详细信息
	 * 
	 * @param  id 项目id
	 * @return 返回项目的详细信息
	 */
	Project findById(final String id);
}
