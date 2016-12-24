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

import io.cde.project.dao.AccountDao;
import io.cde.project.domain.Member;

/**
 * @author lcl
 *
 */
public class AccountDaoImpl implements AccountDao {
	
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
    public AccountDaoImpl(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#findMember(java.lang.String)
	 */
	@Override
	public Member findMember(String principal) {
		DBObject queryBuilder = QueryBuilder.start().or(
				QueryBuilder.start("name").is(principal).get(),
				QueryBuilder.start("email").is(principal).get()
			).get();
		BasicDBObject fieldObject = new BasicDBObject();
		fieldObject.put("name", true);
		Query query = new BasicQuery(queryBuilder, fieldObject);
		Member member = mongoTemplate.findOne(query, Member.class);
		return member;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#findMemberById(java.lang.String)
	 */
	@Override
	public Member findMemberById(String accountId) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#findProjectMembers(java.util.List)
	 */
	@Override
	public List<Member> findProjectMembers(List<String> accountIds) {
		DBObject queryBuilder = QueryBuilder.start("_id").in(accountIds).get();
		BasicDBObject fieldObject = new BasicDBObject();
		fieldObject.put("name", true);
		Query query = new BasicQuery(queryBuilder, fieldObject);
		List<Member> members = mongoTemplate.find(query, Member.class);
		return members;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#addWatchedProject(java.lang.String, java.lang.String)
	 */
	@Override
	public int addWatchedProject(String accountId, String projectId) {
		final Query query = Query.query(Criteria.where("_id").is(accountId));
		Update update = new Update();
		update.addToSet("watchedProjects", projectId);
		WriteResult updateFirst = mongoTemplate.updateFirst(query, update, Member.class);
		if (updateFirst.getN() <= 0) {
			return -1;
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#findWatchedProject(java.lang.String)
	 */
	@Override
	public List<String> findWatchedProject(String accountId) {
		List<String> watchs;
		BasicDBObject queryObject = new BasicDBObject("_id", accountId);
		BasicDBObject fieldsObject = new BasicDBObject();
		fieldsObject.put("watchedProjects", true);
		Query query = new BasicQuery(queryObject, fieldsObject);
		Member member = mongoTemplate.findOne(query, Member.class);
		watchs = member.getWatchedProjects();
		return watchs;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#deleteWatchedProject(java.lang.String, java.lang.String)
	 */
	@Override
	public int deleteWatchedProject(String accountId, String projectId) {
		Query query = Query.query(Criteria.where("_id").is(accountId));
		Update update = new Update();
		update.pull("watchedProjects", projectId);
		WriteResult updateFirst = mongoTemplate.updateFirst(query, update, Member.class);
		if (updateFirst.getN() <= 0) {
			return -1;
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#addCollectionProject(java.lang.String, java.lang.String)
	 */
	@Override
	public int addCollectionProject(String accountId, String projectId) {
		Query query = Query.query(Criteria.where("_id").is(accountId));
		Update update = new Update();
		update.addToSet("collectProjects", projectId);
		WriteResult updateFirst = mongoTemplate.updateFirst(query, update, Member.class);
		if (updateFirst.getN() <= 0) {
			return -1;
		}
		return 1;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#findCollectionProject(java.lang.String)
	 */
	@Override
	public List<String> findCollectionProject(String accountId) {
		List<String> collects;
		BasicDBObject queryObject = new BasicDBObject("_id", accountId);
		BasicDBObject fieldsObject = new BasicDBObject();
		fieldsObject.put("collectProjects", true);
		Query query = new BasicQuery(queryObject, fieldsObject);
		Member member = mongoTemplate.findOne(query, Member.class);
		collects = member.getWatchedProjects();
		return collects;
	}

	/* (non-Javadoc)
	 * @see io.cde.project.dao.AccountDao#deleteCollectionProject(java.lang.String, java.lang.String)
	 */
	@Override
	public int deleteCollectionProject(String accountId, String projectId) {
		Query query = Query.query(Criteria.where("_id").is(accountId));
		Update update = new Update();
		update.pull("collectProjects", projectId);
		WriteResult updateFirst = mongoTemplate.updateFirst(query, update, Member.class);
		if (updateFirst.getN() <= 0) {
			return -1;
		}
		return 1;
	}
}
