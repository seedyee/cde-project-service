package io.cde.project.domain;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author lcl
 *
 */
@Document(collection = "account")
public class Member implements Serializable{
	
	/**
	 * 序列化id
	 */
	private static final long serialVersionUID = -3745206453944050818L;

	/**
	 * 用户id
	 */
	@Id
	private String id;

	/**
	 * 项目成员名(用户名)
	 */
	private String name;
	
	/**
	 * 用户关注的项目的id集合
	 */
	@JsonIgnore
	private List<String> watchedProjects;
	
	/**
	 * 用户收藏的项目的id集合
	 */
	@JsonIgnore
	private List<String> collectProjects;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getWatchedProjects() {
		return watchedProjects;
	}

	public void setWatchedProjects(List<String> watchedProjects) {
		this.watchedProjects = watchedProjects;
	}

	public List<String> getCollectProjects() {
		return collectProjects;
	}

	public void setCollectProjects(List<String> collectProjects) {
		this.collectProjects = collectProjects;
	}
}
