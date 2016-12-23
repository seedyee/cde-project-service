package io.cde.project.domain;

/**
 * @author lcl
 *
 */
public class Member {
	
	/**
	 * 用户id
	 */
	private String accountId;

	/**
	 * 项目成员名(用户名)
	 */
	private String principal;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}
}
