package io.cde.project.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author lcl
 *
 */
@Document(collection = "project")
public class Project implements Serializable{

	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = 8703884661630221400L;
	
	/**
	 * 主键，项目的唯一标识. 使用mongodb的主键生成策略
	 */
	@Id
	private String id;

	/**
	 * 项目所有者id.
	 */
	private String accountId;

	/**
	 * 项目参与者id集合.
	 */
	private List<String> members;

	/**
	 * 项目名称.
	 */
	private String projectName;

	/**
	 * 项目描述.
	 */
	private String describe;

	/**
	 * 该项目是否是公开.
	 */
	private boolean isPublice;

	/**
	 * 项目创建时间.
	 */
	private Date timestamp;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public List<String> getMembers() {
		return members;
	}

	public void setMembers(List<String> members) {
		this.members = members;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public boolean isPublice() {
		return isPublice;
	}

	public void setPublice(boolean isPublice) {
		this.isPublice = isPublice;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
