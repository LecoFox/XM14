package com.model;

/**
 * 
 * @author ����������
 * ��ɫ��
 *
 */
public class Role {
	
 /**
  * @param id ���
  * @param name ����
  */
  private Integer id;
  private String name;
  private String description;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
  
}
