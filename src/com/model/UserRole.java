package com.model;

import java.util.Date;
/**
 * 
 * @author ����������
 * �û���ɫ��
 *
 */
public class UserRole {
  /**
   * @param uid �û����
   * @param rid ��ɫ���
   * @param createBy ������
   * @param createDate ��������
   */
  private Integer uid;
  private Integer rid;
  private Integer createBy;
  private Date createDate;


  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }


  public Integer getRid() {
    return rid;
  }

  public void setRid(Integer rid) {
    this.rid = rid;
  }


  public Integer getCreateBy() {
    return createBy;
  }

  public void setCreateBy(Integer createBy) {
    this.createBy = createBy;
  }


  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

	@Override
	public String toString() {
		return "UserRole [uid=" + uid + ", rid=" + rid + ", createBy=" + createBy + ", createDate=" + createDate + "]";
	}
  
}
