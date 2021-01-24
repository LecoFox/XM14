package com.model;

import java.util.Date;

/**
 * 
 * @author ����������
 *
 * ��ɫȨ�ޱ�
 */
public class RolePrivilege {
 /**
  * @param rid ��ɫ���
  * @param pid Ȩ�ޱ��
  * @param createBy ������
  * @param createDate ��������
  */
  private Integer rid;
  private Integer pid;
  private Integer createBy;
  private Date createDate;
  public Integer getRid() {
    return rid;
  }

  public void setRid(Integer rid) {
    this.rid = rid;
  }


  public Integer getPid() {
    return pid;
  }

  public void setPid(Integer pid) {
    this.pid = pid;
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
		return "RolePrivilege [rid=" + rid + ", pid=" + pid + ", createBy=" + createBy + ", createDate=" + createDate + "]";
	}
  
}
