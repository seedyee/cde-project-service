package io.cde.project.dao;

import java.util.List;

import io.cde.project.domain.Project;

/**
 * @author lcl
 *
 */
public interface ProjectDao {
	
	/**
	 * 更行项目基本信息
	 * @param  project 要更新的项目的信息，包含项目id
	 * @return 更新成功返回1，否则返回-1
	 */
	int updateProject(final Project project);

	/**
	 * 获取项目成员的id集合
	 * @param  projectId 项目id
	 * @return 返回项目成员的id集合
	 */
	List<String> findProjectMembersId(final String projectId);

	/**
	 * 添加项目成员id
	 * @param  projectId  项目id
	 * @param  accountId 用户id
	 * @return 添加成功返回1，否则返回-1
	 */
	int addProjectMember(final String projectId, final String accountId);

	/**
	 * 删除项目成员id
	 * @param  projectId  项目id
	 * @param  accountId 用户id
	 * @return 删除成功返回1，否则返回-1
	 */
	int deleteProjectMember(final String projectId, final String accountId);

	/**
	 * 获取用户关注的或是收藏的项目的信息集合
	 * @param  projectIds 项目id集合
	 * @return 返回用户关注的或是收藏的项目的信息集合
	 */
	List<Project> findAccountProjects(final List<String> projectIds);
}