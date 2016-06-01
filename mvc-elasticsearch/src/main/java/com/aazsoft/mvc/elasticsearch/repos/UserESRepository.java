package com.aazsoft.mvc.elasticsearch.repos;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.aazsoft.mvc.elasticsearch.docs.UserDocument;

public interface UserESRepository extends ElasticsearchRepository<UserDocument, Long> {

	public List<UserDocument> findByEmail(final String email);

}
