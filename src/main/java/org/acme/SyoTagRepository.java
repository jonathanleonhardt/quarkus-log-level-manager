package org.acme;

import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class SyoTagRepository implements PanacheRepository<SyoTag> {
	
	public List<SyoTag> findById() {
			return listAll();
	}

}
