package io.cde.project.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteResult;

import io.cde.project.dao.ProjectDao;
import io.cde.project.domain.Project;

/**
 * @author lcl
 *
 */
public class ProjectDaoImpl implements ProjectDao {
	
	/**
	 * MongoTemplate对象
	 */
	private MongoTemplate mongoTemplate;
	
	/**
     * 通过构造器注入对象.
     *
     * @param mongoTemplate mongoTemplate对象
     */
    @Autowired
    public ProjectDaoImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	/* (non-Javadoc)
	 * @see io.cde.project.dao.ProjectDao#updateProject(io.cde.project.domain.Project)
	 */
	@Override
	public int updateProject(final Project project) {
		final Query query = Query.query(Criteria.where("_id").is(project.getId()));
		final Update update = Update.update("projectName", project.getProjectName()).
				set("describe", project.getDescribe()).set("isPublic", project.isPublice());
		final WriteResult updateFirst = mongoTemplate.updateFirst(query, update, Project.class);
		if (updateFirst.getN() <= 0) {
			return -1;
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.ProjectDao#findProjectMembersId(java.lang.String)
	 */
	@Override
	public List<String> findProjectMembersId(final String projectId) {
		final BasicDBObject queryObject = new BasicDBObject("_id", projectId);
		final BasicDBObject fieldsObject = new BasicDBObject();
		fieldsObject.put("members", true);
		final Query query = new BasicQuery(queryObject, fieldsObject);
		final Project project = mongoTemplate.findOne(query, Project.class);
		return project.getMembers();
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.ProjectDao#addProjectMember(java.lang.String, java.lang.String)
	 */
	@Override
	public int addProjectMember(final String projectId, final String accountId) {
		final Query query = Query.query(Criteria.where("_id").is(projectId));
		final Update update = new Update();
		update.addToSet("members", accountId);
		final WriteResult updateFirst = mongoTemplate.updateFirst(query, update, Project.class);
		if (updateFirst.getN() <= 0) {
			return -1;
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.ProjectDao#deleteProjectMember(java.lang.String, java.lang.String)
	 */
	@Override
	public int deleteProjectMember(final String projectId, final String accountId) {
		final Query query = Query.query(Criteria.where("_id").is(projectId));
		final Update update = new Update();
		update.pull("members", accountId);
		final WriteResult updateFirst = mongoTemplate.updateFirst(query, update, Project.class);
		if (updateFirst.getN() <= 0) {
			return -1;
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.ProjectDao#findAccountProjects(java.util.List)
	 */
	@Override
	public List<Project> findAccountProjects(final List<String> projectIds) {
		final DBObject queryBuilder = QueryBuilder.start("members").in(projectIds).get();
		final BasicDBObject fieldObject = new BasicDBObject();
		fieldObject.put("projectName", true);
		fieldObject.put("isPublic", true);
		final Query query = new BasicQuery(queryBuilder, fieldObject);
		final List<Project> projects = mongoTemplate.find(query, Project.class);
		return projects;
	}

}
