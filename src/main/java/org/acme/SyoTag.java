package org.acme;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "syo_tag" )
public class SyoTag {
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id_tag" )
	private Integer id;

	@Column( name = "ds_tag" )
	private String tag;

	public Integer getId() {
		return id;
	}
	
	public void setId( Integer id ) {
		this.id = id;
	}
	
	public String getTag() {
		return tag;
	}
	
	public void setTag( String tag ) {
		this.tag = tag;
	}
	
}
